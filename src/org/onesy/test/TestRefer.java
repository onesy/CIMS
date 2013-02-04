package org.onesy.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestRefer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties pp = null ;
		TestRefer tRefer = new TestRefer();
		tRefer.kk(pp);
		
		if(pp == null){
			System.err.println("null!");
		}

	}
	
	public void kk(Properties pp){
		pp = new Properties();
		try {
			pp.load(new FileInputStream(new File("test.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
