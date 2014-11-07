package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.PoligonoRegular;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.TipoLinha;

public class ControlePoligonoRegular extends ControleFigura{
	
	public LinkedList<PoligonoRegular> poligonos_regulares_desenhados = new LinkedList<PoligonoRegular>();
	private ControlePonto ctrPonto = new ControlePonto();
	private ControleReta ctrReta = new ControleReta();
	
	public void draw_poligono_regular(Draw panel, Color cor, TipoLinha tipoLinha, Ponto centro, Ponto borda, int qtd_lados, boolean redraw){
		
		double raio = ctrPonto.dist(centro, borda);
		int bx = centro.getX() + (int)Math.round((float)raio) , by = centro.getY();
		
		PoligonoRegular poligono = new PoligonoRegular();
		poligono.setRoatcao(0);
		poligono.setCentro(centro);
		poligono.setBorda(new Ponto(bx,by));
		poligono.setQtdArestas(qtd_lados);
		
		// add o obj na lista
		if(!redraw)poligonos_regulares_desenhados.add(poligono);
		
		Ponto pt_anterior = poligono.getBorda();
		
		double incremento_angular = 360 / qtd_lados;
		int temp_x = 0 , temp_y = 0;
		
		for (double i = incremento_angular; i < 360; i+=incremento_angular) {
			temp_x = (int) Math.round((float)Math.cos(Math.toRadians(i))*raio);
			temp_y = (int) Math.round((float)Math.sin(Math.toRadians(i))*raio);
			
			this.draw_reta(panel, cor, tipoLinha, new Reta(pt_anterior,new Ponto(centro.getX()+temp_x,centro.getY()+temp_y)));
			pt_anterior = new Ponto(centro.getX()+temp_x,centro.getY()+temp_y);
		}
		this.draw_reta(panel, cor, tipoLinha, new Reta(pt_anterior,poligono.getBorda()));
	}
	
	public void draw_reta(Draw panel, Color cor, TipoLinha tipoLinha, Reta r){
		this.ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, cor, tipoLinha, true);
	}
	
	/************************************************Função para figuras rotacionadas***************************************************/
	
	public void draw_poligono_regular(Draw panel, Color cor, TipoLinha tipoLinha, Ponto centro, Ponto borda, int qtd_lados, boolean redraw, int angulo, Ponto pivo){
		double raio = ctrPonto.dist(centro, borda);
		int bx = centro.getX() + (int)Math.round((float)raio) , by = centro.getY();
		
		PoligonoRegular poligono = new PoligonoRegular();
		poligono.setRoatcao(angulo);
		poligono.setCentro(centro);
		poligono.setBorda(new Ponto(bx,by));
		poligono.setQtdArestas(qtd_lados);
		
		// add o obj na lista
		if(!redraw)poligonos_regulares_desenhados.add(poligono);
		
		Ponto pt_anterior = poligono.getBorda();
		
		double incremento_angular = 360 / qtd_lados;
		int temp_x = 0 , temp_y = 0;
		
		for (double i = incremento_angular; i < 360; i+=incremento_angular) {
			temp_x = (int) Math.round((float)Math.cos(Math.toRadians(i))*raio);
			temp_y = (int) Math.round((float)Math.sin(Math.toRadians(i))*raio);
			
			this.draw_reta(panel, cor, tipoLinha, new Reta(pt_anterior,new Ponto(centro.getX()+temp_x,centro.getY()+temp_y)), angulo, pivo);
			pt_anterior = new Ponto(centro.getX()+temp_x,centro.getY()+temp_y);
		}
		this.draw_reta(panel, cor, tipoLinha, new Reta(pt_anterior,poligono.getBorda()),angulo,pivo);
	}
	
	public void draw_reta(Draw panel, Color cor, TipoLinha tipoLinha, Reta r, int angulo, Ponto pivo){
		this.ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, cor, tipoLinha, true, angulo, pivo);
	}
}
