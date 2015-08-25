package cn.hxp.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.service.BolgTagBiz;
import cn.hxp.dao.BolgTagDao;
import cn.hxp.entity.BolgTag;


@Service
public class BolgTagImpl implements BolgTagBiz {

	@Resource
	private BolgTagDao bolgTagDao;

	@Override
	public List<BolgTag> findAll() {
		return bolgTagDao.findAll();
	}
	

	
	
}
