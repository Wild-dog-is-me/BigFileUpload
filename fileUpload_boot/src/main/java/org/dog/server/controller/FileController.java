package org.dog.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dog.server.common.Result;
import org.dog.server.entity.File;
import org.dog.server.service.ChunkService;
import org.dog.server.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:18
 * @Description:
 */

@RestController
@CrossOrigin
@Slf4j
public class FileController {

    @Value("${file.path}")
    private String filePath;

    @Resource
    private FileService fileService;

    @Resource
    private ChunkService chunkService;

    @GetMapping("/check")
    public Result checkFile(@RequestParam("md5") String md5) {
        log.info("检查md5:{}", md5);
        // 检查是否有完整文件
        Boolean isUploaded = fileService.selectFileByMd5(md5);
        Map<String, Object> data = new HashMap<>();
        data.put("isUploaded", isUploaded);
        //如果有，就返回秒传
        if (isUploaded) {
            return new Result(201, "文件已经秒传", data);
        }

        //如果没有，就查找分片信息，并返回给前端
        List<Integer> chunkList = chunkService.selectChunkListByMd5(md5);
        data.put("chunkList", chunkList);
        return new Result(201, "", data);
    }

    @PostMapping("/upload/chunk")
    public Result uploadFile(@RequestParam("chunk")MultipartFile chunk,
                             @RequestParam("md5")String md5,
                             @RequestParam("index")Integer index,
                             @RequestParam("chunkTotal")Integer chunkTotal,
                             @RequestParam("fileSize")Long fileSize,
                             @RequestParam("fileName")String fileName,
                             @RequestParam("chunkSize")Long chunkSize) {
        String[] splits = fileName.split("\\.");
        String type = splits[splits.length - 1];
//        log.info("文件类型:{}", type);
        String resultFileName = filePath + md5 + "." + type;

        chunkService.saveChunk(chunk, md5, index, chunkSize, resultFileName);
        if (Objects.equals(index, chunkTotal)) {
            File file = new File(fileName, md5, fileSize);
            fileService.addFile(file);
            chunkService.deleteChunkByMd5(md5);
            return new Result(200, "文件上传成功", index);
        } else {
            return new Result(201, "分片上传成功", index);
        }
    }

    @GetMapping("/fileList")
    public Result getFileList() {
        log.info("查询文件列表");
        List<File> fileList = fileService.getBaseMapper().selectList(null);
        return new Result(201, "文件列表查询传成功", fileList);
    }
}
