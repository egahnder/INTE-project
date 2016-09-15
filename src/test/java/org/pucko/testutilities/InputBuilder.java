package org.pucko.testutilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputBuilder {
	private StringBuilder builder;
	
	public InputBuilder(){
		this.builder = new StringBuilder();
	}
	
	public InputBuilder addLine(String line){
		builder.append(line).append(System.lineSeparator());
		return this;
	}
	
	public InputStream build(){
		String inString = builder.toString();
		return new ByteArrayInputStream(inString.getBytes());
	}

}
