package cn.hxp.service;

import cn.hxp.entity.THxpUser;

public interface THxpUserBiz {
	
	int deleteByPrimaryKey(Integer userId);

    int insert(THxpUser record);

    int insertSelective(THxpUser record);

    THxpUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(THxpUser record);

    int updateByPrimaryKey(THxpUser record);
    
    int selectByName(String userName);
    
    THxpUser login(String userName,String password);
}
