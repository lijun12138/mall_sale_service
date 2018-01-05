package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCART;

public interface ShoppingCartMapper {

	List<T_MALL_SHOPPINGCART> select_list_cart_by_user_id(int id);

	void insert_cart(T_MALL_SHOPPINGCART cart);

	void updata_cart(T_MALL_SHOPPINGCART cart);

	T_MALL_SHOPPINGCART select_cart_by_sku_id(int sku_id);

	void delete_cart(T_MALL_SHOPPINGCART t_MALL_SHOPPINGCART);

}
