/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import mn.ineg.model.MRegulationDocumentGovType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author developer
 */
public interface RegulationGovernmentTypeRestRepository extends PagingAndSortingRepository<MRegulationDocumentGovType, Integer>{
    
}
