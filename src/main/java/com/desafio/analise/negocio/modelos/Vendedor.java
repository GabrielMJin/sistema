package com.desafio.analise.negocio.modelos;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Vendedor {
    private String cpf;
    private String name;
    private double salary;

    public Vendedor(String cpf, String name, double salary){
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }
    public Vendedor(){

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
