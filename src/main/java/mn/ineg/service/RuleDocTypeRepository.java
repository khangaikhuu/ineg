/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import mn.ineg.model.MRegDocTypeTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author developer
 */
@Repository
public interface RuleDocTypeRepository extends CrudRepository<MRegDocTypeTypes, Integer>{
    //public MRegDocTypeTypes findByTypeId(Integer id);
}
