package s13o.test.search.index.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s13o.test.search.index.api.*;
import s13o.test.search.index.api.Vocabulary;

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
    public Stream<Document> findAny(@NotNull String content) throws IllegalContentException {
        final Document doc = new PlainTextDocument("findAny", content);
        final List<Document> refs = factory.create(doc).tokensOf(doc)
                .parallel().collect(
                LinkedList::new,
                (BiConsumer<List<Document>, Token>) (list, token1) ->
                        list.addAll(
                                vocabulary.get(token1.getToken())
                                        .map((id) -> storage.get(id.getId()))
                                        .filter(Optional::isPresent)
                                        .map(Optional::get).collect(Collectors.toList())),
                List::addAll);
        return refs.stream();
    }

    @Override
    public Stream<Document> findAll(@NotNull String content) throws IllegalContentException {
        final Document doc = new PlainTextDocument("findAll", content);
        final Stream<Token> token = factory.create(doc).tokensOf(doc);
        final Map<String, Set<Integer>> map = token.parallel().collect(
                ConcurrentHashMap::new,
                (BiConsumer<Map<String, Set<Integer>>, Token>) (map1, token1) ->
                        map1.computeIfAbsent(token1.getToken(), (k) -> new HashSet<>())
                                .addAll(
                                        vocabulary.get(token1.getToken()).map(DocRef::getId)
                                                .collect(Collectors.toList())),
                (m1, m2) -> m2.forEach((key, value) -> {
                    m1.computeIfAbsent(key, (k) -> new HashSet<>()).addAll(value);
                })
        );
        final Optional<Set<Integer>> set = map.values().stream().reduce(
                BinaryOperator.minBy(
                        (o1, o2) -> o1.containsAll(o2)?1:-1
                )
        );
        if(!set.isPresent())
            return Stream.empty();

        return set.get().stream()
                .map((id) -> storage.get(id))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

}
