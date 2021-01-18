package com.techelevator.Logger;

import java.awt.List;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Logger implements Closeable {
	
	private File logFile;
	private PrintWriter writer;
	

	public Logger(String path) throws IOException {
		this.logFile = new File(path);
		if(this.logFile.exists()) {
			writer = new PrintWriter(new FileWriter(this.logFile, true));
		} else {
			writer = new PrintWriter(this.logFile);
		}
	}
	
	public void write(String logMessage) {
		writer.println(logMessage);
		writer.flush();
	}

	@Override
	public void close() throws IOException {
	}
}
