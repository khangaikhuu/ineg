/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.formatter;

import java.text.ParseException;
import java.util.Locale;
import mn.ineg.model.MLawType;
import mn.ineg.service.Formatter;
import mn.ineg.service.LawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author developer
 */
public class LawTypeFormatter implements Formatter<MLawType> {

    @Autowired
    private LawTypeRepository lawTypeService;
    
    /**
     * convert object to string
     * @param object
     * @param locale
     * @return 
     */
    @Override
    public String print(MLawType object, Locale locale) {
        return object.getName();
    }

    /**
     * Convert String to object
     * @param lawTypeId
     * @param locale
     * @return
     * @throws ParseException 
     */
    @Override
    public MLawType parse(String lawTypeId, Locale locale) throws ParseException {
        Integer id = Integer.valueOf(lawTypeId);
        return lawTypeService.findOne(id);
    }
    
}
