package com.kt.code.generator;

import java.util.ArrayList;
import java.util.List;

import com.kt.code.CodeMetaVO;

public abstract class ACodeGenerator {
	public static final String LINE_SEP = System.lineSeparator();
	public static final String TAB_SPACE = "\t";
	
	abstract public String generateCode(CodeMetaVO codeMeta);
	
	public List<String> makeList(List<String> columns) {
		List<String> fieldList = new ArrayList<>();
		for(String str: columns) {
			fieldList.add(makeLine(str));
		}
		
		return fieldList;
	}
	
	abstract protected String makeLine(String colNm);
	
	protected String convert2CamelCase(String colNm) {
		StringBuilder sb = new StringBuilder();
		String[] fragements = colNm.toLowerCase().split("_");		
		
		sb.append(fragements[0]);
		if(fragements.length > 1) {
			for(int i=1; i<fragements.length; i++) {
				sb.append(fragements[i].substring(0,1).toUpperCase())
				  .append(fragements[i].substring(1));
			}
		}
			
		return sb.toString();
	}
	
	protected List<String> getColumns(CodeMetaVO vo) {
		List<String> columns = new ArrayList<>();
 		
		columns.addAll(vo.getKeys());
		columns.addAll(vo.getAttributes());
		
		return columns;
	}
}
