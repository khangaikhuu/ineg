/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.formatter;

import java.beans.PropertyEditorSupport;
import mn.ineg.model.MLawType;
import mn.ineg.service.LawTypeRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author developer
 */
@Component
public class LawTypeEditor extends PropertyEditorSupport {
     
    // Do not use @Autowired.
    // Use dependency injection in class constructor.
    private final LawTypeRepository lawTypeService;
     
    public LawTypeEditor() {
        this.lawTypeService = null;
    }
     
    //This constructor will be used to inject the categoryService.
    public LawTypeEditor(LawTypeRepository lawTypeService) {
        this.lawTypeService = lawTypeService;
    }
     
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // Find a category by its categoryId from text
        MLawType lawType = lawTypeService.findOne(Integer.parseInt(text));
        setValue(lawType);
    }   
}