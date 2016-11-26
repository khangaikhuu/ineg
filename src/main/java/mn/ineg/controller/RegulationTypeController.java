/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.model.MRegulationDocumentGovType;
import mn.ineg.service.RegulationGovernmentTypeCrudRepository;
import mn.ineg.service.RegulationGovernmentTypeRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/regtype")
public class RegulationTypeController {
    
    @Autowired
    private RegulationGovernmentTypeRestRepository regGovTypeRestRepo;
    @Autowired 
    private RegulationGovernmentTypeCrudRepository regGovTypeCrudRepo;
    
    //Government CRUD
    @RequestMapping("/government")
    public String listGovernment(Model model){
        model.addAttribute("govtypes", regGovTypeRestRepo.findAll());
        return "regulations/typeGovernment";
    }
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/government/new", method = RequestMethod.GET)
    public ModelAndView addGovernmentType(){
        ModelAndView view = new ModelAndView("regulations/typeGovernmentAdd");
        view.addObject("typeGov", new MRegulationDocumentGovType());
        return view;
    }
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping(value = "/government/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGovernmentType(@PathVariable Integer id){
        ModelAndView view = new ModelAndView("regulations/typeGovernmentEdit");
        view.addObject("typeGov", regGovTypeRestRepo.findOne(id));
        return view;
    }
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping(value = "/government/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGovernmentType(@PathVariable Integer id){
        ModelAndView view = new ModelAndView("redirect:/regtype/government");
        regGovTypeCrudRepo.delete(id);
        return view;
    }
    
    /**
     * 
     * @param name
     * @return 
     */
    @RequestMapping(value = "/government/save", method = RequestMethod.POST)
    public ModelAndView saveGovernmentType(
            @RequestParam("govTypeName") String name){
        ModelAndView view = new ModelAndView("redirect:/regtype/government");
        MRegulationDocumentGovType govType = new MRegulationDocumentGovType();
        govType.setName(name);
        regGovTypeCrudRepo.save(govType);
        return view;
    }
    /**
     * 
     * @param id
     * @param name
     * @return 
     */
    @RequestMapping(value = "/government/saveEdit", method = RequestMethod.POST)
    public ModelAndView saveEditGovernmentType(
            @RequestParam("govTypeId") Integer id,
            @RequestParam("govTypeName") String name){
        ModelAndView view = new ModelAndView("redirect:/regtype/government");
        MRegulationDocumentGovType govType = regGovTypeRestRepo.findOne(id);
        govType.setName(name);
        regGovTypeCrudRepo.save(govType);
        return view;
    }
    
    
    
    
    
    //INEG CRUD
    @RequestMapping("/ineg")
    public String listIneg(Model model){
        return "regulations/typeIneg";
    }
    
    //NHUA CURD
    @RequestMapping("/nhua")
    public String listNhua(Model model){
        
        return "regulations/typeNhua";
    }
}
