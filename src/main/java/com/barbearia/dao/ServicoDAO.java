package com.barbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.barbearia.model.Servicos;
import com.barbearia.util.ConnectionFactory;

public class ServicoDAO
{
    private Connection connection;

    public void inserir(Servicos servico) 
    {
        String sql =
        "INSERT INTO servicos (nome, preco)VALUES (?, ?)";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, servico.getNome());
            stmt.setDouble(2, servico.getPreco());

            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }

    public void atualizar(Servicos servico) 
    {
        String sql =
        "UPDATE servicos SET nome = ?, preco = ? WHERE id = ?";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, servico.getNome());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getId());

            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }
  
    public void excluir(int id) 
    {
        String sql = "DELETE FROM servicos WHERE id = ?";
    
        try 
        {
          PreparedStatement stmt = connection.prepareStatement(sql);
          stmt.setInt(1, id);
          stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
          throw new RuntimeException(e);    
        }
    }
   
    public Servicos buscarPorId(int id) 
    {
        String sql = "SELECT * FROM servicos WHERE id = ?";

        Servicos servico = null;

        try 
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) 
            {
              servico = new Servicos();
              
              servico.setId(rs.getInt("id"));
              servico.setNome(rs.getString("nome")); 
              servico.setPreco(rs.getDouble("preco")); 
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return servico;
    }
  
    public List<Servicos> listarTodos() 
    {
        String sql = "SELECT * FROM servicos";

        List<Servicos> servicos = null;

        try 
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) 
            {
                Servicos servico = new Servicos();
                
                servico.setId(rs.getInt("id"));
                servico.setNome(rs.getString("nome")); 
                servico.setPreco(rs.getDouble("preco")); 

                servicos.add(servico);
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return servicos;
    }
}
