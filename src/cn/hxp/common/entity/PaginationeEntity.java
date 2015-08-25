package cn.hxp.common.entity;

import java.util.HashMap;
import java.util.Map;

public class PaginationeEntity {  
	
    private PageParameter page;  
      
    private String sort;  
      
    private String dir = "asc";  
      
    private String bolgId;
    
    private Map<String, Object> hashMap = new HashMap<String, Object>();

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getBolgId() {
		return bolgId;
	}

	public void setBolgId(String bolgId) {
		this.bolgId = bolgId;
	}

	public Map<String, Object> getHashMap() {
		return hashMap;
	}

	public void setHashMap(Map<String, Object> hashMap) {
		this.hashMap = hashMap;
	}

	
}  
