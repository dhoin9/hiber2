package pl.coderslab.book;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Author author){
        entityManager.persist(author);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }
    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author)); }

    public Author findById(long id) {
        return entityManager.find(Author.class, id); }


    public List<Author> getAll(){
        Query query = entityManager.createQuery("SELECT a from Author a");
        return query.getResultList();
    }

}
