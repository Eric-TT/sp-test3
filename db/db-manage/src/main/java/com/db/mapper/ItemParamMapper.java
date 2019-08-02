package com.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.pojo.ItemParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemParamMapper extends BaseMapper<ItemParam> {

	@Select("select * from db_item_param order by updated desc limit #{start},#{rows}")
	List<ItemParam> findItemParamByPage(Integer start, Integer rows);
	


}
