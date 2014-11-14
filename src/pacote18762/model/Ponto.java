package pacote18762.model;

public class Ponto {
	private int x;
	private int y;
	
	public Ponto() {}
	
	public Ponto(Ponto p){
		this.setX(p.getX());
		this.setY(p.getY());
	}
	
	public Ponto(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		return ("("+this.x +","+ this.y+")");
	}
}
