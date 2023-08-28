package com.barbearia.model;

import java.util.Date;

public class Agenda 
{
    private int id;
    private int clienteId; 
    private int funcionarioId;
    private int servicoId;
    private Date dataHora;
    private String status;
    private double preco;

    /// 

    private String clienteNome;
    private String funcionarioNome;
    
    
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeCliente() {
        return clienteNome;
    }

    public String getNomeFuncionario() {
        return funcionarioNome;
    }

    public void setNomeCliente(String nomeCliente) 
    {
        this.clienteNome = nomeCliente;
    }

    public void setNomeFuncionario(String nomeFuncionario) 
    {
        this.funcionarioNome = nomeFuncionario;
    } 
}
