package com.simplilearn.productretrieval;

public class Constants {

//	Database connectivity details
	public static String DB_NAME = "jdbc:mysql://localhost:3306/product?serverTimezone=GMT";
	public static String DB_USER = "root1";
	public static String DB_PWD  = "root123";
	
//	SQL - Get Product details by Product ID
	public static String sqlGET_PRODUCT_BY_ID = "SELECT * FROM PRODUCT_DETAIL WHERE PRODUCT_ID = (?)";
}
