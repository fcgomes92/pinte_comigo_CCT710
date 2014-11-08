package pacote18762.control;

import pacote18762.model.Ponto;

/**
 * Classe de controle dos objetos Ponto
 * @author gomes
 *
 */
public class ControlePonto {
	
	/**
	 * Método de cálculo da distância entre pontos.
	 * @param a
	 * @param b
	 * @return double
	 */
	public double dist(Ponto a, Ponto b){
		return Math.abs(Math.sqrt(Math.pow((b.getX()-a.getX()), 2)+Math.pow((b.getY()-a.getY()), 2)));
	}
	
	/**
	 * Método de cálculo do ponto médio entre pontos.
	 * @param a
	 * @param b
	 * @return Ponto
	 */
	public Ponto midPoint(Ponto a, Ponto b){
		return new Ponto((a.getX()+b.getX())/2,(a.getY()+b.getY())/2);
	}
	
	/**
	 * Método de comparação entre pontos.
	 * @param a
	 * @param b
	 * @return boolean
	 */
	public boolean isEqual(Ponto a, Ponto b){
		return (a.getX()==b.getX() && a.getY()==b.getY());
	}
}
