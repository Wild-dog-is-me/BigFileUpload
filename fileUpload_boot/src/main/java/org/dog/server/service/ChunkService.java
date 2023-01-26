package org.dog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dog.server.entity.Chunk;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:16
 * @Description:
 */
public interface ChunkService extends IService<Chunk> {

    List<Integer> selectChunkListByMd5(String md5);

    Integer saveChunk(MultipartFile chunk, String md5, Integer index, Long chunkSize, String resultFileName);

    void deleteChunkByMd5(String md5);

    byte[] getChunk(Integer index, Integer chunkSize, String resultFileName, long offset);
}
