/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mn.ineg.model.MLaw;
import mn.ineg.model.MLawType;
import mn.ineg.formatter.LawTypeEditor;
import mn.ineg.service.LawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mn.ineg.repository.LawCrudRepository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import mn.ineg.service.LawRestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String showAllLaws(Model model) throws ParseException {
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

//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView createLawPage() {
//        ModelAndView modelAndView = new ModelAndView("laws/lawEditForm");
//        MLaw lawObject = lawCrudRepository.findOne(id);
//        MLawType lawTypeObject = lawTypeRepository.findOne(lawObject.getLawTypeId().getId());
//        modelAndView.addObject("law", lawObject);
//        modelAndView.addObject("lawTypes", lawTypeObject);
//        return modelAndView;
//    }
    /**
     *
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public ModelAndView createLawPage() {
        System.out.println("Entering in to new method");
        ModelAndView modelAndView = new ModelAndView("laws/lawAddForm");
        Iterable<MLawType> lawTypeObject = lawTypeRepository.findAll();
        Iterable<MLaw> lawObjects = lawCrudRepository.findAll();
        Long size = lawObjects.spliterator().getExactSizeIfKnown();
        MLaw mLaw = new MLaw();
        mLaw.setLawId(size.intValue());
        modelAndView.addObject("law", mLaw);
        modelAndView.addObject("lawTypes", lawTypeObject);
        return modelAndView;
    }

    /**
     * Save law
     *
     * @param law
     * @return
     */
    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public String saveLaw(@ModelAttribute("addForm") MLaw law,
            HttpServletRequest request) throws ParseException {
//    public String saveLaw(HttpServletRequest request,
//            @RequestParam("law_name") String law_name,
//            @RequestParam("law_created_at") String law_created_at,
//            @RequestParam("law_created_by") Integer law_created_by,
//            @RequestParam("law_approved_year") String law_approved_year,
//            @RequestParam("law_changed_year") String law_changed_year,
//            @RequestParam("law_path") String law_path,
//            @RequestParam("law_type_id") String law_type_id_name
//    ) throws ParseException {
        System.out.println("MLaw Object " + law.getLawId());
        System.out.println("Requests " + request.getParameterMap());
        System.out.println("1 : " + request.getParameter("law_type_id"));
        System.out.println("2 : " + request.getParameter("law_approved_year"));
        System.out.println("3 : " + request.getParameter("law_changed_year"));
        System.out.println("4 : " + request.getParameter("law_created_at"));
        System.out.println("5 : " + request.getParameter("law_name"));
        System.out.println("6 : " + request.getParameter("law_path"));
        System.out.println("7 : " + request.getParameter("law_created_by"));
        System.out.println("7 : " + request.getParameter("law_type_id"));
        
//        ModelAndView modelAndView = new ModelAndView("redirect:/law/list");
//        String message = null;
        String action = "save";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date;
        
        if (action.equals("save")) {
            MLaw mLaw = new MLaw();
            mLaw.setLawName(request.getParameter("law_name"));
            MLawType mlawtype = lawTypeRepository.findOne(1);
            String message = null;
            date = formatter.parse(request.getParameter("law_approved_year"));
            mLaw.setApprovedYear(date);
            date = formatter.parse(request.getParameter("law_changed_year"));
            mLaw.setChangedYear(date);
            mLaw.setPath(request.getParameter("path"));
            mLaw.setLawTypeId(mlawtype);
            lawCrudRepository.save(mLaw);
        }
        return "redirect:/law/list";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editLawPage(@PathVariable Integer id) {
        MLaw lawObject;
        MLawType lawTypeObject;
        ModelAndView modelAndView;
        lawObject = lawCrudRepository.findOne(id);
        lawTypeObject = lawTypeRepository.findOne(lawObject.getLawTypeId().getId());
        modelAndView = new ModelAndView("laws/lawForm");
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView editingStrategy(HttpServletRequest request,
            @RequestParam("law_id") Integer id,
            @RequestParam("law_name") String law_name,
            @RequestParam("law_created_at") String law_created_at,
            @RequestParam("law_created_by") Integer law_created_by,
            @RequestParam("law_approved_year") String law_approved_year,
            @RequestParam("law_changed_year") String law_changed_year,
            @RequestParam("law_path") String law_path,
            @RequestParam("law_type_id") String law_type_id_name) throws ParseException {
        
        ModelAndView modelAndView = new ModelAndView("redirect:/law/list");
        String message = null;
        String action = "save";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date;
        if (action.equals("save")) {
            MLaw mlaw;
            if (id == null) {
                mlaw = new MLaw();
            } else {
                mlaw = lawRestRepository.findOne(id);
            }
            MLawType mlawtype = lawTypeRepository.findOne(Integer.parseInt(request.getParameter("law_type_id")));
            mlaw.setLawName(law_name);
            date = formatter.parse(request.getParameter("law_approved_year"));
            mlaw.setApprovedYear(date);
            date = formatter.parse(request.getParameter("law_changed_year"));
            mlaw.setChangedYear(date);
            mlaw.setCreatedBy(law_created_by);
            mlaw.setPath(law_path);
            mlaw.setLawTypeId(mlawtype);
            lawRestRepository.save(mlaw);
            message = "Law " + mlaw.getLawName() + " was successfully edited";
            modelAndView.addObject("message", message);
        }
        if (action.equals("cancel")) {
            message = "Strategy " + law_name + " edit cancelled";
        }
        return modelAndView;
    }

    /**
     * Delete law
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLaw(@PathVariable Integer id) {
        lawRestRepository.delete(id);
        return "redirect:law/list";
    }
    
    private Date simpleDateFormatter(Date date) throws ParseException {
        final String OLD_FORMAT = "yyyy-mm-dd";
        final String NEW_FORMAT = "dd/mm/yyyy";
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        System.out.println("Approved year " + date.toString());
        Date d = sdf.parse(date.toString());
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        return sdf.parse(newDateString);
    }
}
