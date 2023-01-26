package org.dog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dog.server.entity.File;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:15
 * @Description:
 */
public interface FileService extends IService<File> {
    Boolean selectFileByMd5(String md5);

    Integer addFile(File file);
}
