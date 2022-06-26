package com.jcompile.reidlo.compiler.service;

import com.jcompile.reidlo.compiler.repo.BaseCompileDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class CompileDataService {

    private final BaseCompileDataRepository baseCompileDataRepository;

    @Transactional
    public File convertFile(String beforeCompile) {
        log.info("Compile SVC beforeCompile : " + beforeCompile);
        try {
            Date nowTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
            String formatTime = simpleDateFormat.format(nowTime);

//            String path = "C:\\compileData\\";
            String path = "C:\\Users\\user\\";
            File folder = new File(path);

            if(!folder.exists()) {
                if(folder.mkdirs()) {
                    log.info("Compile SVC convertFile folder : "+ folder + " are generated");
                }
            }

            String fileName = "test" + ".jar";
            File file = new File(path, fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                writer.write(beforeCompile);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Compile SVC compileFile error occurred : " + e.getMessage());
            }

            return file;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Compile SVC convertFile error occurred : " + e.getMessage());
        }
        return null;
    }

    public String compileFile(File file) {
        try {
//            String cdPathCmd = "cd " + absolPath;
            String javaCmd = "java " + file.getName();

            Runtime runtime = Runtime.getRuntime();
//            Process process = runtime.exec("cmd /c" + cdPathCmd + "\n" + javaCmd);
            Process process = runtime.exec("cmd /c" + javaCmd);
//            Process process = runtime.exec("cmd /c" + "ipconfig");

            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line = bufferedReader.readLine()) != null){
                log.info("compileFile LINE : " + line.equals("") == null);
                sb.append(line);
                sb.append("\n");
            }
            int execTime = process.waitFor();
            log.info("Compile SVC compileFile execTime : " + execTime);

            String result = sb.toString();
            log.info("Compile SVC compileFile process : " + process);
            log.info("Compile SVC compileFile inputStreamReader : " + inputStreamReader.read());
            log.info("Compile SVC compileFile bufferedReader : " + bufferedReader.readLine());
            log.info("Compile SVC compileFile sBuffer : " + sb);
            log.info("Compile SVC compileFile result : " + result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}