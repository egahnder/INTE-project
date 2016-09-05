package org.pucko.testutilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputBuilder {
	StringBuilder builder;
	
	public InputBuilder(){
		this.builder = new StringBuilder();
	}
	
	public void addLine(String line){
		builder.append(line+System.lineSeparator());
	}
	
	public InputStream build(){
		String inString = builder.toString();
		InputStream inputStream = new ByteArrayInputStream(inString.getBytes());
		return inputStream;
	}

}
