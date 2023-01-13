package com.kt;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.kt.code.CodeMetaBuilder;
import com.kt.code.CodeMetaVO;
import com.kt.code.generator.ACodeGenerator;
import com.kt.code.generator.impl.DataSetXmlGenerator;
import com.kt.code.generator.impl.InsertQueryGenerator;
import com.kt.code.generator.impl.JavaBeanGenerator;
import com.kt.code.generator.impl.SelectQueryGenerator;
import com.kt.code.generator.impl.UpdateQueryGenerator;

public class MainApp extends JFrame{
	
	private static final long serialVersionUID = -8066810997564678592L;
	
	private final Dimension inputLabelSize = new Dimension(150, 30);
	private final Dimension inputSize = new Dimension(200, 30);
	private final Dimension outputLabelSize = new Dimension(170, 30);
	private final Dimension outputSize = new Dimension(170, 600);	
	
	private JPanel inputArea;
	private JPanel outputArea;
	
	private JTextField domInput;
	private JTextField tableInput;
	private JTextField dataSetInput;
	private JTextArea columnsInput;
	private JCheckBox rowTypeCheck;
	
	private JTextArea javaBeanOutput;
	private JTextArea selQueryOutput;
	private JTextArea insQueryOutput;
	private JTextArea updQueryOutput;
	private JTextArea dataSetXmlOutput;
	
