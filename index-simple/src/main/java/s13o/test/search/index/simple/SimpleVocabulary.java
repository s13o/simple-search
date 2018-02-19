package s13o.test.search.index.simple;

import org.springframework.stereotype.Component;
import s13o.test.search.index.api.DocRef;
import s13o.test.search.index.api.Token;
import s13o.test.search.index.api.TokenOffsets;
import s13o.test.search.index.api.Vocabulary;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Component
public class SimpleVocabulary implements Vocabulary {
    /**
     * Map between on a "word\token" and another Map - between ID of Document and {@link TokenOffsets} for the "token"
     */
    private Map<String, Map<DocRef, TokenOffsets>> map = new ConcurrentHashMap<>();

    @Override
    public Integer add(@NotNull DocRef ref, @NotNull Stream<Token> stream) {
        stream.forEach(
                (token) ->{
                    map.computeIfAbsent(token.getToken(), (f) -> new ConcurrentHashMap<>())
                            .computeIfAbsent(ref, TokenOffsets::new).register(token.getOffset());
                }
        );
        return ref.getId();
    }

    /**
     * Return ID of documents with the "token"
     */
    @Override
    public Stream<DocRef> get(@NotNull String token){
        return map.getOrDefault(token, Collections.emptyMap())
                .entrySet().stream().map(Map.Entry::getKey);
    }

}
