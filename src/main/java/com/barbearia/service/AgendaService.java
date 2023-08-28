package com.barbearia.service;

import com.barbearia.dao.AgendaDAO;
import com.barbearia.model.Agenda;

public class AgendaService 
{
    private AgendaDAO agendaDAO;

    public void inserir(Agenda agenda) 
    {
        //Validações
        agendaDAO.inserir(agenda);
    }

    public void atualizar(Agenda agenda) 
    {
        //Validações
        
        agendaDAO.atualizar(agenda);
    }

    public void excluir(int id) 
    {
        //Validações

        agendaDAO.excluir(id);
    }
}
