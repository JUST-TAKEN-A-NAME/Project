package cn.hxp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.service.THxpUserBiz;
import cn.hxp.dao.THxpUserMapper;
import cn.hxp.entity.THxpUser;


@Service("userService")
public class THxpUserImpl implements THxpUserBiz {
	
	@Resource
	private THxpUserMapper userDao;
	
	
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return userDao.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(THxpUser record) {
		return userDao.insert(record);
	}

	@Override
	public int insertSelective(THxpUser record) {
		return userDao.insertSelective(record);
	}

	@Override
	public THxpUser selectByPrimaryKey(Integer userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(THxpUser record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(THxpUser record) {
		return userDao.updateByPrimaryKey(record);
	}

	@Override
	public int selectByName(String userName) {
		return userDao.selectByName(userName);
	}

	@Override
	public THxpUser login(String userName, String password) {
		return userDao.login(userName,password);
	}

}
