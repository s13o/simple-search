package s13o.test.search.index.api;


import javax.validation.constraints.NotNull;

/**
 * The requirement today than only one document should be stored per key.
 * Exception could be thrown to indicate that some document is going to be added to index twice
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public class AlreadyExistsException extends Exception {
    private final String key;

    public AlreadyExistsException(@NotNull String key) {
        super(String.format("Document with key %s already exist", key));
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
