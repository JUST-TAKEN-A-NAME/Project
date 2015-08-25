package cn.hxp.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.service.BolgContentBiz;
import cn.hxp.dao.BolgContentDao;
import cn.hxp.entity.BolgContent;


@Service
public class BolgContentImpl implements BolgContentBiz {

	@Resource
	private BolgContentDao bolgContentDao;


	@Override
	public int insert(BolgContent bolgContent) {
		// TODO Auto-generated method stub
		return bolgContentDao.insert(bolgContent);
	}


	@Override
	public BolgContent selectByBolgInfoId(Integer bolgContentId) {
		// TODO Auto-generated method stub
		return bolgContentDao.selectByBolgInfoId(bolgContentId);
	}
	

	
	
}
