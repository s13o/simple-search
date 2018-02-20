package s13o.test.search.index.api;

import java.util.Date;

/**
 * Some kind of link between "external id" and "internal id".
 * Like a complex key-object (reference) of {@link Document} at {@link DocStore}
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface DocRef {

    /**
     * @return "external" id of the {@link Document}
     */
    String getKeyword();

    /**
     * @return index (internal registered number) of the Document in {@link DocStore}
     */
    int getId();

    /**
     * @return Document registration Date
     */
    Date getRegisteredAt();

}
