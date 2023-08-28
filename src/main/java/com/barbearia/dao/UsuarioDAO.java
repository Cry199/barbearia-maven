package com.barbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barbearia.enums.TipoUsuario;
import com.barbearia.model.Usuarios;
import com.barbearia.util.ConnectionFactory;

public class UsuarioDAO
{
    private Connection connection;

    public void inserir(Usuarios usuario) 
    {
        String sql = 
        "INSERT INTO usuarios (nome, sobrenome, email, senha, telefone, endereco, cpf, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();


            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getEndereco());
            stmt.setString(7, usuario.getCpf());
            stmt.setString(8, usuario.getTipo().toString());

            stmt.executeUpdate(); 

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                usuario.setId(rs.getInt(1));
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        /*
            Há algumas diferenças entre o método executeUpdate() e o padrão de usar execute() + close() em statements JDBC:


            executeUpdate():
            É usado para queries que modificam dados (INSERT, UPDATE, DELETE).
            Retorna um int com o número de linhas afetadas.

            execute():
            Pode ser usado tanto para SELECT quanto para instruções que modificam dados.
            Não retorna nada.             
            Precisa fechar o statement depois com close().

            Fechamento do Statement:
            executeUpdate() fecha o statement automaticamente.
            Com execute() precisamos fechar explicitamente com close()

            Para SELECTs, use:

            stmt = conn.createStatement();
            stmt.execute(sql);
            //processa resultado
            stmt.close();

            Para INSERT, UPDATE, etc use:
            stmt = conn.prepareStatement(sql);
            int linhas = stmt.executeUpdate();
        */
    }
    
    public void atualizar(Usuarios usuario) 
    {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

        try 
        {
            this.connection = new ConnectionFactory().getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate(); 
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
    }

    public void excluir(int id) 
    {
        String sql = "DELETE FROM usuarios WHERE id = ?";
    
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

    public Usuarios buscarPorId(int id) 
    {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        
        Usuarios usuario = null;

        try 
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) 
            {
              usuario = new Usuarios();
              
              usuario.setId(rs.getInt("id"));
              usuario.setNome(rs.getString("nome")); 
            }
            
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }
          
        return usuario;
    }

    public List<Usuarios> buscarPorTipo(TipoUsuario tipo) 
    {
        List<Usuarios> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios WHERE tipo = ?";

        try 
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipo.toString()); 
            ResultSet rs = stmt.executeQuery();
        
            while(rs.next())
            {
              Usuarios usuario = new Usuarios();
              usuario.setId(rs.getInt("id"));
              usuario.setNome(rs.getString("nome"));
        
              usuarios.add(usuario);
            }
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);    
        }

        return usuarios;    
    }

}
