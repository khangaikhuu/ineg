/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import javax.servlet.http.HttpServletRequest;
import mn.ineg.model.MRegDocType;
import mn.ineg.model.MRegDocTypeTypes;
import mn.ineg.service.RuleDocTypeRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mn.ineg.service.RuleDocTypeTypeCrudRepository;
import mn.ineg.service.RuleDocTypeTypeRestRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/docrules")
public class RuleDocTypesController {

    @Autowired
    private RuleDocTypeTypeCrudRepository ruleRepository;

    @Autowired
    private RuleDocTypeTypeRestRepository ruleDocTypeTypeRestRepo;
    @Autowired
    private RuleDocTypeRestRepository ruleDocTypeRepo;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String listDocRules(Model model) {
        model.addAttribute("doctypetypes", ruleDocTypeTypeRestRepo.findAll());
        model.addAttribute("doctypes", ruleDocTypeRepo.findAll());
        System.out.println("Document Rules " + ruleDocTypeRepo.findAll());
        return "regulations/RuleTypeType";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView addRegDocTypeTypes() {
        ModelAndView view = new ModelAndView("regulations/RuleTypeTypeAdd");
        view.addObject("doctypetypes", new MRegDocTypeTypes());
        view.addObject("doctypes", ruleDocTypeRepo.findAll());
        return view;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGovernmentType(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("regulations/RuleTypeTypeEdit");
        view.addObject("doctypetypes", ruleDocTypeTypeRestRepo.findOne(id));
        view.addObject("doctypes", ruleDocTypeRepo.findAll());
        return view;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGovernmentType(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:/docrules/list");
        ruleRepository.delete(id);
        return view;
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public ModelAndView saveGovernmentType(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("redirect:/docrules/list");
        System.out.println("Action : " + request.getParameter("doc_name"));
        System.out.println("Document Name : " + request.getParameter("doc_name"));
        System.out.println("Document Type Id : " + request.getParameter("doc_type_id"));
        String action = request.getParameter("action");
        if (action.equals("save")) {
            MRegDocTypeTypes docTypeTypes = new MRegDocTypeTypes();
            MRegDocType docTypes = ruleDocTypeRepo.findOne(Integer.parseInt(request.getParameter("doc_type_id")));
            docTypeTypes.setName(request.getParameter("doc_name"));
            docTypeTypes.setDocTypeId(docTypes);
            ruleRepository.save(docTypeTypes);
        }
        if (action.equals("cancel")) {
            System.out.println("Saving is canceled");
        }
        return view;
    }

    /**
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public ModelAndView saveEditGovernmentType(
            @RequestParam("doc_type_type_id") Integer doc_type_type_id,
            @RequestParam("doc_type_id") Integer doc_type_id,
            @RequestParam("doc_type_name") String name,
            @RequestParam("action") String action) {
        ModelAndView view = new ModelAndView("redirect:/docrules/list");
        if (action.equals("save")) {
            MRegDocType docType = ruleDocTypeRepo.findOne(doc_type_id);
            MRegDocTypeTypes docTypeTypes = ruleRepository.findOne(doc_type_type_id);
            docTypeTypes.setName(name);
            docTypeTypes.setDocTypeId(docType);
            ruleRepository.save(docTypeTypes);
        }
        if(action.equals("cancel")){
            System.out.println("Save Edit canceled");
        }
        return view;
    }

}
