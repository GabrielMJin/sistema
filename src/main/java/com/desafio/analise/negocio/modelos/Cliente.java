package com.desafio.analise.negocio.modelos;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Cliente {
    private String cnpj;
    private String name;
    private String businessArea;

    public Cliente(String cnpj, String name, String businessArea){
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }
    public Cliente(){}

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea=" + businessArea +
                '}';
    }
}
