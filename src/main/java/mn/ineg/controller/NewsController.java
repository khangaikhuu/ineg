/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.util.Date;
import mn.ineg.model.MNews;
import mn.ineg.service.NewsCategoryRestRepository;
import mn.ineg.service.NewsCrudRepository;
import mn.ineg.service.NewsRestRepository;
import mn.ineg.service.StaffRestRepository;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private StaffRestRepository userRepository;

    @Autowired
    private NewsRestRepository newsRestRepository;
    
    @Autowired
    private NewsCrudRepository newsCrudRepository;
    
    @Autowired
    private NewsCategoryRestRepository newsCatRestRepository;
    
    @RequestMapping("/list")
    public String listNews(Model model) {
        //model.addAttribute("news", newsRestRepository.findAll());
        return "news/index";
    }

    /**
     * New news
     * @return 
     */
    @RequestMapping("/new")
    public ModelAndView createNews() {
        ModelAndView view = new ModelAndView("news/addNews");
        view.addObject("news", new MNews());
        return view;
    }

    /**
     * 
     * @param news_title
     * @param news_content
     * @param action
     * @return 
     */
    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public ModelAndView saveNewNews(@RequestParam("news_title") String news_title,
            @RequestParam("content") String news_content,
            @RequestParam("action") String action) {
        ModelAndView view = new ModelAndView("redirect:/news/list");
        if (action.equals("save")) {
            MNews news = new MNews();
            news.setTitle(news_title);
            news.setContent(news_content);
            news.setUserId(userRepository.findOne(1));
            news.setCreatedDate(new Date());
            news.setCategoryId(newsCatRestRepository.findOne(1));
            newsCrudRepository.save(news);
        }
        if (action.equals("cancel")) {
            System.out.println("News Add Canceled");
        }
        return view;
    }
    
    /**
     * Edit news
     * @return 
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editNews(@PathVariable String id) {
        ModelAndView view = new ModelAndView("news/addNews");
        view.addObject("news", newsRestRepository.findOne(Integer.parseInt(id)));
        return view;
    }
    /**
     * 
     * @param news_id
     * @param news_title
     * @param news_content
     * @param action
     * @return 
     */
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public ModelAndView saveEditNews(
            @RequestParam("news_id") Integer news_id,
            @RequestParam("news_title") String news_title,
            @RequestParam("content") String news_content,
            @RequestParam("action") String action) {
        ModelAndView view = new ModelAndView("redirect:/news/list");
        if (action.equals("save")) {
            MNews news = newsRestRepository.findOne(news_id);
            news.setTitle(news_title);
            news.setContent(news_content);
            news.setUserId(userRepository.findOne(1));
            news.setCreatedDate(new Date());
            news.setCategoryId(newsCatRestRepository.findOne(1));
            newsCrudRepository.save(news);
        }
        if (action.equals("cancel")) {
            System.out.println("News Add Canceled");
        }
        return view;
    }
    
    

}
