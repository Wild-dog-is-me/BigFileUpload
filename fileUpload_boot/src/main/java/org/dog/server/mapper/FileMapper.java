package org.dog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dog.server.entity.File;

/**
 * @Author: Odin
 * @Date: 2023/1/26 11:10
 * @Description:
 */

@Mapper
public interface FileMapper extends BaseMapper<File> {

}
