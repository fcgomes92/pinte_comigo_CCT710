package pacote18762.model;

public class Circulo extends Figura{

	private static final long serialVersionUID = 1L;
	private Ponto centro;
	private Ponto borda;
	private double anguloInicial;
	private double anguloFinal;
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

	public double getAnguloInicial() {
		return anguloInicial;
	}

	public void setAnguloInicial(double anguloInicial) {
		this.anguloInicial = anguloInicial;
	}

	public double getAnguloFinal() {
		return anguloFinal;
	}

	public void setAnguloFinal(double anguloFinal) {
		this.anguloFinal = anguloFinal;
	}
}
