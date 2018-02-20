package s13o.test.search.index.api;

/**
 * @author {@link "mailto:roman.solodovnichenko@playtech.com" "romanso"}
 * @since 2/20/2018
 */
public class WrongParametersException extends Exception {
    private final String content;

    public WrongParametersException(String content) {
        super(String.format("Search content %s is not supported", content));
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
