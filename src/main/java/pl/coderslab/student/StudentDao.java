package pl.coderslab.student;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Student student){
        entityManager.persist(student);
    }

    public void update(Student student) {
        entityManager.merge(student);
    }
    public void delete(Student student) {
        entityManager.remove(entityManager.contains(student) ?
                student : entityManager.merge(student)); }

    public Student findById(long id) {
        return entityManager.find(Student.class, id); }
}

