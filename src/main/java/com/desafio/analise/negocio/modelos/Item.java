package com.desafio.analise.negocio.modelos;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Item {
    private int id;
    private int quantidade;
    private double price;

    public Item(int id, int quantidade, double price){
        this.id = id;
        this.quantidade = quantidade;
        this.price = price;
    }
    public Item(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", price=" + price +
                '}';
    }
}
