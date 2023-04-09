package com.simplilearn.productretrieval;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.jdbc.Driver;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Product() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String searchProductID=request.getParameter("searchProduct");
		
        try {
            // 1. Register Connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Get Connection
            Connection connection = DriverManager.getConnection(
            		Constants.DB_NAME, Constants.DB_USER, Constants.DB_PWD
            );

            // 3. Create Statement
            PreparedStatement preparedStmnt = connection.prepareCall(Constants.sqlGET_PRODUCT_BY_ID);
            preparedStmnt.setString(1, searchProductID);
          
	        response.setContentType("text/html");  
	        
	        request.getRequestDispatcher("product.html").include(request, response);
	        
	        ResultSet resultSet1 = preparedStmnt.executeQuery();
       
            if (resultSet1.next()) {
            	out.print("<br/>" + "Product Id " + searchProductID + " found" + "<br/>");
            	out.print("<br/>" + "Prod ID: " + resultSet1.getString("PRODUCT_ID"));
            	out.print("<br/>" + "Name   : " + resultSet1.getString("PROD_NAME"));
            	out.print("<br/>" + "Color  : " + resultSet1.getString("PROD_COLOR"));
            	out.print("<br/>" + "Desc   : " + resultSet1.getString("PROD_DESC"));
            	out.print("<br/>" + "Stock  : " + resultSet1.getString("PROD_QTY"));
            	out.print("<br/>" + "Price  : " + resultSet1.getString("PROD_PRICE"));
            }else {
	        	out.print("<br/>" + "Product Id " + searchProductID + " not found" + "<br/>");
	        }
  
	        out.close(); 
	        
        } catch (Exception e){
        	System.out.println("database error");
            System.out.println(e);
        };	 
		
	};

}
