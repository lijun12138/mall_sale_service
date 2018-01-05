package com.atguigu.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_DETAIL;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SearchMapper;
import com.atguigu.service.SearchService;

@Service
public class SearchServiceImp implements SearchService {

	@Autowired
	private SearchMapper searchMapper;

	@Override
	public List<OBJECT_T_MALL_SKU> query_sku(Integer class_2_id) {

		return searchMapper.select_sku(class_2_id);
	}

	@Override
	public List<OBJECT_T_MALL_SKU> query_sku_by_attr(Integer class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av) {

		StringBuffer sql = new StringBuffer();

		if (list_av != null) {
			for (int i = 0; i < list_av.size(); i++) {
				T_MALL_SKU_ATTR_VALUE av = list_av.get(i);
				if (i > 0) {
					sql.append(" ,(select sku_id from t_mall_sku_attr_value where shxm_id = " + av.getShxm_id()
							+ " and shxzh_id =" + av.getShxzh_id() + ") sku" + i + " ");
				} else {
					sql.append(" and sku.id in (select sku0.sku_id from ");
					sql.append(" (select sku_id from t_mall_sku_attr_value where shxm_id = " + av.getShxm_id()
							+ " and shxzh_id =" + av.getShxzh_id() + ") sku" + i + " ");
				}

			}
			if (list_av.size() > 1) {
				sql.append(" where ");
			}

			for (int i = 0; i < list_av.size(); i++) {
				if (i > 0) {
					if (i > 1) {
						sql.append(" and ");
					}
					sql.append(" sku" + (i - 1) + ".sku_id = sku" + i + ".sku_id  ");
				}
			}

			sql.append(" ) ");
		}

		Map<Object, Object> param = new HashMap<>();
		param.put("class_2_id", class_2_id);
		param.put("sql", sql.toString());

		return searchMapper.select_sku_by_attr(param);
	}

	@Override
	public OBJECT_T_MALL_SKU_DETAIL query_sku_by_sku_shp(Integer sku_id, Integer spu_id) {
		Map<Object, Object> param = new HashMap<>();
		param.put("sku_id", sku_id);
		param.put("spu_id", spu_id);
		OBJECT_T_MALL_SKU_DETAIL sku_detail = searchMapper.selct_sku_by_sku_shp(param);
		return sku_detail;
	}

	@Override
	public List<T_MALL_SKU> query_sku_by_spu_id(Integer spu_id) {
		return searchMapper.select_sku_by_spu_id(spu_id);
	}

}
