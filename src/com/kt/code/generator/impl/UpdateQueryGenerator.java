package com.kt.code.generator.impl;

import java.util.Arrays;
import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.exception.EmptyColumnsException;
import com.kt.code.generator.AMybatisCodeGenerator;

public class UpdateQueryGenerator extends AMybatisCodeGenerator {
	private final List<String> excludeList = Arrays.asList(new String[]{"REG_USER_ID", "REG_DTIME"});
	private final String MODI_DTIME = "MODI_DTIME";
	
	@Override
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String tableNm = codeMeta.getTableNm();
		
		List<String> attrs = codeMeta.getAttributes();
		List<String> attrLines = makeList(attrs);
		
		sb.append("UPDATE ").append(tableNm).append(LINE_SEP)
		  .append("SET ").append(LINE_SEP);
		
		int j=0;
		for(int i=0; i<attrs.size();i++) {
			String attr = attrs.get(i);
			
			if(excludeList.contains(attr)) continue;
			
			String line = attrLines.get(i);
			
			sb.append(TAB_SPACE);
			
			if(j > 0) sb.append(",");
			else	  sb.append(" ");
			
			sb.append(line).append(LINE_SEP);
			
			j++;
		}
		
		try {
			sb.append(getWhereClause(codeMeta.getKeys()));
		
			return sb.toString();
		}catch(EmptyColumnsException ece) {
			
			return null;
		}
	}

	@Override
	protected String makeLine(String colNm) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(colNm)
		  .append(" = ");
		if(MODI_DTIME.equals(colNm)) {  
			sb.append("current_timestamp");
		} else {
			sb.append(getMybatisBinder(colNm));
		}
		return sb.toString();
	}

}
