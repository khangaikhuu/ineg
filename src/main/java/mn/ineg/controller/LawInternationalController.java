/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import mn.ineg.main.Common;
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
import org.springframework.web.multipart.MultipartFile;
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
    public ModelAndView editInternationalLaw(@PathVariable String id) {
        ModelAndView view = new ModelAndView("lawinternational/lawEdit");
        view.addObject("law", lawIRRepository.findOne(Integer.parseInt(id)));
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
            @RequestParam("law_type_id") String law_type_id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("action") String action) throws IOException {
        ModelAndView view = new ModelAndView("redirect:/lawinternational/list");
        System.out.println("File : " + file.getName());
        String uploadDir = "/externallaw";
        String filePath = Common.ROOT_FOLDER + uploadDir;
        String uploadFileName = "outerlaw" + UUID.randomUUID().toString() + ".pdf";
        String law_file_path = uploadDir + "/" + uploadFileName;
        File destination = new File(filePath, uploadFileName);
        if (!file.isEmpty()) {
            file.transferTo(destination);
        }
        if (action.equals("save")) {
            MLawInternational law = new MLawInternational();
            law.setName(law_name);
            law.setPath(law_file_path);
            law.setLawTypeId(lawITRRepository.findOne(Integer.parseInt(law_type_id)));
            System.out.println("Law Type : " + lawITRRepository.findOne(Integer.parseInt(law_type_id)));
            lawIRCRepository.save(law);
//            view.addObject("law", law);
            System.out.println("International Law Added");
        }
        if (action.equals("cancel")) {
            System.out.println("International Law Canceled");
        }
        return view;
    }
    
    /**
     * Save edited international law
     * @param id
     * @return
     */
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public ModelAndView saveEditInternationalLaw(
            @RequestParam("law_id") String law_id,
            @RequestParam("law_name") String law_name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("law_type_id") String law_type_id,
            @RequestParam("action") String action) throws IOException {
        ModelAndView view = new ModelAndView("redirect:/lawinternational/list");
        String uploadDir = "/externallaw";
        String filePath = Common.ROOT_FOLDER + uploadDir;
        String uploadFileName = "outerlaw" + UUID.randomUUID().toString() + ".pdf";
        String law_file_path = uploadDir + "/" + uploadFileName;
        File destination = new File(filePath, uploadFileName);
        if (!file.isEmpty()) {
            file.transferTo(destination);
        }
        if (action.equals("save")) {
            System.out.println("Law Id " + law_id);
            System.out.println("Law Name " + law_name);
            System.out.println("Law Type Id " + law_type_id);
            System.out.println("Law action " + action);
            MLawInternational law = lawIRRepository.findOne(Integer.parseInt(law_id));
            law.setName(law_name);
            law.setPath(law_file_path);
            law.setLawTypeId(lawITRRepository.findOne(Integer.parseInt(law_type_id)));
            System.out.println("Law Type : " + lawITRRepository.findOne(Integer.parseInt(law_type_id)));
            lawIRCRepository.save(law);
            System.out.println("International Law Edited");
        }
        if (action.equals("cancel")) {
            System.out.println("International Law Canceled");
        }
        return view;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteInternationalLaw(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:/lawinternational/list");
        lawIRCRepository.delete(id);
        return view;
    }

}
