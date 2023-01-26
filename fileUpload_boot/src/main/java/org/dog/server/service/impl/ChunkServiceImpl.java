package org.dog.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.server.entity.Chunk;
import org.dog.server.mapper.ChunkMapper;
import org.dog.server.service.ChunkService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:16
 * @Description:
 */

@Service
public class ChunkServiceImpl extends ServiceImpl<ChunkMapper, Chunk> implements ChunkService {

    @Resource
    private ChunkMapper chunkMapper;

    @Override
    public List<Integer> selectChunkListByMd5(String md5) {
        QueryWrapper<Chunk> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Chunk> chunkList = chunkMapper.selectList(queryWrapper);
        List<Integer> indexList = new ArrayList<>();
        for (Chunk chunk : chunkList) {
            indexList.add(chunk.getChunkIndex());
        }
        return indexList;
    }

    @Override
    public Integer saveChunk(MultipartFile chunk, String md5, Integer index, Long chunkSize, String resultFileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(resultFileName, "rw")) {

            // 偏移量
            long offset = chunkSize * (index - 1);
            // 定位到该分片的偏移量
            randomAccessFile.seek(offset);
            randomAccessFile.write(chunk.getBytes());
            Chunk chunkPo = new Chunk(md5, index);
            return chunkMapper.insert(chunkPo);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void deleteChunkByMd5(String md5) {
        QueryWrapper<Chunk> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        chunkMapper.delete(queryWrapper);
    }

    @Override
    public byte[] getChunk(Integer index, Integer chunkSize, String resultFileName,long offset) {
        java.io.File resultFile = new File(resultFileName);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(resultFileName, "r")) {
            // 定位到该分片的偏移量
            randomAccessFile.seek(offset);
            //读取
            byte[] buffer = new byte[chunkSize];
            randomAccessFile.read(buffer);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
