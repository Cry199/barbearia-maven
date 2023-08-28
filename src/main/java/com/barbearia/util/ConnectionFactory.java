package com.barbearia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory 
{
    public Connection getConnection() throws SQLException
	{
		try 
        {
			Class.forName("com.mysql.cj.jdbc.Driver");

			return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/barbershopdb", "root", "Caua201leu1@");
		} 
        catch (ClassNotFoundException  e) 
        {
			throw new RuntimeException(e);
		}
	}    
}