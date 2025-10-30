package fag.objetos;

public class Carrinho {

	private Produto produto;
	private int quantidade;
	
	public Produto getProduto() {return produto;}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {return quantidade;}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Carrinho(Produto produto, int quantidade) {
		setProduto(produto);
		setQuantidade(quantidade);
	}
	
	public void listarCarrinho() {
		System.out.printf("Produto: %s / Quantidade: %d \n", getProduto(), getQuantidade());
	}
	
}
