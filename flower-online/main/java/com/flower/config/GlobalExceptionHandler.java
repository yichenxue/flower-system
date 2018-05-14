package com.flower.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/***
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("e", e.getMessage());
        mv.addObject("uri", req.getRequestURI());
        mv.setViewName("error");
        e.printStackTrace();
        return mv;
    }
}
