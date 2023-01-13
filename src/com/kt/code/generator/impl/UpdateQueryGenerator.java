package com.kt.code.generator.impl;

import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.generator.AMybatisCodeGenerator;

public class UpdateQueryGenerator extends AMybatisCodeGenerator {

	@Override
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String tableNm = codeMeta.getTableNm();
		List<String> dbColumns = codeMeta.getColumns();
		List<String> mybatisColumns = makeList(dbColumns);
		
		sb.append("UPDATE ").append(tableNm).append(LINE_SEP)
		  .append("SET ").append(LINE_SEP);
		
		int len = dbColumns.size();
		
		for(int i=0; i<len;i++) {
			String mbCol = mybatisColumns.get(i);
			
			sb.append(TAB_SPACE).append(mbCol);
			if(i < len-1) {
				sb.append(",");
			}
			sb.append(LINE_SEP);
		}
		
		sb.append("WHERE 1=1").append(LINE_SEP);
		
		return sb.toString();
	}

	@Override
	protected String makeLine(String colNm) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(colNm)
		  .append(" = ")
		  .append(getMybatisBinder(colNm));
		
		return sb.toString();
	}

}
