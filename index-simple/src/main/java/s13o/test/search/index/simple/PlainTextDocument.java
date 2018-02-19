package s13o.test.search.index.simple;

import lombok.Data;
import s13o.test.search.index.api.Document;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Data
public class PlainTextDocument implements Document {
    private final String keyword;
    private final String content;
}
