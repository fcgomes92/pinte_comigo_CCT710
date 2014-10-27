package pacote18762.model;

import java.util.LinkedList;

public class Texto {
	private TamanhoLetra tamLetra;
	private char caracter;
	private LinkedList<Reta> retas = new LinkedList<Reta>();
	
	public TamanhoLetra getTamLetra() {
		return tamLetra;
	}
	public void setTamLetra(TamanhoLetra tamLetra) {
		this.tamLetra = tamLetra;
	}
	public char getCaracter() {
		return caracter;
	}
	public void setCaracter(char caracter) {
		this.caracter = caracter;
	}
	public LinkedList<Reta> getRetas() {
		return retas;
	}
	public void setRetas(LinkedList<Reta> retas) {
		this.retas = retas;
	}
}
