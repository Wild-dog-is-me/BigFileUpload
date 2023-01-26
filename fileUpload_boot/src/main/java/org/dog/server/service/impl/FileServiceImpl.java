package org.dog.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dog.server.entity.File;
import org.dog.server.mapper.FileMapper;
import org.dog.server.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:17
 * @Description:
 */

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public Boolean selectFileByMd5(String md5) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<File> files = fileMapper.selectList(queryWrapper);
        log.info("文件是否存在:{}", files == null);
        return files == null;
    }

    @Override
    public Integer addFile(File file) {
        return fileMapper.insert(file);
    }
}
