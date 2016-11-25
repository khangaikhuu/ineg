/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import mn.ineg.model.MLawType;
import mn.ineg.repository.LawTypeCrudRepository;
import mn.ineg.service.LawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/lawtype")
public class LawTypeController {

    @Autowired
    private LawTypeRepository lawTypeRepository;

    @Autowired
    private LawTypeCrudRepository lawTypeCrudRepository;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String index(Model model) {
        model.addAttribute("lawTypes", lawTypeRepository.findAll());
        return "lawtypes/list";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editLawTypes(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("lawtypes/editForm");
        MLawType lawType = lawTypeRepository.findOne(id);
        view.addObject("lawType", lawType);
        return view;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView createLawTypes() {
        ModelAndView view = new ModelAndView("lawtypes/addForm");
        MLawType lawType = new MLawType();
        view.addObject("lawType", lawType);
        return view;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/editSave", method = RequestMethod.POST)
    public ModelAndView saveEditLawTypes(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("redirect:/lawtype/list");
        String action = request.getParameter("action");
        if (action.equals("save")) {
            MLawType mLawType = lawTypeRepository.findOne(Integer.parseInt(request.getParameter("law_type_id")));
            mLawType.setName(request.getParameter("law_type_name"));
            lawTypeCrudRepository.save(mLawType);
            System.out.println("Edited and Saved");
        }
        if (action.equals("cancel")) {
            System.out.println("Edit Canceled");
        }
        return view;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/createSave", method = RequestMethod.POST)
    public ModelAndView saveCreateLawTypes(HttpServletRequest request) {
        String action = request.getParameter("action");
        ModelAndView view = new ModelAndView("redirect:/lawtype/list");
        if (action.equals("save")) {
            MLawType mLawType = new MLawType();
            mLawType.setName(request.getParameter("law_type_name"));
            lawTypeCrudRepository.save(mLawType);
            System.out.println("Created and Saved");
        }
        if (action.equals("cancel")) {
            System.out.println("Create Canceled");
        }
        return view;
    }
    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteLawTypes(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:lawtypes/list");
        lawTypeCrudRepository.delete(id);
        return view;
    }

}
