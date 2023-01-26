package org.dog.server.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:06
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;
    private String message;
    private Object data;

}
