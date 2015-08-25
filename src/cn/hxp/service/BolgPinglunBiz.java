package cn.hxp.service;

import java.util.List;

import cn.hxp.common.entity.PaginationeEntity;
import cn.hxp.entity.BolgPinglun;


public interface BolgPinglunBiz {
	

   int createNewComment(BolgPinglun record);
    

	List<BolgPinglun> selectCommentByBolgIdPage(PaginationeEntity entity);
	

	int selectCountByBolgId(int bolgId);
}