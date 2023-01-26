package org.dog.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:08
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chunk {
    private Integer id;
    private String md5;
    private Integer chunkIndex;

    public Chunk(String md5, Integer chunkIndex) {
        this.md5 = md5;
        this.chunkIndex = chunkIndex;
    }
}
