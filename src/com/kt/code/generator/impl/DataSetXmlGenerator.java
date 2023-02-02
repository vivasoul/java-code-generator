package com.kt.code.generator.impl;

import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.generator.ACodeGenerator;

public class DataSetXmlGenerator extends ACodeGenerator {

	@Override
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String dsName = codeMeta.getDataSetId();
		List<String> colums = getColumns(codeMeta);
		List<String> list = makeList(colums);
		
		sb.append("<Dataset id=\"").append(dsName).append("\">").append(LINE_SEP)
		  .append("<ColumnInfo>").append(LINE_SEP);
		
		for(String line : list) {
			sb.append(TAB_SPACE).append(line).append(LINE_SEP);
		}
		sb.append("</ColumnInfo>").append(LINE_SEP);
		
		sb.append("<Rows>").append(LINE_SEP);
		sb.append("<Row>").append(LINE_SEP);
		for(String colNm : colums) {
			sb.append(TAB_SPACE).append(makeRowLine(colNm)).append(LINE_SEP);
		}
		sb.append("</Row>").append(LINE_SEP);
		sb.append("</Rows>").append(LINE_SEP);
		sb.append("</Dataset>");
		
		return sb.toString();
	}

	@Override
	protected String makeLine(String colNm) {
		StringBuilder sb = new StringBuilder();
		sb.append("<Column id=\"")
		  .append(convert2CamelCase(colNm))
		  .append("\" type=\"STRING\" size=\"256\"/>");
		
		return sb.toString();
	}

	private String makeRowLine(String colNm) {
		StringBuilder sb = new StringBuilder();
		sb.append("<Col id=\"")
		  .append(convert2CamelCase(colNm))
		  .append("\" />");
		
		return sb.toString();
	}
}

