package cn.hxp.dao;

import java.util.HashMap;

import cn.hxp.entity.BolgUser;

public interface BolgUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(BolgUser record);

    int insertSelective(BolgUser record);

    BolgUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(BolgUser record);

    int updateByPrimaryKey(BolgUser record);
    
    HashMap<String, String> selectImgandName(int userId);
    
    int selectUsertoConfim(int userId);
}