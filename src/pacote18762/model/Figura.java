package pacote18762.model;

import java.awt.Color;

public class Figura extends Draw{
	private static final long serialVersionUID = 1L;
	private String nome;
	private TipoLinha tipoLinha;
	private Color corLinha;
	private int roatcao;
	private boolean pintado;
	private Color cor_pintura;
	
	public boolean isPintado() {
		return pintado;
	}
	public void setPintado(boolean pintado) {
		this.pintado = pintado;
	}
	public Color getCor_pintura() {
		return cor_pintura;
	}
	public void setCor_pintura(Color cor_pintura) {
		this.cor_pintura = cor_pintura;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Color getCorLinha() {
		return corLinha;
	}
	public void setCorLinha(Color corLinha) {
		this.corLinha = corLinha;
	}
	public TipoLinha getTipoLinha() {
		return tipoLinha;
	}
	public void setTipoLinha(TipoLinha tipoLinha) {
		this.tipoLinha = tipoLinha;
	}
	public int getRoatcao() {
		return roatcao;
	}
	public void setRoatcao(int roatcao) {
		this.roatcao = roatcao;
	}	
}
