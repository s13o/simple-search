package s13o.test.search.index.api;

/**
 * A Document (main entry) that we are going to process with the project.
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface Document {
    /**
     * I think it is not so goot to store the "keyword" (kind of external ID) with Document - it should be in
     * {@link DocRef}. We can assume that the "keyword" here is an original raw data but in {@link DocRef}
     * we storing some "extracted\normalized keywords(s)", they are equals in current simple implementation.
     * Actually when you will check how the method is used - it is it.
     *
     * @return Keyword of the Document (some kind of unique external ID, like an URL or GUID)
     */
    String getKeyword();

    /**
     * I think it could be better to use some {@link java.io.InputStream} but the {@link CharSequence}
     * is still acceptable for test and a little better than simple {@link String} - at least some kind of interface
     *
     * @return content of the Document
     */
    CharSequence getContent();
}
