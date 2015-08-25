package cn.hxp.service.impl;


import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.dao.BolgUserDao;
import cn.hxp.service.BolgUserBiz;


@Service
public class BolgUserImpl implements BolgUserBiz {

	@Resource
	private BolgUserDao bolgUserDao;
	
	@Override
	public HashMap<String, String> selectImgandName(int userId) {
		// TODO Auto-generated method stub
		return bolgUserDao.selectImgandName(userId);
	}
	

	
	
}
