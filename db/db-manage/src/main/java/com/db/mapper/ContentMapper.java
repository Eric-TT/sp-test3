package com.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.pojo.Content;

public interface ContentMapper extends BaseMapper<Content> {

	@Select("select * from db_content order by created limit #{start},#{rows}")
	List<Content> findContent(int start, Integer rows);

}
