package pl.coderslab.animals;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AnimalDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Animal animal){
        entityManager.persist(animal);
    }


}
