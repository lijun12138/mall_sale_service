package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_DETAIL;
import com.atguigu.bean.T_MALL_SKU;

public interface SearchMapper {

	List<OBJECT_T_MALL_SKU> select_sku(Integer class_2_id);

	List<OBJECT_T_MALL_SKU> select_sku_by_attr(Map<Object, Object> param);

	OBJECT_T_MALL_SKU_DETAIL selct_sku_by_sku_shp(Map<Object, Object> param);

	List<T_MALL_SKU> select_sku_by_spu_id(Integer spu_id);



}
