package com.barbearia.service;

import com.barbearia.dao.UsuarioDAO;
import com.barbearia.model.Usuarios;

public class UsuarioService 
{
    private UsuarioDAO usuarioDAO;
    
    public void inserir(Usuarios usuario) 
    {
        //Validações
        usuarioDAO.inserir(usuario);
    }

    public void atualizar(Usuarios usuario) 
    {
        //Validações
        
        usuarioDAO.atualizar(usuario);
    }

    public void excluir(int id) 
    {
        //Validações

        usuarioDAO.excluir(id);
    }
}
