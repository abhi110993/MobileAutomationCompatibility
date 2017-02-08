package com.qait.automation.utils;

import java.sql.*;




public class DataBaseConnecter {

	Connection con;
	ResultSet rst;
	Statement stat;
	ResultSetMetaData rsMdata;
	
   public void connectToDataBase(String host, String databaseName, String username, String password){
	   try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+databaseName, username, password);
		stat = con.createStatement();
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
   }
   
   
   public ResultSet getResultSetOnExecutingASelectQuery(String query){
	   try {
		   	rst = stat.executeQuery(query);
		   
	   } catch (SQLException e) {
			e.printStackTrace();
		}
	   return rst;
   }
   
//   public Map<String, List<String>> executeQueryAndgetGeneratedResult(String selectQuery){
//	   Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
//	   int numberOfColumnsReturned = 0;
//	   try {
//		   	rst = stat.executeQuery(selectQuery);
//		   	rsMdata = rst.getMetaData();
//		   	numberOfColumnsReturned = rsMdata.getColumnCount();
//		   
//		   	for(int i=0;i<numberOfColumnsReturned;i++){
//		   		
//		   	}
//		   
//		   
//		   
//		   
//		   
//	   } catch (SQLException e) {
//			e.printStackTrace();
//		}
//	   
//   }
   
 
}
