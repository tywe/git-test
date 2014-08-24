package org.teng.java.file.study;

import org.apache.log4j.Logger;

public class FileHelper {
	private static Logger logger = Logger.getLogger(FileHelperTest.class); 
	public static void main(String[] args){
		logger.info("app start");
		logger.error("app start", new Exception("app exception"));
	}
}
