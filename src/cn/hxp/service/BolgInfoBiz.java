package cn.hxp.service;

import cn.hxp.entity.BolgInfo;

public interface BolgInfoBiz {
	
	void insert(BolgInfo record);
	
	BolgInfo selectByPrimaryKey(Integer bolgId);
	
	int deleteByPrimaryKey(Integer bolgId);
	
	int update(BolgInfo bolgInfo);
	
}
