package model;

public class Modelmotorista {

    private String nome;
    private String cpf;
    private int id;
    private String carro;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCpf(String Cpf) {
        this.cpf = Cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public int getId() {
        return this.id;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public String getCarro() {
        return this.carro;
    }

    @Override
    public String toString() {
        return " " + this.nome; // + " cpf = " + this.cpf + " carro = " + this.carro +  "}";
    }
}
