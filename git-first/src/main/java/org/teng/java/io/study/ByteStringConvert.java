package org.teng.java.io.study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import sun.nio.cs.StreamDecoder;
import sun.nio.cs.StreamEncoder;

public class ByteStringConvert {
	private FileInputStream fis = null;
	private StreamDecoder streamDecoder = null;
	private InputStreamReader isr = null;
	
	private FileOutputStream fos = null;
	private StreamEncoder streamEncoder = null;
	private OutputStreamWriter osw = null;
	
	public boolean init(String inPath, String outPath, String charsetName) throws FileNotFoundException{
		if(inPath == null && outPath == null || charsetName == null){
			return false;
		}
		
		Charset charset = Charset.forName(charsetName);
		if(inPath != null){
			fis = new FileInputStream(inPath);
			streamDecoder = StreamDecoder.forInputStreamReader(fis, this, charset);
			isr = new InputStreamReader(fis, Charset.forName("utf-8"));
		}
		
		if(outPath != null){
			fos = new FileOutputStream(outPath);
			streamEncoder = StreamEncoder.forOutputStreamWriter(fos, this, charset);
			osw = new OutputStreamWriter(fos, Charset.forName("iso-8859-1"));//charset
		}
		
		return true;
	}
	
	public void clear() throws IOException{
		if(isr != null)
			isr.close();
		if(osw != null)
			osw.close();
		if(fis != null)
			fis.close();
		if(fos != null)
			fos.close();
	}
	
	public static void main(String[] args) throws IOException{
		ByteStringConvert bsc = new ByteStringConvert();
		Logger logger = Logger.getLogger(bsc.getClass());
		try {
			bsc.init("in.txt", "out.txt", "utf-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(bsc.getIsr());
		BufferedWriter bw = new BufferedWriter(bsc.getOsw());
		String line = null;
		int lineCount = 0;
		while((line = br.readLine()) != null){
			logger.info("line " + lineCount + " : " + line);
			bw.write(line + "\r\n");
			lineCount++;
		}
		bw.flush();
		bsc.clear();
	}

	public FileInputStream getFis() {
		return fis;
	}

	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}

	public StreamDecoder getStreamDecoder() {
		return streamDecoder;
	}

	public void setStreamDecoder(StreamDecoder streamDecoder) {
		this.streamDecoder = streamDecoder;
	}

	public InputStreamReader getIsr() {
		return isr;
	}

	public void setIsr(InputStreamReader isr) {
		this.isr = isr;
	}

	public FileOutputStream getFos() {
		return fos;
	}

	public void setFos(FileOutputStream fos) {
		this.fos = fos;
	}

	public StreamEncoder getStreamEncoder() {
		return streamEncoder;
	}

	public void setStreamEncoder(StreamEncoder streamEncoder) {
		this.streamEncoder = streamEncoder;
	}

	public OutputStreamWriter getOsw() {
		return osw;
	}

	public void setOsw(OutputStreamWriter osw) {
		this.osw = osw;
	}
	
	
	
}
