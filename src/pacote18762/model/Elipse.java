package pacote18762.model;

public class Elipse extends Figura{

	private static final long serialVersionUID = 1L;
	private Ponto centro, borda;
	private double eixoMaior, eixoMenor, distFocal;
	private double anguloInicial, anguloFinal;
	
	public double getEixoMaior() {
		return eixoMaior;
	}

	public void setEixoMaior(double eixoMaior) {
		this.eixoMaior = eixoMaior;
	}

	public double getEixoMenor() {
		return eixoMenor;
	}

	public void setEixoMenor(double eixoMenor) {
		this.eixoMenor = eixoMenor;
	}

	public double getDistFocal() {
		return distFocal;
	}

	public void setDistFocal(double distFocal) {
		this.distFocal = distFocal;
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
