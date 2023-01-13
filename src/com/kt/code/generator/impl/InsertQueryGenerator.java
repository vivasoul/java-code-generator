package com.kt.code.generator.impl;

import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.generator.AMybatisCodeGenerator;

public class InsertQueryGenerator extends AMybatisCodeGenerator {

	@Override
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String tableNm = codeMeta.getTableNm();
		List<String> dbColumns = codeMeta.getColumns();
		List<String> mybatisColumns = makeList(dbColumns);
		
		sb.append("INSERT INTO ").append(tableNm).append(" (").append(LINE_SEP);
		
		int len = dbColumns.size();
		
		for(int i=0; i<len;i++) {
			String dbCol = dbColumns.get(i);
			
			sb.append(TAB_SPACE).append(dbCol);
			if(i < len-1) {
				sb.append(",");
			}
			sb.append(LINE_SEP);
		}
	    sb.append(") VALUES (").append(LINE_SEP);
		
		
		for(int i=0; i<len;i++) {
			String mbCol = mybatisColumns.get(i);
			
			sb.append(TAB_SPACE).append(mbCol);
			if(i < len-1) {
				sb.append(",");
			}
			sb.append(LINE_SEP);
		}
		sb.append(")").append(LINE_SEP);
		
		return sb.toString();
	}
	
	@Override
	protected String makeLine(String colNm) {
		
		return getMybatisBinder(colNm);
	}

}
