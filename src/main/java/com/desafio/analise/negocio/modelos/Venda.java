package com.desafio.analise.negocio.modelos;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@EntityScan
public class Venda {
    private int id;
    private String salesmanName;
    private ArrayList<Item> itens;

    public Venda(int id, String salesmanName, ArrayList<Item> itens){
        this.id = id;
        this.salesmanName = salesmanName;
        this.itens = itens;
    }

    public Venda(){}

    public double lucro(){
        //soma do lucro
        double lucro = 0;

        for(int i=0;i<this.itens.size();i++){
            lucro = lucro + this.itens.get(i).getPrice();
        }
        return lucro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public void adicionarItem(ArrayList<Item> adicionarItens){
        adicionarItens.forEach(item -> {
            this.itens.add(item);
        });
    }

    @Override
    public String toString() {
        return "Vendas{" +
                "id=" + id +
                ", salesmanName='" + salesmanName + '\'' +
                ", itens=" + itens +
                '}';
    }
}
