package com.atguigu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.mapper.ShoppingCartMapper;
import com.atguigu.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

	@Autowired
	private ShoppingCartMapper shoppingCart;

	@Override
	public List<T_MALL_SHOPPINGCART> query_list_cart_by_user_id(int id) {
		return shoppingCart.select_list_cart_by_user_id(id);
	}

	@Override
	public void add_cart(T_MALL_SHOPPINGCART cart) {
		shoppingCart.insert_cart(cart);
	}

	@Override
	public void updata_cart(T_MALL_SHOPPINGCART cart) {
		shoppingCart.updata_cart(cart);
	}

	@Override
	public T_MALL_SHOPPINGCART query_cart_by_sku_id(int sku_id) {
		return shoppingCart.select_cart_by_sku_id(sku_id);
	}

	@Override
	public void delete_cart(T_MALL_SHOPPINGCART t_MALL_SHOPPINGCART) {
		shoppingCart.delete_cart(t_MALL_SHOPPINGCART);
	}
}
