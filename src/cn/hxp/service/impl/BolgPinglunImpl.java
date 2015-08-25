package cn.hxp.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.service.BolgPinglunBiz;
import cn.hxp.common.entity.PaginationeEntity;
import cn.hxp.dao.BolgPinglunDao;
import cn.hxp.entity.BolgPinglun;


@Service
public class BolgPinglunImpl implements BolgPinglunBiz {

	@Resource
	private BolgPinglunDao bolgPinglunDao;
	
	
	@Override
	public int createNewComment(BolgPinglun record) {
		// TODO Auto-generated method stub
		return bolgPinglunDao.createNewComment(record);
	}


	@Override
	public List<BolgPinglun> selectCommentByBolgIdPage(PaginationeEntity entity) {
		// TODO Auto-generated method stub
		
		return bolgPinglunDao.selectCommentByBolgIdPage(entity);
	}


	@Override
	public int selectCountByBolgId(int bolgId) {
		// TODO Auto-generated method stub
		return bolgPinglunDao.selectCountByBolgId(bolgId);
	}
	

	
	
}
