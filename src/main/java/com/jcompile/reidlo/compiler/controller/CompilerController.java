package com.jcompile.reidlo.compiler.controller;

import com.jcompile.reidlo.compiler.service.CompileDataService;
import com.jcompile.reidlo.response.model.ListResult;
import com.jcompile.reidlo.response.service.ResponseLoggingService;
import com.jcompile.reidlo.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CompilerController {

    private static final String className = CompilerController.class.toString();

    private final CompileDataService compileDataService;
    private final ResponseService responseService;
    private final ResponseLoggingService loggingService;

    @GetMapping(value = "/")
    public String compileHome() {
        return "/compileHome";
    }

    @ResponseBody
    @PostMapping(value = "/submit")
    public ResponseEntity<ListResult<String>> compileAfterConvert(@RequestParam("beforeCompile") String beforeCompile) {
        log.info("Compile Controller compileAfterConvert beforeCompile : " + beforeCompile);
        ResponseEntity<ListResult<String>> ett = null;
        try {
            List<String> list = compileDataService.compileAfterConvertFile(beforeCompile);
            log.info("Compile Controller compileAfterConvert list : " + list);

            ListResult<String> result = responseService.getListResult(list);
            loggingService.listResultLogging(className, "compileAfterConvert", result);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ett = new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
            log.info("Compile Controller compileAfterConvert ett : " + ett);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Compile Controller compileAfterConvert Error occurred : " + e.getMessage());
        }
        return ett;
    }
}
