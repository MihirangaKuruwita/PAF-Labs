package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Item {
public Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf",
"root", "");
//For testing
System.out.print("Successfully connected");
}
catch(Exception e)
{
e.printStackTrace();
}
return con;
}
public String insertItem(String code, String name, String price, String desc) {
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database";
}
// create a prepared statement
String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"+ " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, code);
preparedStmt.setString(3, name);
preparedStmt.setDouble(4, Double.parseDouble(price));
preparedStmt.setString(5, desc);
//execute the statement
preparedStmt.execute();
con.close();
output = "Inserted successfully";
}
catch (Exception e)
{
output = "Error while inserting";
System.err.println(e.getMessage());
}
return output;
}
}