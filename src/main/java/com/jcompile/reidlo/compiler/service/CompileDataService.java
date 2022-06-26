package com.jcompile.reidlo.compiler.service;

import com.jcompile.reidlo.compiler.repo.BaseCompileDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

            String path = "C:\\compileData\\";
            File folder = new File(path);

            if(!folder.exists()) {
                if(folder.mkdirs()) {
                    log.info("Compile SVC convertFile folder : "+ folder + " are generated");
                }
            }

            String fileName = "Test" + ".java";
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
            String[] commands = new String[] {"cd c:\\compileData", "java " + file.getName()};
            ProcessBuilder pb = new ProcessBuilder("cmd");
            pb.redirectErrorStream(false);

            Process p = pb.start();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            for(String cmd : commands) {
                writer.write(cmd + "\n");
                writer.flush();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String outputLine = "";
            String output = "";
            List<String> result = new ArrayList<>();
            while((outputLine = reader.readLine()) != null){
                result.add(outputLine);
                for(int i=0;i<result.size();i++) {
                    log.info("RESULT : " + result.get(i));
                }
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}