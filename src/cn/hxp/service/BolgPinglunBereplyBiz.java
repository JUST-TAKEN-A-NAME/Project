package cn.hxp.service;

import java.util.List;

import cn.hxp.entity.BolgPinglunBereply;


public interface BolgPinglunBereplyBiz {
	
	
	int newPinglunBereply(BolgPinglunBereply entity);

    int deleteByPrimaryKey(Integer beReplyId);


    List<BolgPinglunBereply> selectBereplyComment(int pinglunId);
}