package cn.hxp.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.service.BolgTypeBiz;
import cn.hxp.dao.BolgTypeDao;
import cn.hxp.entity.BolgType;


@Service
public class BolgTypeImpl implements BolgTypeBiz {

	@Resource
	private BolgTypeDao bolgTypeDao;

	@Override
	public List<BolgType> findAll() {
		return bolgTypeDao.findAll();
	}
	

	
	
}
