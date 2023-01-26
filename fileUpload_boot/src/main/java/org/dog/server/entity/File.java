package org.dog.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:06
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Integer id;
    private String md5;
    private String name;
    private Long size;

    public File(String name, String md5, Long size) {

        this.md5 = md5;
        this.name = name;
        this.size = size;
    }
}
