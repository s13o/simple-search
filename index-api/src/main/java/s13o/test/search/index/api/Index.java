package s13o.test.search.index.api;

import javax.validation.constraints.NotNull;
import java.util.stream.Stream;

/**
 * Central Service Interface of the project.
 * It declare only methods required by the test assignment
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public interface Index {
    Integer add(@NotNull Document doc) throws IllegalContentException, AlreadyExistsException;

    /**
     * @param key it is actually a "keyword", external ID
     * @return Stream of references on {@link Document} but in current implementation (as required) it could be only one
     * Document
     */
    Stream<Document> get(@NotNull String key);

    Stream<Document> findAny(@NotNull String content) throws IllegalContentException;

    Stream<Document> findAll(@NotNull String content) throws IllegalContentException;
}
