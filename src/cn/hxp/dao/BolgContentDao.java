package cn.hxp.dao;

import cn.hxp.entity.BolgContent;

public interface BolgContentDao {
    int deleteByPrimaryKey(Integer bolgContentId);

    int insert(BolgContent record);

    int insertSelective(BolgContent record);

    BolgContent selectByPrimaryKey(Integer bolgContentId);

    int updateByPrimaryKeySelective(BolgContent record);

    int updateByPrimaryKeyWithBLOBs(BolgContent record);

    int updateByPrimaryKey(BolgContent record);
    
    BolgContent selectByBolgInfoId(Integer bolgContentId);
}