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
import java.util.UUID;
import mn.ineg.dao.SchedulerDao;
import mn.ineg.main.Common;
import mn.ineg.model.MScheduler;
import mn.ineg.service.ScheduleCrudRepository;
import mn.ineg.service.ScheduleTypeRestRepository;
import mn.ineg.service.SchedulerRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/schedule")
public class SchedulerController {

    
    @Autowired
    private ScheduleTypeRestRepository schTypeRestRepo;

    @Autowired
    private ScheduleCrudRepository schCrudRepo;

    @Autowired
    private SchedulerRestRepository schRestRepo;
    
    @Autowired
    private SchedulerDao schedulerDao;

    @RequestMapping("/listup")
    public String listUpSchedule(Model model) {
        model.addAttribute("scheduler", schedulerDao.findByTypeId(1));
        return "schedule/scheduleUpAir";
    }

    @RequestMapping("/listdown")
    public String listDownSchedule(Model model) {
        model.addAttribute("scheduler", schedulerDao.findByTypeId(2));
        return "schedule/scheduleDownAir";
    }

    @RequestMapping("/listplan")
    public String listPlan(Model model) {
        model.addAttribute("scheduler", schedulerDao.findByTypeId(3));
        return "schedule/schedulePlan";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newSchedule() {
        ModelAndView view = new ModelAndView("schedule/scheduleAdd");
        view.addObject("scheduler", new MScheduler());
        view.addObject("scheduleTypes", schTypeRestRepo.findAll());
        return view;
    }

    /**
     * 
     * @param schedule_name
     * @param schedule_type_id
     * @param file
     * @param schedule_date
     * @param action
     * @return
     * @throws IOException
     * @throws ParseException 
     */
    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public ModelAndView saveSchedule(
            @RequestParam("schedule_name") String schedule_name,
            @RequestParam("schedule_type_id") String schedule_type_id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("schedule_date") String schedule_date,
            @RequestParam("action") String action) throws IOException, ParseException {

        Integer sch_type_id = schTypeRestRepo.findOne(Integer.parseInt(schedule_type_id)).getId();
        ModelAndView view;
        switch (sch_type_id) {
            case 1:
                view = new ModelAndView("redirect:/schedule/listup");
                break;
            case 2:
                view = new ModelAndView("redirect:/schedule/listdown");
                break;
            case 3:
                view = new ModelAndView("redirect:/schedule/listplan");
                break;
            default:
                view = new ModelAndView("redirect:/schedule/listup");
                break;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        System.out.println("File : " + file.getName());
        String uploadDir = "/schedule";
        String filePath = Common.ROOT_FOLDER + uploadDir;
        String uploadFileName = "up" + UUID.randomUUID().toString() + ".pdf";
        String schedule_file_path = uploadDir + "/" + uploadFileName;
        File destination = new File(filePath, uploadFileName);
        if (!file.isEmpty()) {
            file.transferTo(destination);
        }
        if (action.equals("save")) {
            MScheduler schedule = new MScheduler();
            schedule.setName(schedule_name);
            formatter.parse(schedule_date);
            Date date = formatter.parse(schedule_date);
            schedule.setDate(date);
            schedule.setFilePath(schedule_file_path);
            schedule.setTypeId(schTypeRestRepo.findOne(Integer.parseInt(schedule_type_id)));
            schCrudRepo.save(schedule);
            System.out.println("Schedule Type : " + schTypeRestRepo.findOne(Integer.parseInt(schedule_type_id)));
            System.out.println("Schedule Added");
        }
        if (action.equals("cancel")) {
            System.out.println("Schedule Canceled");
        }
        return view;
    }

}
