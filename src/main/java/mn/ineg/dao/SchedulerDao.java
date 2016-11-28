/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mn.ineg.model.MScheduler;
import org.springframework.stereotype.Component;

/**
 *
 * @author developer
 */
@Component
public class SchedulerDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public void persist(MScheduler scheduler) {
        em.persist(scheduler);
    }

    public List<MScheduler> findByTypeId(Integer id) {
        return em.createQuery("SELECT p FROM MScheduler p where p.typeId.id = :id").setParameter("id", id).getResultList();
    }
}
