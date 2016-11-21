/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.repository;

import mn.ineg.model.MLaw;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author developer
 */
public interface LawCrudRepository extends CrudRepository<MLaw, Integer>
{
}
