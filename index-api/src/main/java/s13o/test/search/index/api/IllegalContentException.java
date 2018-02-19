package s13o.test.search.index.api;

/**
 * Probably, not all type of content could be supported. For example "null" or "empty"
 * {@link Document#getContent()} is not acceptable
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public class IllegalContentException extends Exception {
    private final String keyword;

    public IllegalContentException(String keyword) {
        super(String.format("The content of Document with keyword %s is missed or not supported", keyword));
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
