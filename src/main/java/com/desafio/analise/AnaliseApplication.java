package com.desafio.analise;

import com.desafio.analise.dados.LerArquivos;
import com.desafio.analise.negocio.modelos.*;
import com.desafio.analise.negocio.servico.ClienteServico;
import com.desafio.analise.negocio.servico.ItemServico;
import com.desafio.analise.negocio.servico.VendasServico;
import com.desafio.analise.negocio.servico.VendedorServico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class AnaliseApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AnaliseApplication.class, args);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                AnaliseApplication.class.getPackage().getName());

        LerArquivos lerArquivos = applicationContext.getBean(LerArquivos.class);
        lerArquivos.lerArquivosDAT();

        //Instanciando Vendedor
        Vendedor vendedor = new Vendedor();
        VendedorServico vendedorServico = applicationContext.getBean(VendedorServico.class);
        vendedorServico.cadastrar(vendedor);

        //instanciando Cliente
        Cliente cliente = new Cliente();
        ClienteServico clienteServico = applicationContext.getBean(ClienteServico.class);
        clienteServico.cadastrarCliente(cliente);

        //Instanciando Vendas
        Venda venda = new Venda();
        VendasServico vendasServico = applicationContext.getBean(VendasServico.class);
        vendasServico.cadastrar(venda);

        //Instanciando Item
        Item item = new Item();
        ItemServico itemServico = applicationContext.getBean(ItemServico.class);
        itemServico.cadastrar(item);

        System.out.println(lerArquivos.vendaMaisCara());

        applicationContext.close();

        lerArquivos.salvarArquivo();

    }
}
