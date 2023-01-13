package com.kt.code.generator;

public abstract class AMybatisCodeGenerator extends ACodeGenerator{
	
	protected String getMybatisBinder(String colNm) {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("#{")
			  .append(convert2CamelCase(colNm))
			  .append("}");
		
		return buffer.toString();		
	}
}
