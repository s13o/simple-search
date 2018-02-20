package s13o.test.search.index.api;

/**
 * Probably, not all type of content could be supported. For example "null" or "empty"
 * {@link Document#getContent()} is not acceptable
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public class IllegalContentException extends Exception {
    private final String key;

    public IllegalContentException(String key) {
        super(String.format("The content of Document with key %s is missed or not supported", key));
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
