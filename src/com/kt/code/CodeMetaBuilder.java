package com.kt.code;

import java.util.ArrayList;
import java.util.List;

public class CodeMetaBuilder {
	private String dataSetId;
	private String domainNm;
	private String tableNm;
	private List<String> keys = new ArrayList<>();
	private List<String> attributes = new ArrayList<>();

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
	public CodeMetaBuilder setKeys(List<String> keys) {
		this.keys = keys;
		return this;
	}
	
	public CodeMetaBuilder addKey(String keyNm) {
		this.keys.add(keyNm);
		return this;
	}
	public CodeMetaBuilder setAttributes(List<String> attributes) {
		this.attributes = attributes;
		return this;
	}
	
	public CodeMetaBuilder addAttribute(String attrNm) {
		this.attributes.add(attrNm);
		return this;
	}	
	
	public CodeMetaVO build() {
		CodeMetaVO vo = new CodeMetaVO();
		
		vo.setDataSetId(dataSetId);
		vo.setDomainNm(domainNm);
		vo.setTableNm(tableNm);
		vo.setKeys(keys);
		vo.setAttributes(attributes);
		
		return vo;
	}
}
