package com.kt.code.generator.impl;

import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.generator.AMybatisCodeGenerator;

public class SelectQueryGenerator extends AMybatisCodeGenerator {

	private boolean useRowCheckBox;
	
	public SelectQueryGenerator() {
		this(false);
	}
	
	public SelectQueryGenerator(boolean useRowCheckBox) {
		this.useRowCheckBox = useRowCheckBox; 
	}
	
	@Override
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String tableNm = codeMeta.getTableNm();
		List<String> dbColumns = getColumns(codeMeta);
		
		sb.append("SELECT").append(LINE_SEP);
		
		int len = dbColumns.size();
		
		for(int i=0; i<len;i++) {
			String dbCol = dbColumns.get(i);
			
			sb.append(TAB_SPACE);
			if(i > 0) sb.append(",");
			else	  sb.append(" ");
			sb.append(dbCol).append(LINE_SEP);
		}
		
		if(useRowCheckBox) {
			sb.append(TAB_SPACE).append(",'0' AS CHK").append(LINE_SEP);
		}
		
		sb.append("FROM ").append(tableNm).append(LINE_SEP)
		  .append(getDynamicWhereClause(codeMeta.getKeys())).append(LINE_SEP);
		
		return sb.toString();
	}

	@Override
	protected String makeLine(String colNm) {
		return colNm;
	}

}
