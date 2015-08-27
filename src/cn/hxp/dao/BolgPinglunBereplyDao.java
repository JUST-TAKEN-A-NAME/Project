package cn.hxp.dao;

import java.util.List;

import cn.hxp.entity.BolgPinglunBereply;

public interface BolgPinglunBereplyDao {
    int deleteByPrimaryKey(Integer beReplyId);

    int newPinglunBereply(BolgPinglunBereply record);

    int insertSelective(BolgPinglunBereply record);

    BolgPinglunBereply selectByPrimaryKey(Integer beReplyId);

    int updateByPrimaryKeySelective(BolgPinglunBereply record);

    int updateByPrimaryKey(BolgPinglunBereply record);
    
    List<BolgPinglunBereply> selectBereplyComment(int pinglunId);
}