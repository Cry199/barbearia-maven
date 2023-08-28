package com.barbearia.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.barbearia.util.ConnectionFactory;

public class TestaConexao
{
    public static void main(String[] args) {
		
		try {
			Connection connection = new ConnectionFactory().getConnection();
			connection.close();
            
			System.out.println("Conexão aberta!");
		} catch (SQLException e) 
        {
			e.printStackTrace();
		}
		
	}
}