package pacote18762.model;

import java.util.LinkedList;
import java.util.List;

public class Letra {
	private List<Reta> retas = new LinkedList<Reta>();
	private Ponto top_left;

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
}
