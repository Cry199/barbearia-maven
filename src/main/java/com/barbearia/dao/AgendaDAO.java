package com.barbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.barbearia.model.Agenda;
import com.barbearia.util.ConnectionFactory;

public class AgendaDAO
{
    private Connection connection;

    public void inserir(Agenda agenda) 
    {
        String sql = 
        "INSERT INTO agendas (cliente_id, funcionario_id, servico_id, data_hora, status, preco) VALUES (?, ?, ?, ?, ?, ?)";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, agenda.getClienteId());
            stmt.setInt(2, agenda.getFuncionarioId());
            stmt.setInt(3, agenda.getServicoId());
            stmt.setDate(4, new java.sql.Date(agenda.getDataHora().getTime()));
            stmt.setString(5, agenda.getStatus());
            stmt.setDouble(6, agenda.getPreco());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                agenda.setId(rs.getInt(1));
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
        
    }

    public void atualizar(Agenda agenda) 
    {
        String sql =
        "UPDATE agendas SET cliente_id = ?, funcionario_id = ?, servico_id = ?,   data_hora = ?, status = ?, preco = ? WHERE id = ?";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, agenda.getClienteId());
            stmt.setInt(2, agenda.getFuncionarioId());
            stmt.setInt(3, agenda.getServicoId());
            stmt.setDate(4, new java.sql.Date(agenda.getDataHora().getTime()));
            stmt.setString(5, agenda.getStatus());
            stmt.setDouble(6, agenda.getPreco());
            stmt.setInt(7, agenda.getId());

            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }

    public void excluir(int id) 
    {
        String sql = "DELETE FROM agendas WHERE id = ?";
        
        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }

    public Agenda buscarPorId(int id) 
    {
        String sql = "SELECT * FROM agendas WHERE id = ?";

        Agenda agenda = null;

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                agenda = new Agenda();

                agenda.setId(rs.getInt("id"));
                agenda.setClienteId(rs.getInt("cliente_id"));
                agenda.setFuncionarioId(rs.getInt("funcionario_id"));
                agenda.setServicoId(rs.getInt("servico_id"));
                agenda.setDataHora(rs.getDate("data_hora"));
                agenda.setStatus(rs.getString("status"));
                agenda.setPreco(rs.getDouble("preco"));
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return null;
    }

    public List<Agenda> buscarPorFuncionario(int funcionarioId) 
    {
        String sql = "SELECT * FROM agendas WHERE funcionario_id = ?";

        List<Agenda> agendas = new ArrayList<>();

        try
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                Agenda agenda = new Agenda();

                agenda.setId(rs.getInt("id"));
                agenda.setClienteId(rs.getInt("cliente_id"));
                agenda.setFuncionarioId(rs.getInt("funcionario_id"));
                agenda.setServicoId(rs.getInt("servico_id"));
                agenda.setDataHora(rs.getDate("data_hora"));
                agenda.setStatus(rs.getString("status"));
                agenda.setPreco(rs.getDouble("preco"));

                agendas.add(agenda);
            }
        }
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return agendas;
    } 
    
    // Agenda com o nome do cliente, o nome do funcionário e a hora da agenda: //
    public List<Agenda> listarAgendas()
    {
        String sql = 
        "SELECT CONCAT(c.nome, ' ', c.sobrenome) AS nome_cliente," + 
        "CONCAT(f.nome, ' ', f.sobrenome) AS nome_funcionario," +
        "a.data_hora AS hora_agenda FROM agendas a " + 
        "JOIN usuarios c ON a.cliente_id = c.id " + 
        "JOIN  usuarios f ON a.funcionario_id = f.id";

        List<Agenda> agendas = new ArrayList<>();

        try
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                Agenda agenda = new Agenda();  
                agenda.setNomeCliente(rs.getString("nome_cliente"));
                agenda.setNomeFuncionario(rs.getString("nome_funcionario"));
                agenda.setDataHora(rs.getDate("hora_agenda"));
      
                agendas.add(agenda);
            }
        }
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return agendas;
    }
}
