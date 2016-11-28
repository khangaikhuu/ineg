/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.repository.DivisionContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("divisions")
public class DivisionController {
    
    @Autowired
    private DivisionContentRepository divisionRepository;
    /**
     * Show quality and security content
     *
     * @return
     */
    @RequestMapping(value = "/quality", method = RequestMethod.GET)
    public String indexQuality(Model model) {
        model.addAttribute("quality", divisionRepository.findOne(1));
        return "divisions/indexQuality";
    }
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/technology", method = RequestMethod.GET)
    public String indexTechnology(Model model) {
        model.addAttribute("technology", divisionRepository.findOne(2));
        return "divisions/indexTechnology";
    }
    
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/humanresource", method = RequestMethod.GET)
    public String indexHumanResource(Model model) {
        model.addAttribute("humanresource", divisionRepository.findOne(9));
        return "divisions/indexHumanResource";
    }
    
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/regional", method = RequestMethod.GET)
    public String indexRegional(Model model) {
        model.addAttribute("regional", divisionRepository.findOne(4));
        return "divisions/indexRegional";
    }
    
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String indexSearch(Model model) {
        model.addAttribute("search", divisionRepository.findOne(3));
        return "divisions/indexSearch";
    }
    
    
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/aerodrom", method = RequestMethod.GET)
    public String indexAerodrom(Model model) {
        model.addAttribute("aerodrom", divisionRepository.findOne(7));
        return "divisions/indexAerodrom";
    }
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = "/flightinfo", method = RequestMethod.GET)
    public String indexFlightInfo(Model model) {
        model.addAttribute("flightinfo", divisionRepository.findOne(6));
        return "divisions/indexFlightInfo";
    }
    
    /**
     * Planning
     * @param model
     * @return 
     */
    @RequestMapping(value = "/planning", method = RequestMethod.GET)
    public String indexPlanning(Model model) {
        model.addAttribute("planning", divisionRepository.findOne(8));
        return "divisions/indexPlanning";
    }
    
    /**
     * Stydying
     * @param model
     * @return 
     */
    @RequestMapping(value = "/studying", method = RequestMethod.GET)
    public String indexStudying(Model model) {
        model.addAttribute("studying", divisionRepository.findOne(5));
        return "divisions/indexStudying";
    }
    
    
    
}
