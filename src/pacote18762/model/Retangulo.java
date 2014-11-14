package pacote18762.model;

public class Retangulo extends Figura{
	
	private Ponto centro;
	private Reta lado0, lado1, lado2, lado3;
	
	public Ponto getCentro() {
		return centro;
	}

	public void setCentro(Ponto centro) {
		this.centro = centro;
	}

	public Reta getLado0() {
		return lado0;
	}

	public void setLado0(Reta lado0) {
		this.lado0 = lado0;
	}

	public Reta getLado1() {
		return lado1;
	}

	public void setLado1(Reta lado1) {
		this.lado1 = lado1;
	}

	public Reta getLado2() {
		return lado2;
	}

	public void setLado2(Reta lado2) {
		this.lado2 = lado2;
	}

	public Reta getLado3() {
		return lado3;
	}

	public void setLado3(Reta lado3) {
		this.lado3 = lado3;
	}
	public String toString(){
		return "["+this.lado0.getPtoInicial().getX()+","+this.lado0.getPtoInicial().getY()+"/"+
				this.lado1.getPtoInicial().getX()+","+this.lado1.getPtoInicial().getY()+"/"+this.getRoatcao()+"/"+
				this.getCorLinha().getRed()+","+this.getCorLinha().getGreen()+","+this.getCorLinha().getBlue()+"/"+
				this.getTipoLinha()+"]";
	}
}
