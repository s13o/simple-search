package s13o.test.search.index.api;

import java.util.stream.Stream;

/**
 * Extractor of {@link Token}s from content of the document
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface Tokenizer {
    Stream<Token> tokensOf(Document document) throws IllegalContentException;
}
