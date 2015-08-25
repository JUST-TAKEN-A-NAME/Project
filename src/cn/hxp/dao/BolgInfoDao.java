package cn.hxp.dao;

import cn.hxp.entity.BolgInfo;

public interface BolgInfoDao {
    int deleteByPrimaryKey(Integer bolgId);

    void insert(BolgInfo record);

    int insertSelective(BolgInfo record);

    BolgInfo selectByPrimaryKey(Integer bolgId);

    int update(BolgInfo record);

    int updateByPrimaryKey(BolgInfo record);
}