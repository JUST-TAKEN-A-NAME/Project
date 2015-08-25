package cn.hxp.dao;

import java.util.List;

import cn.hxp.entity.BolgType;

public interface BolgTypeDao {
    int deleteByPrimaryKey(Integer typeId);

    int insert(BolgType record);

    int insertSelective(BolgType record);

    BolgType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(BolgType record);

    int updateByPrimaryKey(BolgType record);
    
    List<BolgType> findAll();
}