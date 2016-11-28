/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.model.MScheduleType;
import mn.ineg.service.ScheduleTypeCrudRepository;
import mn.ineg.service.ScheduleTypeRestRepository;
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
@RequestMapping("/scheduletype")
public class ScheduleTypeController {

    @Autowired
    private ScheduleTypeRestRepository schTypeRestRepo;

    @Autowired
    private ScheduleTypeCrudRepository schTypeCrudRepo;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String scheduleTypeList(Model model) {
        model.addAttribute("scheduleTypes", schTypeRestRepo.findAll());
        return "schedule/scheduleType";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/new")
    public ModelAndView scheduleTypeAddNew() {
        ModelAndView view = new ModelAndView("schedule/scheduleTypeAdd");
        MScheduleType scheduleType = new MScheduleType();
        view.addObject("scheduleType", scheduleType);
        return view;
    }

    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView scheduleTypeEdit(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("schedule/scheduleTypeEdit");
        MScheduleType type = schTypeRestRepo.findOne(id);
        view.addObject("scheduleType", type);
        return view;
    }

    /**
     *
     * @param scheduleName
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView scheduleTypeSave(
            @RequestParam("schedule_id") String scheduleId,
            @RequestParam("schedule_name") String scheduleName,
            @RequestParam("action") String action) {
        ModelAndView view = new ModelAndView("redirect:/scheduletype/list");
        System.out.println("Schedule ID : " + scheduleId);
        if (action.equals("save")) {
            if (scheduleId.isEmpty()) {
                MScheduleType scheduleType = new MScheduleType();
                scheduleType.setName(scheduleName);
                schTypeCrudRepo.save(scheduleType);
            } else {
                MScheduleType scheduleType = schTypeRestRepo.findOne(Integer.parseInt(scheduleId));
                scheduleType.setName(scheduleName);
                schTypeCrudRepo.save(scheduleType);
            }
        }
        if(action.equals("cancel")){
            System.out.println("schedule type save canceled");
        }
        return view;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping("/delete/{id}")
    public ModelAndView scheduleTypeDelete(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:/scheduletype/list");
        schTypeCrudRepo.delete(id);
        return view;
    }

}
