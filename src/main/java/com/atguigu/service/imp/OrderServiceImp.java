package com.atguigu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.exception.OverSaleException;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.service.OrderService;
import com.atguigu.util.MyDateUtil;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void save_ordre(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address) {
		order.setDzh_id(address.getId());
		order.setDzh_mch(address.getYh_dz());
		order.setShhr(address.getShjr());
		orderMapper.insert_order(order);

		List<OBJECT_T_MALL_FLOW> flow_list = order.getFlow_list();

		for (OBJECT_T_MALL_FLOW flow : flow_list) {
			flow.setDd_id(order.getId());
			flow.setMdd(address.getYh_dz());
			orderMapper.insert_flow(flow);
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			for (T_MALL_ORDER_INFO info : list_info) {
				info.setDd_id(order.getId());
				info.setFlow_id(flow.getId());
				orderMapper.insert_info(info);
				orderMapper.delete_cart(info.getGwch_id());
			}
		}
	}

	@Override
	public void order_pay(OBJECT_T_MALL_ORDER order) throws OverSaleException {
		// 支付改库存的业务
		// 获取订单中的物流包裹
		List<OBJECT_T_MALL_FLOW> flow_list = order.getFlow_list();

		for (OBJECT_T_MALL_FLOW flow : flow_list) {
			// 先进行库存判断再持久化
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			for (T_MALL_ORDER_INFO info : list_info) {
				// 获取库存
				long kc = get_kc(info.getSku_id());
				if (kc >= info.getSku_shl()) {
					// 修改订单中对应sku的库存
					// 更新数据库中的数据
					orderMapper.update_info(info);
				} else {
					throw new OverSaleException("库存不足,支付失败");
				}

			}
			// 修改物流包裹中的信息,实际上是从物流系统中获得以下数据
			flow.setPsshj(MyDateUtil.get_date(1));
			flow.setPsmsh("商品出库中");
			flow.setYwy("马云");
			flow.setLxfsh("9090980");
			orderMapper.update_flow(flow);
		}
		// 修改订单中信息
		order.setJdh(1);
		order.setYjsdshj(MyDateUtil.get_date(4));
		orderMapper.update_order(order);
	}

	public long get_kc(Integer sku_id) {
		int i = orderMapper.select_kc_enough(sku_id);
		long kc = 0;
		if (i == 1) {
			kc = orderMapper.select_kc(sku_id);
		} else {
			kc = orderMapper.select_kc_for_update(sku_id);
		}
		return kc;
	}

}
