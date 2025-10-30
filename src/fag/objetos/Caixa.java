package fag.objetos;

public class Caixa {
	
	private int dez;
	private int vinte;
	private int cinquenta;
	private double saldo;
	
	public Caixa (int dez, int vinte, int cinquenta) {
		setDez(dez);
		setVinte(vinte);
		setCinquenta(cinquenta);
	}
	
	public int getCinquenta() {return cinquenta;}
	public void setCinquenta(int cinquenta) {
		this.cinquenta = cinquenta;
	}
	
	public int getVinte() {return vinte;}
	public void setVinte(int vinte) {
		this.vinte = vinte;
	}
	
	public int getDez() {return dez;}
	public void setDez(int dez) {
		this.dez = dez;
	}

	public double getSaldo() {return saldo;}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void exibirCaixa() {
		atualizarCaixa();
		System.out.printf("Notas de 10: %d\n", getDez());
		System.out.printf("Notas de 20: %d\n", getVinte());
		System.out.printf("Notas de 50: %d\n", getCinquenta());
		
		System.out.printf("Total do Caixa: R$ %.2f\n", getSaldo());
	}
	
	public void removerCinquenta(int cinquentaT) {
		setCinquenta(cinquenta-cinquentaT);
	}
	public void removerVinte(int vinteT) {
		setVinte(vinte-vinteT);
	}
	public void removerDez(int dezT) {
		setDez(dez-dezT);
	}
	
	public void atualizarCaixa() {
		setSaldo((cinquenta*50)+(vinte*20)+(dez*10));
	}
}
