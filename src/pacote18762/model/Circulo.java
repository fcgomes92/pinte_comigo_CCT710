package pacote18762.model;

public class Circulo extends Figura{

	private static final long serialVersionUID = 1L;
	private Ponto centro;
	private Ponto borda;
	private int anguloInicial, anguloFinal;
	private double raio;
	
	public Circulo(){};
	
	public Circulo(Ponto c, Ponto borda){
		this.centro = c;
		this.borda = borda;
	}
	
	public Ponto getBorda() {
		return borda;
	}

	public void setBorda(Ponto borda) {
		this.borda = borda;
	}

	public Ponto getCentro() {
		return centro;
	}
	public void setCentro(Ponto centro) {
		this.centro = centro;
	}
	public double getRaio() {
		return raio;
	}
	public void setRaio(double raio) {
		this.raio = raio;
	}

	public int getAnguloInicial() {
		return anguloInicial;
	}

	public void setAnguloInicial(int anguloInicial) {
		this.anguloInicial = anguloInicial;
	}

	public int getAnguloFinal() {
		return anguloFinal;
	}

	public void setAnguloFinal(int anguloFinal) {
		this.anguloFinal = anguloFinal;
	}
	public String toString(){
		return "["+this.centro.getX()+","+this.centro.getY()+"/"+
				this.borda.getX()+","+this.borda.getY()+"/" +
				this.anguloInicial+","+this.anguloFinal+"/"+
				this.raio+","+this.getRoatcao()+"/"+
				this.getCorLinha().getRed()+","+this.getCorLinha().getGreen()+","+this.getCorLinha().getBlue()+"/"+this.getTipoLinha()+"]";
	}
}
