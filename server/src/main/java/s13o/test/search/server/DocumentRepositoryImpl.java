package s13o.test.search.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s13o.test.search.index.api.AlreadyExistsException;
import s13o.test.search.index.api.Document;
import s13o.test.search.index.api.IllegalContentException;
import s13o.test.search.index.api.Index;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/19/2018
 */
@Component
public class DocumentRepositoryImpl implements DocumentRepository {

    private Index index;

    public DocumentRepositoryImpl(@Autowired Index index) {
        this.index = index;
    }

    @Override
    public Document save(Document entity) throws AlreadyExistsException, IllegalContentException {
        index.add(entity);
        return entity;
    }

    @Override
    public Iterable<Document> find(String criteria) throws IllegalContentException {
        return index.findAll(criteria)::iterator;
    }
}
