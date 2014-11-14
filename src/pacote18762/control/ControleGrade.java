package pacote18762.control;

import java.awt.Color;

import pacote18762.model.Draw;
import pacote18762.model.Grade;
import pacote18762.model.Ponto;
import pacote18762.model.TipoLinha;

/**
 * @author gomes
 */
public class ControleGrade {
	
	private Grade grade;
	private ControleReta ctrReta = new ControleReta();
	
	/**
	 * Contrutor
	 * @param panel Paneil de Desenho
	 */
	public ControleGrade(Draw panel){
		this.grade = new Grade();
		this.grade.setGap(20);
		this.grade.setHeight(1920);
		this.grade.setWidth(1080);
	}
	
	public void setVisibleGrade(boolean cond){
		grade.setVisivel(cond);
	}
	
	/**
	 * Verifica se a grade esta visível
	 * @return True/False
	 */
	public boolean isVisible(){
		return grade.isVisivel();
	}
	
	/**
	 * Função para desenho das linhas da grade.
	 * @param panel Painel de Desenho
	 * @param bg_color Cor de fundo do painel
	 */
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
	
	
	/**
	 * Função para verificar o quão longe o ponto selecionado está
	 * de um dos pontos de intersecção da grade.
	 * @param p Ponto
	 * @return Novo Ponto
	 */
	public Ponto get_ponto_prox(Ponto p){
		int x = p.getX() , y = p.getY(), gap_aux = grade.getGap(), mid_gap = gap_aux/2;
		
		// calculo do 'nível' de proximidade do ponto
		int proximidade_x = x%gap_aux;
		int proximidade_y = y%gap_aux;
		
		// ajuste de x
		if(proximidade_x <= mid_gap) x -= proximidade_x;
		else x += (gap_aux - proximidade_x);
		
		// ajuste de y
		if(proximidade_y <= mid_gap) y -= proximidade_y;
		else y += (gap_aux - proximidade_y);
		
		return new Ponto(x,y);
	}
}