/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.model.MRegDocTypeTypes;
import mn.ineg.service.RuleDocTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author developer
 */
@Controller
public class RuleDocTypesController {

    @Autowired
    private RuleDocTypeRepository ruleRepository;

    /**
     * 
     */
    @RequestMapping(value = "/docrules/new", method = RequestMethod.POST)
    public String createDocRules(Model model){
        model.addAttribute("doctypes", new MRegDocTypeTypes());
        return "doctypesform";
    }

    /**
     * List of document types
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/docrules", method = RequestMethod.GET)
    public String developersList(Model model) {
        model.addAttribute("docrules", ruleRepository.findAll());
        return "docrules/docrules";
    }

    @RequestMapping("/docrules/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", ruleRepository.findOne(id));
        return "productform";
    }

    public RuleDocTypeRepository getRuleRepository() {
        return ruleRepository;
    }

    public void setRuleDocTypeRepository(RuleDocTypeRepository rdtRepository) {
        this.ruleRepository = rdtRepository;
    }
}
