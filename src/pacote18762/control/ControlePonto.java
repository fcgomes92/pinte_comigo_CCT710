package pacote18762.control;

import pacote18762.model.Ponto;

public class ControlePonto {
	public double dist(Ponto a, Ponto b){
		return Math.abs(Math.sqrt(Math.pow((b.getX()-a.getX()), 2)+Math.pow((b.getY()-a.getY()), 2)));
	}
	
	public Ponto midPoint(Ponto a, Ponto b){
		return new Ponto((a.getX()+b.getX())/2,(a.getY()+b.getY())/2);
	}
	
	public boolean isEqual(Ponto a, Ponto b){
		return (a.getX()==b.getX() && a.getY()==b.getY());
	}
}
