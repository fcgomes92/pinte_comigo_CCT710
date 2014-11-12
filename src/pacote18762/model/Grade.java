package pacote18762.model;

import java.util.LinkedList;

public class Grade {
	private LinkedList<Ponto> pontos = new LinkedList<Ponto>();
	private boolean visivel = false;
	private int gap = 0;
	private int height = 0, width = 0;
	
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	public LinkedList<Ponto> getPontos() {
		return pontos;
	}
	public void setPontos(LinkedList<Ponto> pontos) {
		this.pontos = pontos;
	}
	public int getGap() {
		return gap;
	}
	public void setGap(int gap) {
		this.gap = gap;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}
