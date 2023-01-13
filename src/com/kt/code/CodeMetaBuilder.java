package com.kt.code;

import java.util.ArrayList;
import java.util.List;

public class CodeMetaBuilder {
	private String dataSetId;
	private String domainNm;
	private String tableNm;
	private List<String> columns = new ArrayList<>();

	public CodeMetaBuilder setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId;
		return this;
	}
	
	public CodeMetaBuilder setDomainNm(String domainNm) {
		this.domainNm = domainNm;
		return this;
	}
	
	public CodeMetaBuilder setTableNm(String tableNm) {
		this.tableNm = tableNm;
		return this;
	}
	public CodeMetaBuilder setColumns(List<String> columns) {
		this.columns = columns;
		return this;
	}
	
	public CodeMetaBuilder addColumn(String colNm) {
		this.columns.add(colNm);
		return this;
	}
	
	public CodeMetaVO build() {
		CodeMetaVO vo = new CodeMetaVO();
		
		vo.setDataSetId(dataSetId);
		vo.setDomainNm(domainNm);
		vo.setTableNm(tableNm);
		vo.setColumns(columns);
		
		return vo;
	}
}
