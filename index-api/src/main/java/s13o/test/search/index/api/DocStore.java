package s13o.test.search.index.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Storage of registered {@link Document}s
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface DocStore {
    /**
     * Register new Document to the DocStore
     * @param document
     * @return reference on Document from the DocStore
     * @throws AlreadyExistsException
     * @throws IllegalContentException
     */
    DocRef add(@NotNull Document document) throws AlreadyExistsException;

    /**
     * Find a document(s) by keyword
     * @param keyword
     */
    Stream<Document> get(@NotNull String keyword);

    /**
     * Find a document by registration ID
     * @param docIndex
     */
    Optional<Document> get(@Positive int docIndex);
}