	public MainApp() {
	    super("OY♡Code Generator");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setBounds(100, 200, 1000, 600);
	    Container contentPane = getContentPane();
	    JPanel ioPanel = new JPanel();
	    
	    /* 1. input area */
	    initInputArea();
	    
	    /* 2. output area */
	    initOutputArea();
	    
	    ioPanel.add(inputArea);
	    ioPanel.add(outputArea);
	    
	    /* 3. submit area */
	    JButton buttonStart = new JButton("GENERATE★");
	    buttonStart.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  boolean useRowType = rowTypeCheck.isSelected();
	        	  CodeMetaVO codeMeta = getCodeMeata();
	        	  //System.out.println(codeMeta);
	        	  generate(codeMeta, useRowType);
	          }
	    });	    
	    
	    contentPane.add(ioPanel, "North");
	    contentPane.add(buttonStart, "South");
	    pack();
	    setVisible(true);	
	}
	
	public void generate(CodeMetaVO codeMeta, boolean useRowType) {
		ACodeGenerator javaBeanGen = new JavaBeanGenerator(useRowType);
		ACodeGenerator selectGen = new SelectQueryGenerator();
		ACodeGenerator insertGen = new InsertQueryGenerator();
		ACodeGenerator updateGen = new UpdateQueryGenerator();
		ACodeGenerator dataSetGen = new DataSetXmlGenerator();
		
		
		String javaBeanCode = javaBeanGen.generateCode(codeMeta);
		String selectCode = selectGen.generateCode(codeMeta);
		String insertCode = insertGen.generateCode(codeMeta);
		String updatetCode = updateGen.generateCode(codeMeta);
		String dataSetCode = dataSetGen.generateCode(codeMeta);
		
		javaBeanOutput.setText(javaBeanCode);
		selQueryOutput.setText(selectCode);
		insQueryOutput.setText(insertCode);
		updQueryOutput.setText(updatetCode);
		dataSetXmlOutput.setText(dataSetCode);
	}
	
	private void initInputArea() {
	    inputArea = new JPanel();
	    BoxLayout inputLayoyt = new BoxLayout(inputArea, BoxLayout.Y_AXIS);
	    inputArea.setLayout(inputLayoyt);
	    	
	    	JPanel domPanel = new JPanel();
	    	
		    	JLabel domLb = new JLabel("Domain(VO) Name: ");
		    	domLb.setHorizontalAlignment(SwingConstants.RIGHT);
		    	domLb.setPreferredSize(inputLabelSize);
			    domInput = new JTextField();
			    domInput.setPreferredSize(inputSize);
		    
		    domPanel.add(domLb);
		    domPanel.add(domInput);

		    JPanel tablePanel = new JPanel();
		    	
		    	JLabel tableLb = new JLabel("Table Name: ");
		    	tableLb.setHorizontalAlignment(SwingConstants.RIGHT);
		    	tableLb.setPreferredSize(inputLabelSize);
			    tableInput = new JTextField();
			    tableInput.setPreferredSize(inputSize);
		    
			tablePanel.add(tableLb);
			tablePanel.add(tableInput);		    
	    
			JPanel dataSetPanel = new JPanel();
	    	
		    	JLabel dataSetLb = new JLabel("DataSet Id: ");
		    	dataSetLb.setHorizontalAlignment(SwingConstants.RIGHT);
		    	dataSetLb.setPreferredSize(inputLabelSize);
			    dataSetInput = new JTextField();
			    dataSetInput.setPreferredSize(inputSize);		    
		    
			dataSetPanel.add(dataSetLb);
			dataSetPanel.add(dataSetInput);
		
			JPanel columnsPanel = new JPanel();
	    	
		    	JLabel columnsLb = new JLabel("List of Columns: ");
		    	columnsLb.setHorizontalAlignment(SwingConstants.RIGHT);
		    	columnsLb.setPreferredSize(inputLabelSize);
		    	columnsInput = new JTextArea(5, 10);
			    
		    	JScrollPane columnsScroll = new JScrollPane (columnsInput, 
			    		   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	    
		    	columnsScroll.setPreferredSize(new Dimension(200, 400));			    
		    
			columnsPanel.add(columnsLb);
			columnsPanel.add(columnsScroll);			
			
			JPanel rowTypePanel = new JPanel();
	    	
		    	JLabel rowTypeLb = new JLabel("Use row-type: ");
		    	rowTypeLb.setHorizontalAlignment(SwingConstants.RIGHT);
		    	rowTypeLb.setPreferredSize(inputLabelSize);
		    	rowTypeCheck = new JCheckBox();
		    	//rowTypeCheck.setPreferredSize(inputSize);		    
		    
		    rowTypePanel.add(rowTypeLb);
		    rowTypePanel.add(rowTypeCheck);			
	    
	    inputArea.add(domPanel);
	    inputArea.add(tablePanel);
	    inputArea.add(dataSetPanel);
	    inputArea.add(columnsPanel);
	    inputArea.add(rowTypePanel);
	}
	
	private void initOutputArea() {
		
		outputArea = new JPanel();
			JPanel jbPanel = new JPanel();
			jbPanel.setLayout(new BoxLayout(jbPanel, BoxLayout.Y_AXIS));
			
		    	JLabel jbLb = new JLabel("Java Bean code");
		    	jbLb.setHorizontalAlignment(SwingConstants.CENTER);
		    	jbLb.setPreferredSize(outputLabelSize);
				javaBeanOutput = new JTextArea(5, 20);
				javaBeanOutput.setEditable(false);
				JScrollPane jbScroll = new JScrollPane (javaBeanOutput, 
			    		   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	    
				jbScroll.setPreferredSize(outputSize);				
				
			jbPanel.add(jbLb);	
			jbPanel.add(jbScroll);

			JPanel selqPanel = new JPanel();
			selqPanel.setLayout(new BoxLayout(selqPanel, BoxLayout.Y_AXIS));
			
		    	JLabel selqLb = new JLabel("Select Query");
		    	selqLb.setHorizontalAlignment(SwingConstants.CENTER);
		    	selqLb.setPreferredSize(outputLabelSize);
				selQueryOutput = new JTextArea(5, 20);
				selQueryOutput.setEditable(false);
				JScrollPane selqScroll = new JScrollPane (selQueryOutput, 
			    		   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	    
				selqScroll.setPreferredSize(outputSize);
				
			selqPanel.add(selqLb);	
			selqPanel.add(selqScroll);
			
			JPanel insqPanel = new JPanel();
			insqPanel.setLayout(new BoxLayout(insqPanel, BoxLayout.Y_AXIS));
			
		    	JLabel insqLb = new JLabel("Insert Query");
		    	insqLb.setHorizontalAlignment(SwingConstants.CENTER);
		    	insqLb.setPreferredSize(outputLabelSize);
		    	insQueryOutput = new JTextArea(5, 20);
		    	insQueryOutput.setEditable(false);
				JScrollPane insqScroll = new JScrollPane (insQueryOutput, 
			    		   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	    
				insqScroll.setPreferredSize(outputSize);
				
			insqPanel.add(insqLb);	
			insqPanel.add(insqScroll);			

			JPanel updqPanel = new JPanel();
			updqPanel.setLayout(new BoxLayout(updqPanel, BoxLayout.Y_AXIS));
			
		    	JLabel updqLb = new JLabel("Update Query");
		    	updqLb.setHorizontalAlignment(SwingConstants.CENTER);
		    	updqLb.setPreferredSize(outputLabelSize);
		    	updQueryOutput = new JTextArea(5, 20);
		    	updQueryOutput.setEditable(false);
				JScrollPane updqScroll = new JScrollPane (updQueryOutput, 
			    		   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	    
				updqScroll.setPreferredSize(outputSize);
				
			updqPanel.add(updqLb);	
			updqPanel.add(updqScroll);	
			
			JPanel dsxPanel = new JPanel();
			dsxPanel.setLayout(new BoxLayout(dsxPanel, BoxLayout.Y_AXIS));
			
		    	JLabel dsxLb = new JLabel("DataSet XML");
		    	dsxLb.setHorizontalAlignment(SwingConstants.CENTER);
		    	dsxLb.setPreferredSize(outputLabelSize);
		    	dataSetXmlOutput = new JTextArea(5, 20);
		    	dataSetXmlOutput.setEditable(false);
				JScrollPane dsxScroll = new JScrollPane (dataSetXmlOutput, 
			    		   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	    
				dsxScroll.setPreferredSize(outputSize);
				
			dsxPanel.add(dsxLb);	
			dsxPanel.add(dsxScroll);			
			
		outputArea.add(jbPanel);
		outputArea.add(selqPanel);
		outputArea.add(insqPanel);
		outputArea.add(updqPanel);
		outputArea.add(dsxPanel);
	}
	
	private CodeMetaVO getCodeMeata() {
		CodeMetaBuilder cmb = new CodeMetaBuilder();
		
		String domainNm = domInput.getText() != null ? domInput.getText().trim() : "";
		String tableNm = tableInput.getText() != null ? tableInput.getText().trim() : "";
		String dataSetId = dataSetInput.getText() != null ? dataSetInput.getText().trim() : "";
		List<String> columns = getColumns();

		cmb.setDomainNm(domainNm)
		   .setTableNm(tableNm)
		   .setDataSetId(dataSetId);
		
		if(columns != null) {   
		   cmb.setColumns(columns);
		}
		
		return cmb.build();
	}	
	
	private List<String> getColumns() {
		String columnsStr = columnsInput.getText();
		
		if(columnsStr != null && !columnsStr.isEmpty()) {
			String[] arr = columnsStr.split("\\s+");
			return Arrays.asList(arr);
		} else {
			return null; 
		}
	}
	
	public static void main(String[] args) {
	//	test();
		new MainApp();
	}
		
	private void test(CodeMetaVO codeMeta) {
		//List<String> list = readData();
		
		ACodeGenerator javaBeanGen = new JavaBeanGenerator();
		ACodeGenerator selectGen = new SelectQueryGenerator();
		ACodeGenerator insertGen = new InsertQueryGenerator();
		ACodeGenerator updateGen = new UpdateQueryGenerator();
		
		String javaBeanCode = javaBeanGen.generateCode(codeMeta);
		String selectCode = selectGen.generateCode(codeMeta);
		String insertCode = insertGen.generateCode(codeMeta);
		String updatetCode = updateGen.generateCode(codeMeta);
		
		System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆JAVA BEAN CODE☆☆☆☆☆☆☆☆☆☆☆☆");
		System.out.println(javaBeanCode);
		System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆SELECT QUERY CODE☆☆☆☆☆☆☆☆☆☆☆☆");
		System.out.println(selectCode);		
		System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆INSERT QUERY CODE☆☆☆☆☆☆☆☆☆☆☆☆");
		System.out.println(insertCode);
		System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆UPDATE QUERY CODE☆☆☆☆☆☆☆☆☆☆☆☆");
		System.out.println(updatetCode);		
	}
	
	@Deprecated
	private static CodeMetaVO getCodeMeata2(List<String> columns) {
		CodeMetaBuilder cmb = new CodeMetaBuilder();
		
		cmb.setDomainNm("Test")
		   .setTableNm("TB_TEST")
		   .setColumns(columns);
		
		return cmb.build();
	}
	
	@Deprecated
	private static List<String> readData() {
		BufferedReader reader = null;
		List<String> list = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader("target.gen"));
			String line = null;
			while(  (line = reader.readLine()) != null) {
				list.add(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null) { try { reader.close(); } catch(Exception e) {} }
		}
		
		return list;
	}
}
