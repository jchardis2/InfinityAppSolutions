package com.infinityappsolutions.webvideo.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.infinityappsolutions.webvideo.test.DBBuilder;
import com.infinityappsolutions.webvideo.test.TestDataGenerator;

//import com.infinityappsolutions.webvideo.test.DBBuilder;
//import com.infinityappsolutions.webvideo.test.TestDataGenerator;

public class BuildWithTestData {

	public static void main(String args[]) {
		try {
			DBBuilder.main(args);
			TestDataGenerator.addAllFromFresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
