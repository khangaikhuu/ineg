/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import mn.ineg.model.MHistory;
import mn.ineg.service.HistoryRepository;
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
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Show history
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        int content = historyRepository.findOne(2).getContent().length();
        System.out.println(content);
        model.addAttribute("history", historyRepository.findOne(2));
        return "history/index";
    }
    /**
     * Create new history
     * @param model
     * @return 
     */
    @RequestMapping(value = "/create")
    public String editHistory(Model model) {
        //model.addAttribute("history", historyRepository.findOne(2));
        model.addAttribute("history", new MHistory());
        return "history/historyForm";
    }
    
    /**
     * Edit history by id
     * @param id
     * @param model
     * @return 
     */
    @RequestMapping(value = "/edit/{id}")
    public String updateHistory(@PathVariable Integer id, Model model) {
        model.addAttribute("history", historyRepository.findOne(id));
        return "history/historyForm";
    }
    
    /**
     * Save a history
     * @param history
     * @return 
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveHistory(MHistory history) {
        historyRepository.save(history);
        return "redirect:/history/index";
    }
    
}
