package s13o.test.search.index.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Immutable Token of which a {@link Document} consists.
 * Always belongs to one Document only, contains {@link #offset} of a {#token}
 * from top of the Document
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Data
public class Token {
    /**
     * lowercased word, in normalized view
     */
    private final String token;
    /**
     * position in a document (some kind of meta information of the Token)
     */
    private final long offset;

    public Token(@Positive long offset, @NotNull String token) {
        this.token = token;
        this.offset = offset;
    }

}
