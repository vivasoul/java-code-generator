package com.kt.code.generator.impl;

import java.util.List;

import com.kt.code.CodeMetaVO;
import com.kt.code.generator.ACodeGenerator;

public class JavaBeanGenerator extends ACodeGenerator {

	private boolean isExtendingRowTypeAccessor;
	
	public JavaBeanGenerator() {
		this(false);
	}
	
	public JavaBeanGenerator(boolean isExtendingRowTypeAccessor) {
		this.isExtendingRowTypeAccessor = isExtendingRowTypeAccessor;
	}
	
	public String generateCode(CodeMetaVO codeMeta) {
		StringBuilder sb = new StringBuilder();
		String voName = getVOName(codeMeta.getDomainNm());
		List<String> list = makeList(codeMeta.getColumns());
		
		if(isExtendingRowTypeAccessor) { 
			sb.append("import com.nexacro.uiadapter.spring.core.data.DataSetRowTypeAccessor;")
			  .append(LINE_SEP).append(LINE_SEP);
		}
		sb.append("import lombok.Getter;").append(LINE_SEP)
		  .append("import lombok.Setter;").append(LINE_SEP).append(LINE_SEP)
		  .append("@Getter").append(LINE_SEP)
		  .append("@Setter").append(LINE_SEP)
		  .append("public class ").append(voName);
		
		if(isExtendingRowTypeAccessor) {
			sb.append(" implements DataSetRowTypeAccessor");
		} 
		sb.append(" {").append(LINE_SEP);
		
		for(String line : list) {
			sb.append(TAB_SPACE).append(line).append(LINE_SEP);
		}
		
		if(isExtendingRowTypeAccessor) { 
			sb.append(TAB_SPACE).append("private int rowType;").append(LINE_SEP);
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	

	@Override
	protected String makeLine(String colNm) {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("private String ")
			  .append(convert2CamelCase(colNm))
			  .append(";");
		
		return buffer.toString();
	}
	
	private String getVOName(String domainNm) {
		return domainNm + "VO";
	}
}
