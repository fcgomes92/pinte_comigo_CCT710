package pacote18762.model;

import java.awt.Color;

public class Figura extends Draw{
	private static final long serialVersionUID = 1L;
	private String nome;
	private TipoLinha tipoLinha;
	private Color corLinha;
	
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
}
