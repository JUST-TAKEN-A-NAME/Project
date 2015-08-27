package cn.hxp.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.dao.BolgPinglunBereplyDao;
import cn.hxp.entity.BolgPinglunBereply;
import cn.hxp.service.BolgPinglunBereplyBiz;



@Service
public class BolgPinglunBereplyImpl implements BolgPinglunBereplyBiz {
	
	@Resource
	BolgPinglunBereplyDao bolgPinglunBereplyDao;
	
	@Override
	public int newPinglunBereply(BolgPinglunBereply entity) {
		// TODO Auto-generated method stub
		return bolgPinglunBereplyDao.newPinglunBereply(entity);
	}

	@Override
	public int deleteByPrimaryKey(Integer beReplyId) {
		// TODO Auto-generated method stub
		return bolgPinglunBereplyDao.deleteByPrimaryKey(beReplyId);
	}

	@Override
	public List<BolgPinglunBereply> selectBereplyComment(int pinglunId) {
		// TODO Auto-generated method stub
		return bolgPinglunBereplyDao.selectBereplyComment(pinglunId);
	}



	
	
}
