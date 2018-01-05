package com.atguigu.mapper;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(OBJECT_T_MALL_ORDER order);

	void insert_flow(OBJECT_T_MALL_FLOW flow);

	void insert_info(T_MALL_ORDER_INFO info);

	void delete_cart(int gwch_id);

	long select_kc(Integer sku_id);

	long select_kc_for_update(Integer sku_id);

	void update_order(OBJECT_T_MALL_ORDER order);

	void update_flow(OBJECT_T_MALL_FLOW flow);

	void update_info(T_MALL_ORDER_INFO info);

	int select_kc_enough(Integer sku_id);

}
