package com.flower.controller;

import com.flower.entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
