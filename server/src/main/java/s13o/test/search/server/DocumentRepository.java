package s13o.test.search.server;

import org.springframework.data.repository.Repository;
import s13o.test.search.index.api.AlreadyExistsException;
import s13o.test.search.index.api.Document;
import s13o.test.search.index.api.IllegalContentException;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/19/2018
 */
public interface DocumentRepository extends Repository<Document, Long> {

    Document save(Document entity) throws AlreadyExistsException, IllegalContentException;

    Iterable<Document> find(String criteria)throws IllegalContentException;

}
