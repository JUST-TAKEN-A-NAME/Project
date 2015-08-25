package cn.hxp.dao;

import java.util.List;


import cn.hxp.common.entity.PaginationeEntity;
import cn.hxp.entity.BolgPinglun;

public interface BolgPinglunDao {
	int deleteByPrimaryKey(Integer pinglunId);

	int createNewComment(BolgPinglun record);

	int insertSelective(BolgPinglun record);

	BolgPinglun selectByPrimaryKey(Integer pinglunId);

	int updateByPrimaryKeySelective(BolgPinglun record);

	int updateByPrimaryKey(BolgPinglun record);

	List<BolgPinglun> selectCommentByBolgIdPage(PaginationeEntity entity);
	
}