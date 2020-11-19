package pl.coderslab.book;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher){
        entityManager.persist(publisher);
    }

    public void update(Publisher author) {
        entityManager.merge(author);
    }
    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ?
                publisher : entityManager.merge(publisher)); }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id); }

    public List<Publisher> getAll(){
        Query query = entityManager.createQuery("SELECT p from Publisher p");
        return query.getResultList();
    }

}
