package s13o.test.search.index.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s13o.test.search.index.api.*;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Component
public class SimpleIndex implements Index {

    private DocStore storage;
    private Vocabulary vocabulary;
    private TokFactory factory;

    public SimpleIndex(@Autowired DocStore storage, @Autowired Vocabulary vocabulary, @Autowired TokFactory factory) {
        this.storage = storage;
        this.vocabulary = vocabulary;
        this.factory = factory;
    }

    @Override
    public Integer add(@NotNull Document doc) throws IllegalContentException, AlreadyExistsException {
        return vocabulary.add(storage.add(doc), factory.create(doc).tokensOf(doc));
    }

    @Override
    public Stream<Document> get(@NotNull String key) {
        return storage.get(key);
    }

    @Override
    public Stream<Document> findAny(@NotNull String content) throws WrongParametersException {
        final Document doc = new PlainTextDocument("findAny", content);
        try {
            return factory.create(doc).tokensOf(doc)
                    .parallel().map(this::listRefByToken)
                    .collect(ConcurrentHashMap::new,
                            (BiConsumer<Map<Integer, DocRef>, List<DocRef>>)
                                    (map, list) -> list.forEach((r) -> map.put(r.getId(), r)),
                            Map::putAll)
                    .entrySet().stream().map(Map.Entry::getValue)
                    .map((ref) -> storage.get(ref.getId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        } catch (IllegalContentException e) {
            throw new WrongParametersException(content);
        }
    }

    private List<DocRef> listRefByToken(Token token) {
        return vocabulary.getRef(token.getToken()).collect(Collectors.toList());
    }

    @Override
    public Stream<Document> findAll(@NotNull String content) throws WrongParametersException {
        final Document doc = new PlainTextDocument("findAll", content);
        try {
            final Optional<Set<Integer>> set =  factory.create(doc).tokensOf(doc)
                    .parallel()
                    .collect(ConcurrentHashMap::new,
                            (BiConsumer<Map<String, Set<Integer>>, Token>)
                                (map, token) -> map.computeIfAbsent(
                                        token.getToken(), (k) -> new HashSet<>()).addAll(listIdByToken(token)),
                                (map1, map2) -> map2.forEach((key, value) -> {
                                    map1.computeIfAbsent(key, (k) -> new HashSet<>()).addAll(value);
                                    }
                                )
            ).values().stream().reduce(
                    BinaryOperator.minBy(
                            (o1, o2) -> o1.containsAll(o2) ? o2.containsAll(o1) ? 0 : 1 : -1
                    )
            );
            if(!set.isPresent())
                return Stream.empty();

            return set.get().stream()
                    .map((id) -> storage.get(id))
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        } catch (IllegalContentException e) {
            throw new WrongParametersException(content);
        }
    }

    private List<Integer> listIdByToken(Token token) {
        return vocabulary.getRef(token.getToken())
                .map(DocRef::getId)
                .distinct().sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Stream<Document> getAll() throws WrongParametersException {
        return findAny(vocabulary.allTokens().collect(Collectors.joining(" ", "", "")));
    }
}
