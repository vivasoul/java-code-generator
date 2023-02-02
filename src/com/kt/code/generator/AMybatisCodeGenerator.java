package com.kt.code.generator;

import java.util.List;

import com.kt.code.exception.EmptyColumnsException;

public abstract class AMybatisCodeGenerator extends ACodeGenerator{
	
	protected String getMybatisBinder(String colNm) {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("#{")
			  .append(convert2CamelCase(colNm))
			  .append("}");
		
		return buffer.toString();		
	}
	
	protected String getWhereClause(List<String> columns) throws EmptyColumnsException {
		StringBuilder sb = new StringBuilder();
		
		if(columns == null || columns.isEmpty()) {
			throw new EmptyColumnsException();
		} else {
			for(int i=0; i<columns.size(); i++) {
				String col = columns.get(i);
				
				if(i > 0)	sb.append("AND "); 
				else		sb.append("WHERE ");
				
				sb.append(col).append("\t=\t").append(getMybatisBinder(col)).append(LINE_SEP);
			}
		}
		
		return sb.toString();
	}

	protected String getDynamicWhereClause(List<String> columns) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<where>").append(LINE_SEP);
		for(String col : columns) {
			String cc = convert2CamelCase(col);
			sb.append("\t<if test=\"").append(cc).append(" != null and ").append(cc).append(" != '' \">").append(LINE_SEP)
			  .append("\tAND ").append(col).append(" = #{").append(cc).append("}").append(LINE_SEP)
			  .append("\t</if>").append(LINE_SEP);
		}
		sb.append("</where>");
		
		return sb.toString();
	}
}
