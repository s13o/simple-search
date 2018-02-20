package s13o.test.search.index.api;

/**
 * A Document (main entry) that we are going to process with the project.
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface Document {
    /**
     * I think it is not so goot to store the "key" (kind of external ID) with Document - it should be in
     * {@link DocRef}. We can assume that the "key" here is an original raw data but in {@link DocRef}
     * we storing some "extracted\normalized key(s)", they are equals in current simple implementation.
     * Actually when you will check how the method is used - it is it.
     *
     * @return Key of the Document (some kind of unique external ID, like an URL or GUID)
     */
    String getKey();

    /**
     * I think it could be better to use some {@link java.io.InputStream} but the {@link CharSequence}
     * is still acceptable for test and a little better than simple {@link String} - at least some kind of interface
     *
     * @return content of the Document
     */
    CharSequence getContent();
}
