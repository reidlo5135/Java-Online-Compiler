package com.jcompile.reidlo.compiler.controller;

import com.jcompile.reidlo.compiler.service.CompileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CompilerController {

    private final CompileDataService compileDataService;

    @GetMapping(value = "/")
    public String compileHome() {
        return "/compileHome";
    }

    @PostMapping(value = "/submit")
    public ModelAndView compileAfterConvert(ModelAndView mav, @RequestParam("beforeCompile") String beforeCompile) {
        log.info("Compile Controller compileAfterConvert beforeCompile : " + beforeCompile);
        try {
            List<String> result = compileDataService.compileAfterConvertFile(beforeCompile);
            log.info("Compile Controller compileAfterConvert result : " + result);
            mav.addObject("result", result);
            mav.setViewName("/compileHome");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Compile Controller compileAfterConvert Error occurred ");
        }
        return mav;
    }
}
