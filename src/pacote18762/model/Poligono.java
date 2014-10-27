package pacote18762.model;

import java.util.LinkedList;

public class Poligono extends Figura{

	private static final long serialVersionUID = 1L;
	private LinkedList<Reta> lados = new LinkedList<Reta>();
	private int qtdLados;
	
	public LinkedList<Reta> getLados() {
		return lados;
	}

	public void setLados(LinkedList<Reta> lados) {
		this.lados.addAll(lados);
	}

	public int getQtdLados() {
		return qtdLados;
	}

	public void setQtdLados(int qtdLados) {
		this.qtdLados = qtdLados;
	}
}
