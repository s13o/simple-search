package s13o.test.search.index.api;

import javax.validation.constraints.NotNull;
import java.util.stream.Stream;

/**
 * Vocabulary is basically an index. It based on Dictionly of processed Tokens
 * and manage relations between Tokens and {@link DocRef#getId()}
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface Vocabulary {

    Integer add(@NotNull DocRef doc, @NotNull Stream<Token> stream);

    Stream<DocRef> getRef(@NotNull String token);

    Stream<String> allTokens();

}
