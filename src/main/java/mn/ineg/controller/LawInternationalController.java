/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.model.MLawInternational;
import mn.ineg.model.MLawTypeInternational;
import mn.ineg.service.LawInternationalCrudRepository;
import mn.ineg.service.LawInternationalRestRepository;
import mn.ineg.service.LawInternationalTypeRestRepository;
import mn.ineg.service.LawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/lawinternational")
public class LawInternationalController {

    @Autowired
    private LawInternationalRestRepository lawIRRepository;

    @Autowired
    private LawInternationalCrudRepository lawIRCRepository;
    
    @Autowired
    private LawInternationalTypeRestRepository lawITRRepository;

    //list all existing internation laws
    @RequestMapping("/list")
    public String listInternationalLaw(Model model) {
        model.addAttribute("laws", lawIRRepository.findAll());
        model.addAttribute("lawTypes", lawIRRepository.findAll());
        return "lawinternational/list";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/new")
    public ModelAndView newInternationalLaw(Model model) {
        ModelAndView view = new ModelAndView("lawinternational/lawAdd");
        view.addObject("law", new MLawInternational());
        view.addObject("lawTypes", lawITRRepository.findAll());
        return view;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editInternationalLaw(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("lawinternational/lawEdit");
        view.addObject("law", lawIRRepository.findOne(id));
        view.addObject("lawTypes", lawITRRepository.findAll());
        return view;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public ModelAndView saveInternationalLaw(
            @RequestParam("law_name") String law_name,
            @RequestParam("law_path") String law_path,
            @RequestParam("law_type_id") String law_type_id,
            @RequestParam("action") String action) {
        ModelAndView view = new ModelAndView("redirect:/lawinternational/list");
        if (action.equals("save")) {
            MLawInternational law = new MLawInternational();
            law.setName(law_name);
            law.setPath(law_path);
            law.setLawTypeId(lawITRRepository.findOne(Integer.parseInt(law_type_id)));
            System.out.println("Law Type : " + lawITRRepository.findOne(Integer.parseInt(law_type_id)));
            lawIRCRepository.save(law);
            
            view.addObject("law", law);
            System.out.println("International Law Added");
        }
        if (action.equals("cancel")) {
            System.out.println("International Law Canceled");
        }
        return view;
    }

}
