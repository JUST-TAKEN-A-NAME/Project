package cn.hxp.common.entity;

public class KeyValueEntity {
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public KeyValueEntity(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public KeyValueEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
