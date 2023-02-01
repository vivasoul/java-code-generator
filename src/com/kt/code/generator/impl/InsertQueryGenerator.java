package com.kt.code.generator.impl;

import java.util.Arrays;
import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.generator.AMybatisCodeGenerator;

public class InsertQueryGenerator extends AMybatisCodeGenerator {
	private final List<String> excludeList = Arrays.asList(new String[]{"REG_DTIME", "MODI_DTIME"});
	
	@Override
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String tableNm = codeMeta.getTableNm();
		List<String> dbColumns = getColumns(codeMeta);
		List<String> mybatisColumns = makeList(dbColumns);
		
		sb.append("INSERT INTO ").append(tableNm).append(" (").append(LINE_SEP);
		
		int len = dbColumns.size();
		
		int j=0;
		for(int i=0; i<len;i++) {
			String dbCol = dbColumns.get(i);
			
			if(excludeList.contains(dbCol)) continue;
			
			sb.append(TAB_SPACE);
			if(j > 0) sb.append(",");
			else	  sb.append(" ");
			sb.append(dbCol).append(LINE_SEP);
			
			j++;
		}
	    sb.append(") VALUES (").append(LINE_SEP);
		
	    int k=0;
		for(int i=0; i<len;i++) {
			String mbCol = mybatisColumns.get(i);
			
			if(excludeList.contains(dbColumns.get(i))) continue;
			
			sb.append(TAB_SPACE);
			if(k > 0) sb.append(",");
			else	  sb.append(" ");
			sb.append(mbCol).append(LINE_SEP);
			
			k++;
		}
		sb.append(")").append(LINE_SEP);
		
		return sb.toString();
	}
	
	@Override
	protected String makeLine(String colNm) {
		
		return getMybatisBinder(colNm);
	}

}
