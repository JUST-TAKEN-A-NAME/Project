package cn.hxp.service.impl;



import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hxp.service.BolgInfoBiz;
import cn.hxp.dao.BolgInfoDao;
import cn.hxp.entity.BolgInfo;


@Service
public class BolgInfoImpl implements BolgInfoBiz {

	@Resource
	private BolgInfoDao bolgInfoDao;
	
	/*@Override
	public void insert(BolgInfo entity) {
		if(entity.getBolgIsReport() == null){
			entity.setBolgIsReport(0);
		}
		
		if(entity.getBolgGrade() == null){
			entity.setBolgGrade(0);
		}
		
		
    	Timestamp now = new Timestamp(new Date().getTime());
		
		entity.setBolgCreatetime(now);
		entity.setBolgLastEditTime(now);
		bolgInfoDao.insert(entity);
	}*/
	
	@Override
	public void insert(BolgInfo entity) {
		bolgInfoDao.insert(entity);
	}

	public BolgInfo selectByPrimaryKey(Integer bolgId){
		return bolgInfoDao.selectByPrimaryKey(bolgId);
	}

	
	@Override
	public int deleteByPrimaryKey(Integer bolgId) {
		return bolgInfoDao.deleteByPrimaryKey(bolgId);
	}

	@Override
	public int update(BolgInfo entity) {
		
		if(entity.getBolgIsReport() == null){
			entity.setBolgIsReport(0);
		}
		
		if(entity.getBolgGrade() == null){
			entity.setBolgGrade(0);
		}

    	Timestamp now = new Timestamp(new Date().getTime());
    	
    	if(entity.getBolgCreatetime() == null){
    		entity.setBolgCreatetime(now);
    	}
    	
		entity.setBolgLastEditTime(now);
		
		return bolgInfoDao.update(entity);
	}
	
	
}
