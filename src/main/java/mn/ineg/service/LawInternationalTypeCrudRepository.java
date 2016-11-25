/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import mn.ineg.model.MLawTypeInternational;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author developer
 */
public interface LawInternationalTypeCrudRepository extends CrudRepository<MLawTypeInternational, Integer>{
    
}
