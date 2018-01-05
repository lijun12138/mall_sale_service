package com.atguigu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;

public interface AttrMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_list(Integer calss_2);

	void insert_attr_value(@Param("attr_value_list") List<T_MALL_VALUE> attr_value_list,@Param("attr_id") Integer attr_id);

	void insert_attr(OBJECT_T_MALL_ATTR attr);

}
