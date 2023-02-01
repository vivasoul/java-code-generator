package com.kt.code;

import java.util.List;

public class CodeMetaVO {
	private String dataSetId;			//Id of DataSet(Nexacro)
	private String domainNm;			//domain of java-classes..
	private String tableNm;				//table name  on database
	private List<String> keys;			//primary key names on database
	private List<String> attributes;	//attribute names on database
	
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
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	@Override
	public String toString() {
		return "CodeMetaVO [dataSetId=" + dataSetId + ", domainNm=" + domainNm + ", tableNm=" + tableNm + ", keys="
				+ keys + ", attributes=" + attributes + "]";
	}
}