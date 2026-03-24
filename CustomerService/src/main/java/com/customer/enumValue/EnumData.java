package com.customer.enumValue;

public enum EnumData {
	
	Active("A"),
	NonActive("N");
	
	private final String value;
	
	EnumData(String value){
		this.value=value;
	}
	 public String getValue() {
		 return value;
	 }
	
	
	

}
