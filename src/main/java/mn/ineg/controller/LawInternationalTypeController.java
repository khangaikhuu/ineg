/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.model.MLawTypeInternational;
import mn.ineg.service.LawInternationalTypeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import mn.ineg.service.LawInternationalTypeRestRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/lawtypeinter")
public class LawInternationalTypeController {

    @Autowired
    public LawInternationalTypeRestRepository lawITypeRepository;

    @Autowired
    public LawInternationalTypeCrudRepository lawITypeCrudRepository;

    @RequestMapping("/list")
    public String listTypes(Model model) {
        model.addAttribute("lawTypes", lawITypeRepository.findAll());
        return "lawinternational/listType";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editTypes(@PathVariable Integer id, Model model) {
        model.addAttribute("lawTypes", lawITypeRepository.findOne(id));
        return "lawinternational/lawTypeEdit";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/new")
    public String newTypes(Model model) {
        model.addAttribute("lawTypes", new MLawTypeInternational());
        return "lawinternational/lawTypeAdd";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveType(
            @RequestParam("law_type_id") Integer law_type_id,
            @RequestParam("law_type_name") String law_type_name,
            @RequestParam("action") String action) {
        ModelAndView view = new ModelAndView("redirect:/lawtypeinter/list");
        if (action.equals("save")) {
            if (law_type_id == null) {
                MLawTypeInternational lawType = new MLawTypeInternational();
                lawType.setName(law_type_name);
                lawITypeCrudRepository.save(lawType);
                System.out.println("International Law Added");
            } else {
                MLawTypeInternational lawType = lawITypeCrudRepository.findOne(law_type_id);
                lawType.setName(law_type_name);
                lawITypeCrudRepository.save(lawType);
            }
        }
        if (action.equals("cancel")) {
            System.out.println("International Law Adding Canceled");
        }
        return view;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteType(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:/lawtypeinter/list");
        lawITypeCrudRepository.delete(id);
        System.out.println("International Law Deleted");
        return view;
    }

}
