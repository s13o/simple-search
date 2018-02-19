package s13o.test.search.index.simple;

import s13o.test.search.index.api.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
public class SimpleDocStore implements DocStore {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private Map<String, Integer> key2index = new ConcurrentHashMap<>();
    private List<Document> storage = new LinkedList<>();

    @Override
    public DocRef add(@NotNull Document document) throws AlreadyExistsException{
        Integer index = key2index.get(document.getKeyword());
        if (index != null)
            throw new AlreadyExistsException(document.getKeyword());
        lock.writeLock().lock();
        try {
            final SimpleDocRef ref = new SimpleDocRef(document.getKeyword(), storage.size(), null);
            storage.add(document);
            key2index.put(document.getKeyword(), ref.getId());
            return ref;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Stream<Document> get(@NotNull String keyword) {
        Integer index = key2index.get(keyword);
        if (index == null)
            return Stream.empty();
        return get(index).map(Stream::of).orElseGet(Stream::empty);
    }

    @Override
    public Optional<Document> get(@Positive int docIndex) {
        lock.readLock().lock();
        try {
            if (docIndex > storage.size() || docIndex < 0)
                return Optional.empty();
            return Optional.of(storage.get(docIndex));
        } finally {
            lock.readLock().unlock();
        }
    }
}
