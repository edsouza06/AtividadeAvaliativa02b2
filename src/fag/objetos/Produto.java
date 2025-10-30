package fag.objetos;

public class Produto {
	private String nome;
	private double preco;
	private int estoqueinicial;
	
	public Produto (String nome, double preco, int estoqueinicial){
		setNome(nome);
		setPreco(preco);
		setEstoqueinicial(estoqueinicial);
	}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) this.nome = nome;
	}
	
	public double getPreco() {return preco;}
	public void setPreco(double preco) {
		if(preco > 0)this.preco = preco;
	}
	
	public int getEstoqueinicial() {return estoqueinicial;}
	public void setEstoqueinicial(int estoqueinicial) {
		if(estoqueinicial > 0)this.estoqueinicial = estoqueinicial;
	}
	
	public void listarProduto() {
		System.out.printf("Produto: %s / Preço:  %.2f / Estoque: %d \n", getNome(), getPreco(), getEstoqueinicial());
	}
	
	public String toString() {
		return String.format("%s (R$ %.2f)", nome, preco);
	}

}
