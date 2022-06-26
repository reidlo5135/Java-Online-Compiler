package com.jcompile.reidlo.compiler.controller;

import com.jcompile.reidlo.compiler.service.CompileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

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
    public String compile(@RequestParam("beforeCompile") String beforeCompile) {
        log.info("Compile Controller beforeCompile : " + beforeCompile);
        File file = compileDataService.convertFile(beforeCompile);
        String result = compileDataService.compileFile(file);
        log.info("Compile Controller file : " + file);
        log.info("Compile Controller result : " + result);
        return "/compileHome";
    }
}
