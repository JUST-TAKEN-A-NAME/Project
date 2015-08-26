package cn.hxp.common;

import cn.hxp.common.entity.PageParameter;
import cn.hxp.utils.StringUtils;




public class PageHepler {

	
	/*
	 * currentPage:当前页！
	 * 
	 * totalPage:总页数！
	 * 
	 * 
	 * 检查前台传过来的 currentPage 值
	 */

	public static PageParameter checkPageNum(String currentPage,int totalPage) throws Exception {

		PageParameter page = new PageParameter();
		
		int currentPage_int = 1;
		
		if(StringUtils.isNumeric((currentPage))){//判断分页参数合法
			currentPage_int = Integer.parseInt(currentPage);
			if(currentPage_int + 1 >= totalPage){
				if(currentPage_int > totalPage){
					page.setCurrentPage(totalPage);//设置分页参数
					page.setPrePage(totalPage - 1);
					page.setNextPage(totalPage);
				}else if(currentPage_int == 1){
					page.setCurrentPage(1);//设置分页参数
					page.setPrePage(1);
					page.setNextPage(2);
				}else{
					page.setCurrentPage(currentPage_int);//设置分页参数
					page.setPrePage(currentPage_int - 1);
					page.setNextPage(totalPage);
				}
			}
			else if(currentPage_int <= 1){
				page.setCurrentPage(1);//设置分页参数
				page.setPrePage(1);
				page.setNextPage(2);
			}
			else{
				page.setCurrentPage(currentPage_int);//设置分页参数
				page.setPrePage(currentPage_int - 1);
				page.setNextPage(currentPage_int + 1);
			}
			
		}else{
			page.setCurrentPage(currentPage_int);//设置分页参数
			page.setPrePage(1);
			page.setNextPage(2);
		}
		
		return page;
	}


	
}
