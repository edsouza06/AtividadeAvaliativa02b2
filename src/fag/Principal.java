package fag;

import fag.objetos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	
	static Scanner scanner = new Scanner(System.in);
	static List<Produto> estoque = new ArrayList<>();
	static List<Carrinho> carrinho = new ArrayList<>();
	static Caixa caixa = new Caixa(5,5,5);
	
	
	public static void main(String[]args) {
		chamarMenu();
	}
	
	public static void chamarMenu() {
		int opc=1000;
		do {
			System.out.println("==Menu==");
			System.out.println("1 - Cadastrar Produto");
			System.out.println("2 - Listar Produtos");
			System.out.println("3 - Realizar Venda");
			System.out.println("4 - Exibir Saldo");
			System.out.println("0 - Sair");
			opc = scanner.nextInt();
			scanner.nextLine();
			validarOpcao(opc);
			} while(opc != 0);	
	}
	
	public static void validarOpcao(int opc) {
		switch (opc) {
		case 1:
			cadastrarProduto();
			break;
		case 2:
			listarProdutos();
			break;
		case 3:
			realizarVenda();
			break;
		case 4:
			exibirSaldo();
			break;
		case 0:
			System.out.println("Saindo...");
			break;
		default:
			if(opc != 0) System.out.println("Selecione uma opção válida");
			break;
		}
	}
	
	public static void realizarVenda() {
		listarProdutos();
		conferirEstoque();
		int opc = 1000;
		int qtd = 0;
		do {
			System.out.println("Digite o Código do Produto ou -1 para finalizar");
			opc = scanner.nextInt();
			
			if(opc == -1) {
				finalizarVenda();
				break;
			}
			
			Produto produtoSelecionado = estoque.get(opc-1);
			
			System.out.println("Digite a Quantidade do Produto");
			qtd = scanner.nextInt();
			
			
			if(qtd <= 0) {
				System.out.println("Quantidade Inválida!");
				return;
			}
			
			if(qtd > produtoSelecionado.getEstoqueinicial()) {
				System.out.println("Estoque insuficiente!");
				return;
			}
			
			Carrinho itemCarrinho = new Carrinho(produtoSelecionado, qtd);
			carrinho.add(itemCarrinho);
			
			System.out.println("Produto adicionado a venda");
			
		} while(opc != 0);
	}
	
	public static void finalizarVenda() {
		listarProdutoCarrinho();
		double valortotal = retornarTotalVenda();
		double troco = realizarPagamento(valortotal);
		retornarTroco(troco);
		subtrairEstoque();
		carrinho.removeAll(carrinho);
	}
	
	public static void subtrairEstoque() {
		for (Carrinho item : carrinho) {
			Produto produto = item.getProduto();
			int novaqtd = produto.getEstoqueinicial() - item.getQuantidade();
			produto.setEstoqueinicial(novaqtd);
		}
		System.out.println("Estoque atualizado");
	}
	
	public static double realizarPagamento(double valortotal) {
		System.out.println("Quantos que o cliente deu de dinheiro?");
		double clientevalor = scanner.nextDouble();
		
		double troco = clientevalor - valortotal;
		
		return troco;
		
	}
	
	public static double retornarTotalVenda() {
		System.out.printf("Valor total: ");
		double valortotal=0;
		double x=0;
		for (Carrinho item : carrinho) {
			x = item.getProduto().getPreco() * item.getQuantidade();
			valortotal+=x;
		}
		System.out.printf("%.2f \n", valortotal);
		
		return valortotal;
	}
	
	public static void retornarTroco(double troco) {
		int cinquenta=0;
		int vinte=0;
		int dez=0;
		System.out.println("Troco:");
		if(troco >= 50) {
			cinquenta = (int) (troco / 50);
			if(caixa.getCinquenta() >= cinquenta) {
				caixa.removerCinquenta(cinquenta);
			} else {
				System.out.println("Notas de 50 insuficientes"); 
				return;
			}
			troco = troco % 50;
		}
		if(troco >= 20) {
			vinte = (int) (troco / 20);
			if(caixa.getVinte() >= vinte) {
				caixa.removerVinte(vinte);
			} else {
				System.out.println("Notas de 20 insuficientes"); 
				return;
			}
			troco = troco % 20;
		}
		if(troco >= 10) {
			dez = (int) (troco / 10);
			if(caixa.getDez() >= dez) {
				caixa.removerDez(dez);
			} else {
				System.out.println("Notas de 10 insuficientes"); 
				return;
			}
			troco = troco % 10;
		}

		if(cinquenta > 0)System.out.printf("Notas de 50 = %d \n", cinquenta);
		if(vinte > 0)System.out.printf("Notas de 20 = %d \n", vinte);
		if(dez > 0)System.out.printf("Notas de 10 = %d \n", dez);
		if(cinquenta <= 0 && vinte <=0 && dez <=0) System.out.println("Sem retorno de troco!");
	}
	
	public static void conferirEstoque() {
		if(estoque.isEmpty()) {
			System.out.println("Sem produto cadastrado!");
			return;
		}
	}
	
	public static void exibirSaldo() {
		System.out.println("Exibindo saldo do Caixa...");
		caixa.exibirCaixa();
	}
	
	public static void cadastrarProduto() {	
		System.out.println("Qual é o Nome?");
		String nome = scanner.nextLine();
		
		for (Produto prod : estoque) {
			if(prod.getNome().equalsIgnoreCase(nome)) {
				System.out.println("Já existe um produto com este nome!");
				return;
			}
		}
		
		System.out.println("Qual é o Estoque?");
		int estoqueinicial = scanner.nextInt();
		
		System.out.println("Qual é o Preço do Produto?");
		double preco = scanner.nextDouble();
		
		scanner.nextLine();
		
		Produto produto = new Produto(nome, preco, estoqueinicial);
		
		estoque.add(produto);
		
		System.out.println("Produto adicionado com sucesso!");
	}
	
	public static void listarProdutos() {
		for(int i=0; i < estoque.size();i++) {
			System.out.printf("%d - ", i+1);
			estoque.get(i).listarProduto();
		}
	}
	
	public static void listarProdutoCarrinho() {
		System.out.println("Carrinho:");
		for(int i=0; i < carrinho.size();i++) {
			System.out.printf("%d - ", i+1);
			carrinho.get(i).listarCarrinho();
		}
	}

}
