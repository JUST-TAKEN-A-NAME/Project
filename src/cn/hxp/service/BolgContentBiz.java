package cn.hxp.service;


import cn.hxp.entity.BolgContent;

public interface BolgContentBiz {
	
	int insert(BolgContent bolgContent);

    BolgContent selectByBolgInfoId(Integer bolgContentId);
}
