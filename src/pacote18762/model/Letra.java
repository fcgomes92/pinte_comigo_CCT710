package pacote18762.model;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class Letra extends Figura{
	private List<Reta> retas = new LinkedList<Reta>();
	private Ponto top_left;
	private TamanhoLetra tamLetra;
	private int caracter;
	
	public TamanhoLetra getTamLetra() {
		return tamLetra;
	}

	public void setTamLetra(TamanhoLetra tamLetra) {
		this.tamLetra = tamLetra;
	}

	public int getCaracter() {
		return caracter;
	}

	public void setCaracter(int caracter) {
		this.caracter = caracter;
	}
	
	public void set_one_line(Reta r){
		this.retas.add(r);
	}

	public Ponto getTop_left() {
		return top_left;
	}

	public void setTop_left(Ponto top_left) {
		this.top_left = top_left;
	}

	public List<Reta> getRetas() {
		return retas;
	}

	public void setRetas(List<Reta> retas) {
		this.retas = retas;
	}
	
	public String toString(){
		return "["+
				this.getTop_left().getX()+","+this.getTop_left().getY()+"/"+
				this.getCaracter()+"/"+
				this.getTamLetra()+"/"+
				this.getTipoLinha()+"/"+
				this.getCorLinha().getRed()+","+this.getCorLinha().getGreen()+","+this.getCorLinha().getBlue()+"/"+
				this.getRoatcao()+
				"]";
	}
}
