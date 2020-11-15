package com.desafio.analise.dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.desafio.analise.negocio.modelos.*;
import org.springframework.stereotype.Repository;

@Repository
public class LerArquivos {

    private ArrayList<Cliente> clientes = new ArrayList();
//    private ArrayList<Item> itens = new ArrayList();
    private ArrayList<Venda> vendas = new ArrayList();
    private ArrayList<Vendedor> vendedores = new ArrayList();

    public void LerArquivos(){
    }

    public void salvarArquivo() throws IOException {
        File arquivo = new File(System.getenv("HOMEPATH")+"\\data\\out\\{flat_file_name}.done.dat");
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(arquivo));
        dos.writeChars("Quantidade de Clientes : " + quantidadeClientes() + "\n");
        dos.writeChars("Quantidade de Vendedor : " + quantidadeVendedor() + "\n");
        dos.writeChars("ID da Venda mais cara : " + vendaMaisCara() + "\n");
        dos.writeChars("Pior Vendedor : " + piorVendedor());
        dos.close();
    }

    public void lerArquivosDAT() throws FileNotFoundException {
        //Metodo onde faz à leitura do arquivo e a regra de negócio para separar por tipos de dados diferentes.
        File arquivos[];
        File diretorio = new File(System.getenv("HOMEPATH")+"\\data\\in");
        arquivos = diretorio.listFiles();

        for(int i=0;i< arquivos.length;i++){
            if(arquivos[i].getName().endsWith(".dat")){
                System.out.println(arquivos[i]);
                if(arquivos[i].canRead()){
                    Scanner myReader = new Scanner(arquivos[i]);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        //System.out.println(data);
                        String array[] = data.split("ç");
                        if(array.length>0){
                            if(array[0].equals("001")){
                                //vendedor
                                AtomicBoolean aux = new AtomicBoolean(false);
                                String cpf = array[1];
                                String name = array[2];
                                int salary = Integer.parseInt(array[3]);

                                this.vendedores.forEach(vendedor -> {
                                    if(vendedor.getCpf().equals(cpf)){
                                        //significa que este vendedor já foi cadastrado e não será necessário cadastrar novamente
                                        aux.set(true);
                                    }
                                });
                                if(aux.get()==false){
                                    //adicionar vendedor
                                    Vendedor vendedor = new Vendedor(cpf, name, salary);
                                    this.vendedores.add(vendedor);
                                    System.out.println(vendedor);
                                }

                            }else if(array[0].equals("002")){
                                //cliente
                                AtomicBoolean aux = new AtomicBoolean(false);

                                String cnpj = array[1];
                                String name = array[2];
                                String businessArea = array[3];
                                this.clientes.forEach(cliente -> {
                                    if(cliente.getCnpj().equals(cnpj)){
                                        //significa que este cliente já foi cadastrado e não será necessário cadastrar novamente
                                        aux.set(true);
                                    }

                                });
                                if(aux.get()==false){
                                    //adicionar cliente
                                    Cliente cliente = new Cliente(cnpj, name, businessArea);
                                    this.clientes.add(cliente);
                                    System.out.println(cliente);
                                }

                            }else if(array[0].equals("003")){
                                //vendas
                                ArrayList<Item> itens = new ArrayList();
                                int id = Integer.parseInt(array[1]);
                                String listaItens[]= array[2].substring(1, array[2].length()-1).split(",");
                                String salesmanName = array[3];
                                for(int j=0;j< listaItens.length;j++){
                                    String dadosItem[] = listaItens[j].split("-");
                                    int itemID = Integer.parseInt(dadosItem[0]);
                                    int itemQuantity = Integer.parseInt(dadosItem[1]);
                                    double itemPrice = Double.parseDouble(dadosItem[2]);
                                    Item item = new Item(itemID,itemQuantity,itemPrice);
                                    itens.add(item);
                                }
                                AtomicBoolean aux = new AtomicBoolean(false);
                                this.vendas.forEach(venda -> {
                                    if(venda.getSalesmanName().equals(salesmanName)){
                                        venda.adicionarItem(itens);
                                        aux.set(true);
                                        System.out.println(venda);
                                    }
                                });
                                if(aux.get() ==false){
                                    Venda venda = new Venda(id,salesmanName,itens);
                                    this.vendas.add(venda);
                                    System.out.println(venda);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int quantidadeClientes(){

        return clientes.size();
    }

    public int vendaMaisCara(){
        int idAux = 0;
        double lucroAux = 0;

        for(int i = 0; i<vendas.size();i++){
            if(vendas.get(i).lucro()>lucroAux){
                idAux = vendas.get(i).getId();
                lucroAux = vendas.get(i).lucro();
            }
        }
        return idAux;
    }



    public Vendedor piorVendedor(){
        //int posicaoVenda;
        int posicaoVendedor = 0 ; //metodo colocará a posição do pior vendedor
        double lucroAux = -123.7654;
        for(int i = 0; i<this.vendedores.size(); i++){
            for(int j = 0; j<this.vendas.size();j++){
                if(this.vendedores.get(i).getName().equals(this.vendas.get(j).getSalesmanName())){
                    if(lucroAux == -123.7654){
                        lucroAux = this.vendas.get(j).lucro();
                        posicaoVendedor = i;
                        //posicaoVenda = j;
                    }
                    else if(lucroAux>this.vendas.get(j).lucro()){
                        lucroAux = this.vendas.get(j).lucro();
                        posicaoVendedor = i;
                        //posicaoVenda = j;
                    }
                }
            }
        }
        return this.vendedores.get(posicaoVendedor);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public int quantidadeVendedor(){
        return vendedores.size();
    }

    @Override
    public String toString() {
        return "LerArquivos{" +
                "clientes=" + clientes +
                ", vendas=" + vendas +
                ", vendedores=" + vendedores +
                '}';
    }
}
