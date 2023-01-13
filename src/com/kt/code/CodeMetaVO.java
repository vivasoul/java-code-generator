package com.kt.code;

import java.util.List;

public class CodeMetaVO {
	private String dataSetId;		//Id of DataSet(Nexacro)
	private String domainNm;		//domain of java-classes..
	private String tableNm;			//table name  on database
	private List<String> columns;	//column name on database
	
	public String getDataSetId() {
		return dataSetId;
	}
	public void setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId;
	}
	public String getDomainNm() {
		return domainNm;
	}
	public void setDomainNm(String domainNm) {
		this.domainNm = domainNm;
	}
	public String getTableNm() {
		return tableNm;
	}
	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	@Override
	public String toString() {
		return "CodeMetaVO [dataSetId=" + dataSetId + ", domainNm=" + domainNm + ", tableNm=" + tableNm + ", columns="
				+ columns + "]";
	}
}