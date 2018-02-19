package s13o.test.search.index.api;

import javax.validation.constraints.NotNull;

/**
 * Since potentially a {@link Document} can be a JSON, XML or another SGML-like document
 * it is better to have Factory for Tokenizers per Document type
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface TokFactory {
    Tokenizer create(@NotNull Document document);
}
