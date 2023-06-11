package ru.raiffeisen.pfrxml.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping("/")
    public ModelAndView startPage() {
        ModelAndView modelAndView = new ModelAndView("pfrxml");
        return modelAndView;
    }

    @GetMapping("/packs")
    public ModelAndView viewPacks() {
        ModelAndView modelAndView = new ModelAndView("packs");
        return modelAndView;
    }
}

