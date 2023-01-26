package org.dog.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.dog.server.service.ChunkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/1/26 15:08
 * @Description:
 */

@Controller
@CrossOrigin
@Slf4j
public class DownloadController {

    @Value("${file.path}")
    private String filePath;

    @Resource
    private ChunkService chunkService;

    @PostMapping("/download")
    public void download(@RequestParam("md5") String md5,
                         @RequestParam("fileName") String fileName,
                         @RequestParam("chunkSize") Integer chunkSize,
                         @RequestParam("chunkTotal") Integer chunkTotal,
                         @RequestParam("index") Integer index,
                         HttpServletResponse response) {
        String[] splits = fileName.split("\\.");
        String type = splits[splits.length - 1];
        String resultFileName = filePath + md5 + "." + type;

        File resultFile = new File(resultFileName);

        long offset = (long) chunkSize * (index - 1);
        if (Objects.equals(index, chunkTotal)) {
            offset = resultFile.length() - chunkSize;
        }
        byte[] chunk = chunkService.getChunk(index, chunkSize, resultFileName, offset);


        log.info("下载文件分片" + resultFileName + "," + index + "," + chunkSize + "," + chunk.length + "," + offset);
//        response.addHeader("Access-Control-Allow-Origin","Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.addHeader("Content-Length", "" + (chunk.length));
        response.setHeader("filename", fileName);


        response.setContentType("application/octet-stream");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(chunk);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

