package s13o.test.search.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import s13o.test.search.index.api.Document;

/**
 * @author {@link "mailto:roman.solodovnichenko@playtech.com" "romanso"}
 * @since 2/20/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doc implements Document {
    private String keyword;
    private String content;
}
