package com.teng.java.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class Serialization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Serialization.class);
	private int num = 10;
	
	public static void main(String[] args){
		try{
			FileOutputStream fos = new FileOutputStream("serialization.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new Serialization());
			oos.flush();
			oos.close();
		}catch(Exception e){
			logger.error("serialization error", e);
		}
		
		try{
			FileInputStream fis = new FileInputStream("serialization.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Serialization sObject = (Serialization) ois.readObject();
			logger.info(sObject.getNum());
		}catch(Exception e){
			logger.error("deserialization error", e);
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
