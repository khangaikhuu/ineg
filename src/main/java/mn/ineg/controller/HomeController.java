/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String lawList(Model model){
        return "index";
    }
    
}