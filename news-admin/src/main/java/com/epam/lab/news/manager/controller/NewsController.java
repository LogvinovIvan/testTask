package com.epam.lab.news.manager.controller;

import com.epam.lab.news.manager.entity.News;
import com.epam.lab.news.manager.service.FullNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan_Lohvinau on 10/28/2016.
 */
@Controller
@RequestMapping("/admin")
public class NewsController {
    @Autowired
    private FullNewsService fullNewsService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @ResponseBody
    public List<News> showNews() {
        News news = new News();
        news.setId(1l);
        news.setMainTitle("asdasdasd");
        news.setShortTitle("asdasdsdewfdfaf");
        news.setMainPhoto("assas.png");
        news.setNewsText("aaaaaaaaaaaaaaaaaaaasssssssssssssssssssssccccccc");
        List<News> list = new ArrayList<News>();
        list.add(news);
        return list;
    }

    @RequestMapping(value = "add", method = RequestMethod.PUT,
            headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addNews(@RequestBody News news){
        System.out.print(news);
    }


    public void setFullNewsService(FullNewsService fullNewsService) {
        this.fullNewsService = fullNewsService;
    }
}
