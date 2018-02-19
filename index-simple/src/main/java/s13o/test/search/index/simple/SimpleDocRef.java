package s13o.test.search.index.simple;

import lombok.Data;
import s13o.test.search.index.api.DocRef;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Data
public class SimpleDocRef implements DocRef {
    final String keyword;
    final int id;
    final Date registeredAt;

    public SimpleDocRef(@NotNull String keyword, @Positive int id, Date registeredAt) {
        this.registeredAt = registeredAt != null ? registeredAt : new Date();
        this.keyword = keyword;
        this.id = id;
    }

}
