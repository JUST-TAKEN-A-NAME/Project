package cn.hxp.dao;

import java.util.List;

import cn.hxp.entity.BolgTag;

public interface BolgTagDao {
    int deleteByPrimaryKey(Integer tagId);

    int insert(BolgTag record);

    int insertSelective(BolgTag record);

    BolgTag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(BolgTag record);

    int updateByPrimaryKey(BolgTag record);
    
    List<BolgTag> findAll();
}