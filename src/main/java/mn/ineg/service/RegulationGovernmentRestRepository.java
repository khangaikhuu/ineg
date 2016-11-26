/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import mn.ineg.model.MRegulationDocumentGovernment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author developer
 */
public interface RegulationGovernmentRestRepository extends PagingAndSortingRepository<MRegulationDocumentGovernment, Integer>{
    
}
