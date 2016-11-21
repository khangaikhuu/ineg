/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import mn.ineg.model.MLaw;
import mn.ineg.model.MLawType;
import mn.ineg.formatter.LawTypeEditor;
import mn.ineg.service.LawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mn.ineg.repository.LawCrudRepository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import mn.ineg.service.LawRestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/law")
public class LawController {

    static Logger logger = LoggerFactory.getLogger(LawController.class);
    @Autowired
    private LawCrudRepository lawCrudRepository;

    @Autowired
    private LawRestRepository lawRestRepository;

    @Autowired
    private LawTypeRepository lawTypeRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MLawType.class, new LawTypeEditor(lawTypeRepository));
    }

    /**
     * List of laws
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showAllLaws(Model model) {
        model.addAttribute("laws", lawRestRepository.findAll());
        return "laws/laws";
    }

    /**
     * Show law by id
     *
     * @param law
     * @param model
     * @return
     */
    @RequestMapping(value = "law/{id}")
    public String showLaw(@PathVariable Integer id, Model model) {
        model.addAttribute("law", lawRestRepository.findOne(id));
        return "laws/lawDetailed";
    }

    /**
     * Save law
     *
     * @param law
     * @return
     */
    //@RequestMapping(value = "/law/law", method = RequestMethod.POST)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLaw(@RequestParam Map<String, String> allRequestParams,
            @ModelAttribute("law") MLaw law,
            Model model) {
        System.out.println("Entering in to save method");
        System.out.println("Law Name" + law.getLawName());
        System.out.println("Law Type Name" + law.getLawTypeId().getName());
        MLawType lawType = lawTypeRepository.findOne(law.getLawTypeId().getId());
        law.setLawTypeId(lawType);
        lawRestRepository.save(law);
        return "redirect:/law/list";
    }

    /**
     * Creation of Law
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create")
    public String createLaw(Model model) {
        System.out.println("Entering in to create method");
        model.addAttribute("lawTypes", lawTypeRepository.findAll());
        model.addAttribute("law", new MLaw());
        //return "laws/lawAddForm";
        return "laws/lawForm";
    }

    /**
     * Editing the Model
     *
     * @param id
     * @param model
     * @return
     */
//    @RequestMapping(value = "/edit/{id}")
//    public String editLaw(@PathVariable Integer id, Model model) {
//        System.out.println("Entering in to edit method");
//        model.addAttribute("lawTypes", lawTypeRepository.findAll());
//        System.out.println("Edit is beginning with Id = " + id);
//        model.addAttribute("law", lawRestRepository.findOne(id));
//        return "laws/lawEditForm";
//    }
    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editStrategyPage(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView("laws/lawEditForm");
        MLaw lawObject = lawCrudRepository.findOne(id);
        MLawType lawTypeObject = lawTypeRepository.findOne(lawObject.getLawTypeId().getId());
        modelAndView.addObject("law", lawObject);
        modelAndView.addObject("lawTypes", lawTypeObject);
        return modelAndView;
    }

    /**
     *
     * @param mLaw
     * @param action
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editingStrategy(HttpServletRequest request,
            @RequestParam("law_id") Integer id,
            @RequestParam("law_name") String law_name,
            @RequestParam("law_created_at") String law_created_at,
            @RequestParam("law_created_by") Integer law_created_by,
            @RequestParam("law_approved_year") String law_approved_year,
            @RequestParam("law_changed_year") String law_changed_year,
            @RequestParam("law_path") String law_path,
            @RequestParam("law_type_id") String law_type_id_name) {

        ModelAndView modelAndView = new ModelAndView("redirect:/law/list");
        String message = null;
        String action = "save";
        System.out.println("Request " + request.getParameter("law_id"));
        System.out.println("Request " + request.getParameter("law_name"));
        System.out.println("Request " + request.getParameter("law_created_at"));
        System.out.println("Request " + request.getParameter("law_created_by"));
        System.out.println("Request " + request.getParameter("law_approved_year"));
        System.out.println("Request " + request.getParameter("law_changed_year"));
        System.out.println("Request " + request.getParameter("law_path"));
        System.out.println("Request " + request.getParameter("law_type_id"));
        if (action.equals("save")) {
            MLaw mlaw = lawRestRepository.findOne(id);
            MLawType mlawtype = lawTypeRepository.findOne(1);
            System.out.println("Law Id " + mlaw.getLawId());
            mlaw.setLawName(law_name);
//            mlaw.setApprovedYear(new Date(law_approved_year));
//            mlaw.setChangedYear(new Date(law_changed_year));
//            mlaw.setCreatedAt(new Date(law_created_at));
//            mlaw.setCreatedBy(law_created_by);
//            mlaw.setPath(law_path);
//            mlaw.setLawTypeId(mlawtype);
            lawRestRepository.save(mlaw);
//            message = "Law " + mlaw.getLawId() + " was successfully edited";
//            modelAndView.addObject("message", message);
        }
//        if (action.equals("cancel")) {
////            message = "Strategy " + law_name + " edit cancelled";
//        }
        return modelAndView;
    }

    /**
     * Update the model
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update")
    public String updateLaw(@ModelAttribute MLaw mlaw) {
        //model.addAttribute("lawTypes", lawTypeRepository.findAll());
        mlaw.setLawTypeId(mlaw.getLawTypeId());
        lawRestRepository.save(mlaw);
        return "redirect:/laws/laws";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLaw(@PathVariable Integer id) {
        lawRestRepository.delete(id);
        return "redirect:law/list";
    }
}
