package pacote18762.model;

public class Reta extends Figura{

	private static final long serialVersionUID = 1L;
	private Ponto ptMedio;
	private Ponto ptoInicial;
	private Ponto ptoFinal;
	
	public Ponto getPtMedio() {
		return ptMedio;
	}

	public void setPtMedio(Ponto ptMedio) {
		this.ptMedio = ptMedio;
	}

	public Reta(Ponto pInicial, Ponto pFinal){
			this.setPtoInicial(pInicial);
			this.setPtoFinal(pFinal);
	}
	
	public Ponto getPtoInicial() {
		return ptoInicial;
	}
	public void setPtoInicial(Ponto ptoInicial) {
		this.ptoInicial = ptoInicial;
	}
	public Ponto getPtoFinal() {
		return ptoFinal;
	}
	public void setPtoFinal(Ponto ptoFinal) {
		this.ptoFinal = ptoFinal;
	}
	
	@Override
	public String toString() {
		return "[" + ptoInicial + "," + ptoFinal
				+ "]";
	}
}
