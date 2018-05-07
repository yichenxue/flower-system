package com.flower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yumaoying on 2018/5/7.
 */
@Controller
@RequestMapping({"/goods"})
public class GoodsController {

    @RequestMapping({"/findById"})
    public String index() {
        return "flowerInfo";
    }
}
