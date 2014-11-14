package pacote18762.control;

import java.awt.Color;
import java.awt.Dimension;

import pacote18762.model.Draw;
import pacote18762.model.Grade;
import pacote18762.model.Ponto;
import pacote18762.model.TipoLinha;

public class ControleGrade {
	
	private Grade grade;
	private ControleReta ctrReta = new ControleReta();
	
	public ControleGrade(Draw panel){
		this.grade = new Grade();
		this.grade.setGap(40);
		this.grade.setHeight(1920);
		this.grade.setWidth(1080);
	}
	
	public void setVisibleGrade(boolean cond){
		grade.setVisivel(cond);
	}
	
	public boolean isVisible(){
		return grade.isVisivel();
	}
	
	public Ponto guia_ponto(){
		Ponto pto = new Ponto(-1,-1);
		return pto;
	}
	
	public void draw_grid(Draw panel, Color bg_color){
		
		System.out.println(grade.getHeight() + "-" + grade.getWidth());
		
		Ponto p1, p2;
		
		for(int i = 0 ; i < grade.getHeight() ; i += grade.getGap()){
			p1 = new Ponto(i, 0);
			p2 = new Ponto(i, grade.getWidth());
			ctrReta.drawReta(p1, p2, panel, new Color(255-bg_color.getRed(),255-bg_color.getGreen(),255-bg_color.getBlue()), TipoLinha.fina, true);
		}
		
		for(int i = 0 ; i < grade.getWidth() ; i += grade.getGap()){
			p1 = new Ponto(0, i);
			p2 = new Ponto(grade.getHeight(), i);
			ctrReta.drawReta(p1, p2, panel, new Color(255-bg_color.getRed(),255-bg_color.getGreen(),255-bg_color.getBlue()), TipoLinha.fina, true);
		}
	}
	
	public Ponto get_ponto_prox(Ponto p){
		int x = p.getX() , y = p.getY(), gap_aux = grade.getGap(), mid_gap = gap_aux/2;
		
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		
		int proximidade_x = x%40;
		int proximidade_y = y%40;
		
		System.out.println("P-X: " + proximidade_x);
		System.out.println("P-Y: " + proximidade_y);
		
		if(proximidade_x <= mid_gap) x -= proximidade_x;
		else x += (gap_aux - proximidade_x);
		
		if(proximidade_y <= mid_gap) y -= proximidade_y;
		else y += (gap_aux - proximidade_y);
		
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		System.out.println();
		
		return new Ponto(x,y);
	}
}
