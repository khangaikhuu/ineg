/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import mn.ineg.model.MScheduler;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author developer
 */
public interface ScheduleCrudRepository extends CrudRepository<MScheduler, Integer>{
    
}
