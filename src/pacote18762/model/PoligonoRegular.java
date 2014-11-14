package pacote18762.model;

public class PoligonoRegular extends Figura{
	
	private Ponto centro;
	private Ponto borda;
	private int qtdArestas;
	
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
	public int getQtdArestas() {
		return qtdArestas;
	}
	public void setQtdArestas(int qtdArestas) {
		this.qtdArestas = qtdArestas;
	}
	public String toString(){
		return "["+this.centro.getX()+","+this.centro.getY()+"/"+
				this.borda.getX()+","+this.borda.getY()+"/"+
				this.getRoatcao()+"/"+
				this.getCorLinha().getRed()+","+this.getCorLinha().getGreen()+","+this.getCorLinha().getBlue()+"/"+
				this.getTipoLinha()+"/"+
				this.getQtdArestas()+"]";
	}
}
