/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import mn.ineg.model.MLawInternational;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author developer
 */
@RepositoryRestResource(collectionResourceRel = "data", path = "lawinternational")
public interface LawInternationalRestRepository extends PagingAndSortingRepository<MLawInternational, Integer>{
    
}
