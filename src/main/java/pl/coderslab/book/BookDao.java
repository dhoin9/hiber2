package pl.coderslab.book;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book){
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }
    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book)); }

    public Book findById(long id) {
        return entityManager.find(Book.class, id); }

    public List<Book> getAll(){
        Query query = entityManager.createQuery("SELECT b from Book b ");
        return query.getResultList();
    }
    public List<Book> getAll(Publisher publisher){
        Query query = entityManager.createQuery("SELECT b from Book b WHERE b.publisher=:selectedPublisher ");
        query.setParameter("selectedPublisher", publisher);
        return query.getResultList();
    }
    public List<Book> getAll(Author author){
        Query query = entityManager.createQuery("SELECT b from Book b WHERE :selectedAuthor member of b.authorLis");
        query.setParameter("selectedAuthor", author);
        return query.getResultList();
    }
}
