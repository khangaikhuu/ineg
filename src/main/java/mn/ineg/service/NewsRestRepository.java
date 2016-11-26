/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.service;

import java.io.Serializable;
import mn.ineg.model.MNews;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author developer
 */
public interface NewsRestRepository extends PagingAndSortingRepository<MNews, Integer> {
    
}
