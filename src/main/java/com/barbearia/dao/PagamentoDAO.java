package com.barbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.barbearia.model.Pagamento;
import com.barbearia.util.ConnectionFactory;

public class PagamentoDAO 
{
    private Connection connection;


    public void inserir(Pagamento pagamento) 
    {
        String sql =
        "INSERT INTO pagamentos (cliente_id, agenda_id, valor, data_pagto) VALUES (?, ?, ?, ?)";
        
        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, pagamento.getClienteId());
            stmt.setInt(2, pagamento.getAgendaId());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setDate(4, new java.sql.Date(pagamento.getDataPagamento().getTime()));

            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }

    public void atualizar(Pagamento pagamento) 
    {
        String sql =
        "UPDATE pagamentos SET cliente_id = ?, agenda_id = ?,  valor = ?, data_pagto = ? WHERE id = ?";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, pagamento.getClienteId());
            stmt.setInt(2, pagamento.getAgendaId());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setDate(4, new java.sql.Date(pagamento.getDataPagamento().getTime()));
            stmt.setInt(5, pagamento.getId());

            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }
  
    public void excluir(int id) 
    {
        String sql = "DELETE FROM pagamentos WHERE id = ?";
    
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
   
    public Pagamento buscarPorId(int id) 
    {
        String sql = "SELECT * FROM pagamentos WHERE id = ?";

        Pagamento pagamento = null;

        try 
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) 
            {
                pagamento = new Pagamento();
                
                pagamento.setId(rs.getInt("id"));
                pagamento.setClienteId(rs.getInt("cliente_id"));
                pagamento.setAgendaId(rs.getInt("agenda_id"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setDataPagamento(rs.getDate("data_pagto"));
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return pagamento;
    }
  
    public List<Pagamento> listarTodos() 
    {
        String sql = "SELECT * FROM pagamentos";

        List<Pagamento> pagamentos = null;

        try
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                Pagamento pagamento = new Pagamento();
                
                pagamento.setId(rs.getInt("id"));
                pagamento.setClienteId(rs.getInt("cliente_id"));
                pagamento.setAgendaId(rs.getInt("agenda_id"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setDataPagamento(rs.getDate("data_pagto"));

                pagamentos.add(pagamento);
            }
        }
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
        
        return pagamentos;
    }    
}
