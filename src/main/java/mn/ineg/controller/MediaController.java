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
@RequestMapping("/media")
public class MediaController {
    
    @RequestMapping("/images")
    public String imagesIndex(Model model){
        return "media/images";
    }
    
    @RequestMapping("/videos")
    public String videosIndex(Model model){
        return "media/videos";
    }
    
    @RequestMapping("/others")
    public String othersIndex(Model model){
        return "media/others";
    }
    
}
