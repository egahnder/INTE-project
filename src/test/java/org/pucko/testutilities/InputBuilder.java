package org.pucko.testutilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputBuilder {
	StringBuilder builder;
	
	public InputBuilder(){
		this.builder = new StringBuilder();
	}
	
	public InputBuilder addLine(String line){
		builder.append(line+System.lineSeparator());
		return this;
	}
	
	public InputStream build(){
		String inString = builder.toString();
		InputStream inputStream = new ByteArrayInputStream(inString.getBytes());
		return inputStream;
	}

}
