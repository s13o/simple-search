package s13o.test.search.index.api;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Stream;

/**
 * The object is designed to store sorted list of offsets for specific "word\token" in a single document
 *
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public class TokenOffsets {
    /**
     * index of the {@link s13o.test.search.index.api.Document} in {@link s13o.test.search.index.api.DocStore}
     */
    private final int docIndex;
    /**
     * sorted list of offsets for some "word\token" in single document
     */
    private Queue<Long> offsets;

    public TokenOffsets(DocRef ref) {
        this(ref.getId());
    }

    public TokenOffsets(int docIndex) {
        this.docIndex = docIndex;
        this.offsets = new ConcurrentLinkedDeque<>();
    }

    public void register(Long offset) {
        offsets.add(offset);
    }

    public int frequency() {
        return offsets.size();
    }

    public Stream<Long> offsetStream() {
        return offsets.stream();
    }

}
