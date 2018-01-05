package com.atguigu.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.service.AttrService;

@Service
public class AttrServiceImp implements AttrService {

	@Autowired
	private AttrMapper attrMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> query_attr_list(Integer calss_2) {

		return attrMapper.select_attr_list(calss_2);
	}

}
