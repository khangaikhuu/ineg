/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/introduction")
public class IntroductionController {
    
    @RequestMapping(value = "/index")
    public String index(Model model){
        return "introduction/index";
    }
    
}
