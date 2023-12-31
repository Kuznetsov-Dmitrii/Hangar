package com.example.hangar.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController implements ErrorController{
//    private final static String PATH = "/error";
//    @RequestMapping(PATH)
//    @ResponseBody
//    public String getErrorPath() {
//        // TODO Auto-generated method stub
//        return "No Mapping Found";
//    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();

        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            modelAndView.setViewName("error/error-404");
            //modelAndView.setViewName("redirect:/");
        }
        else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            modelAndView.setViewName("error/error-403");
        }
        else if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            modelAndView.setViewName("error/error-400");
        }
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            modelAndView.setViewName("error/error-500");
        }
        else {
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }


    @ResponseBody
    public String getErrorPath() {
        return "/error";
    }

}