package Cadastro;

import java.util.ArrayList;


public class Residencias {

	private final int id;
	private String endereco;
	private double consumoEnergia;
	private ArrayList<PainelSolar> listaPaineis;
	private double energiaGerada;
	
	
	public Residencias(int id, String endereco, double consumoEnergia) {
		this.id = id;
		this.endereco = endereco;
		this.consumoEnergia = consumoEnergia;
		this.listaPaineis = new ArrayList<PainelSolar>();
		this.energiaGerada = 0;
	}

	public int getId() {
		return id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public double getConsumoEnergia() {
		return this.consumoEnergia;
	}

	public void setConsumoEnergia(double consumoEnergia) {
		this.consumoEnergia = consumoEnergia;
	}
	
	public ArrayList<PainelSolar> getListaPaineis() {
		return this.listaPaineis;
	}	
	
	public void calcularEnergia() {
		for(PainelSolar painel: this.listaPaineis) {
			this.energiaGerada += painel.getPotencia();
			}
	}
	
	public double energiaGerada() {
		return this.energiaGerada;
	}
	
	public boolean verificaConsumo() {
		if(this.energiaGerada >= this.consumoEnergia) {
			return true;
		}
		return false;
	}
	
	public void verificarEnergiaRecebida(double energiaDistribuida) {
	    this.energiaGerada = energiaDistribuida;

	    if (energiaGerada >= consumoEnergia) {
	        System.out.println("Residência: " + this.id
	            + " - Consumo: " + this.consumoEnergia
	            + " - ✅ Geração de energia suficiente!");
	    } else {
	        System.out.println("Residência: " + this.id
	            + " - Consumo: " + this.consumoEnergia
	            + " - ⚠️ Alerta! Geração de energia insuficiente!");
	    }
	}
}