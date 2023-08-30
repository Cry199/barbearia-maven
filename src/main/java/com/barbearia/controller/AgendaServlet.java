package com.barbearia.controller;

import java.io.IOException;
import java.util.List;

import com.barbearia.dao.AgendaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.inject.Inject;

import com.barbearia.model.Agenda;


@WebServlet("views/public/agenda")
public class AgendaServlet extends HttpServlet {

    @Inject
    private AgendaDAO agendaDAO; //injetar dao

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        List<Agenda> agendas = agendaDAO.listarAgendas();

        //enviar lista para request
        request.setAttribute("agendas", agendas);

        //encaminhar para a página JSP
        request.getRequestDispatcher("/views/public/agenda.jsp").forward(request, response);
    }
}