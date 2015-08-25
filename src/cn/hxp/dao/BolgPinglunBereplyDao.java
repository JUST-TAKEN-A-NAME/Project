package cn.hxp.dao;

import cn.hxp.entity.BolgPinglunBereply;

public interface BolgPinglunBereplyDao {
    int deleteByPrimaryKey(Integer beReplyId);

    int insert(BolgPinglunBereply record);

    int insertSelective(BolgPinglunBereply record);

    BolgPinglunBereply selectByPrimaryKey(Integer beReplyId);

    int updateByPrimaryKeySelective(BolgPinglunBereply record);

    int updateByPrimaryKey(BolgPinglunBereply record);
}