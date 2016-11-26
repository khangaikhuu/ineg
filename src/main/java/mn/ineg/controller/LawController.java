/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
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
import mn.ineg.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/law")
public class LawController {

    private final StorageService storageService;

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

    @Autowired
    public LawController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Get list of all MLawTypes
     *
     * @return
     */
    @ModelAttribute("allLawTypes")
    public List<MLawType> populateVarieties() {
        Iterable<MLawType> lawTypes = lawTypeRepository.findAll();
        List<MLawType> lawTypeList = new LinkedList<>();
        for (MLawType i : lawTypes) {
            lawTypeList.add(i);
        }
        return lawTypeList;
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
        System.out.println("MLaw Object " + law.getLawId());
        System.out.println("Requests " + request.getParameterMap());
        System.out.println("1 : " + request.getParameter("law_type_id"));
        System.out.println("2 : " + request.getParameter("law_approved_year"));
        System.out.println("3 : " + request.getParameter("law_changed_year"));
        System.out.println("4 : " + request.getParameter("law_created_at"));
        System.out.println("5 : " + request.getParameter("law_name"));
        System.out.println("6 : " + request.getParameter("law_path"));
        System.out.println("7 : " + request.getParameter("law_created_by"));
        System.out.println("8 : " + request.getParameter("law_type_id"));

        String action = "save";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date date;

        if (action.equals("save")) {
            MLaw mLaw = new MLaw();
            mLaw.setLawName(request.getParameter("law_name"));
            MLawType mlawtype = lawTypeRepository.findOne(1);
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
        Iterable<MLawType> lawTypeObjects;
        ModelAndView modelAndView;
        lawObject = lawCrudRepository.findOne(id);
        lawTypeObject = lawTypeRepository.findOne(lawObject.getLawTypeId().getId());
        lawTypeObjects = lawTypeRepository.findAll();
        modelAndView = new ModelAndView("laws/lawForm");
        modelAndView.addObject("law", lawObject);
        modelAndView.addObject("lawTypes", lawTypeObjects);
        return modelAndView;
    }

    /**
     *
     * @param mLaw
     * @param action
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    //public ModelAndView editingStrategy(HttpServletRequest request,
    public ModelAndView editingStrategy(
            HttpServletRequest request,
            @RequestParam("law_id") Integer id,
            @RequestParam("law_name") String law_name,
            @RequestParam("law_approved_year") String law_approved_year,
            @RequestParam("law_changed_year") String law_changed_year,
            @RequestParam("action") String action_parameter,
            //            @RequestParam("law_path") String law_path,
            @RequestParam("law_type_id") String law_type_id_name,
            @RequestParam("file") MultipartFile file) throws ParseException, IOException {
        //view
        ModelAndView modelAndView = new ModelAndView("redirect:/law/list");
        Date date;
        System.out.println("File : " + file.getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String rootFolder = "/Users/developer/uploadingdir";
        String uploadDir = "/internallaw";
        String filePath = rootFolder + uploadDir;
        String uploadFileName = "law" + UUID.randomUUID().toString() + ".pdf";
        String law_file_path = uploadDir + "/" + uploadFileName;
        File destination = new File(filePath, uploadFileName);
        String fileName = file.getOriginalFilename();
        System.out.println("File Content " + file.getContentType());
        System.out.println("File Name " + fileName);
        if (!file.isEmpty()) {
            file.transferTo(destination);
        }
        //creation of law object
        if (action_parameter.equals("save")) {
            System.out.println("Law ID : " + id);
            System.out.println("Law Type ID : " + law_type_id_name);
            System.out.println("Law Type Id Type : " + law_type_id_name.getClass());
            MLaw mlaw = lawRestRepository.findOne(id);
            MLawType mlawtype = lawTypeRepository.findOne(Integer.parseInt(law_type_id_name));
            System.out.println("Law Types : " + mlawtype.getName());
            mlaw.setLawName(law_name);
            date = formatter.parse(law_approved_year);
            mlaw.setApprovedYear(date);
            date = formatter.parse(law_changed_year);
            mlaw.setChangedYear(date);
            Date createdDate = new Date();
            mlaw.setCreatedAt(createdDate);
            mlaw.setCreatedBy(1);
            mlaw.setPath(law_file_path);
            mlaw.setLawTypeId(mlawtype);
            lawRestRepository.save(mlaw);
            String message = "Law " + mlaw.getLawName() + " was successfully edited";
            modelAndView.addObject("message", message);
        }
        if (action_parameter.equals("cancel")) {
            String message = "Law Save " + law_name + " cancelled";
            modelAndView.addObject("message", message);
        }
        return modelAndView;
    }

//    @RequestMapping(value="/upload/{}", method = RequestMethod.GET)
//    public void 
    /**
     * Delete law
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteLaw(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:/law/list");
        lawCrudRepository.delete(id);
        return view;
    }

    /**
     *
     * @param date
     * @return
     * @throws ParseException
     */
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
