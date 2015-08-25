package cn.hxp.dao;

import cn.hxp.entity.THxpUser;

public interface THxpUserMapper {
	
    int deleteByPrimaryKey(Integer userId);

    int insert(THxpUser record);

    int insertSelective(THxpUser record);

    THxpUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(THxpUser record);

    int updateByPrimaryKey(THxpUser record);
    
    int selectByName(String userName);
    
    THxpUser login(String userName,String password);
}