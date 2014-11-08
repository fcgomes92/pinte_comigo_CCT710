package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.Retangulo;
import pacote18762.model.TipoLinha;

/**
 * Classe de controle de objetos do tipo Retangulo.
 * @author gomes
 *
 */
public class ControleRetangulo extends ControleFigura {
	public LinkedList<Retangulo> retangulos_desenhados = new LinkedList<Retangulo>();
	
	public ControleReta ctrReta = new ControleReta();
	private ControlePonto ctrPonto = new ControlePonto();
	
	/**
	 * Método de calculo de pontos de retângulos.
	 * @param panel
	 * @param cor
	 * @param p1
	 * @param p2
	 * @param tipoLinha
	 * @param redraw
	 * 
	 */
	/* Modelo do retângulo
	 * p1------(l3)-----p4
	 * |				|
	 * (l0)			  (l1)    								
	 * |				|
	 * p3------(l2)-----p2
	 */
	public void drawRetangulo(Draw panel, Color cor, Ponto p1, Ponto p2, TipoLinha tipoLinha, boolean redraw){
		// calculando pontos auxiliares do retangulo
		Ponto p3 = new Ponto(p1.getX(),p2.getY()), p4 = new Ponto(p2.getX(),p1.getY());
		
		// criando o novo retangulo
		Retangulo ret = new Retangulo();
		ret.setRoatcao(0);
		ret.setLado0(new Reta(p1,p3));
		ret.setLado1(new Reta(p2,p4));
		ret.setLado2(new Reta(p2,p3));
		ret.setLado3(new Reta(p1,p4));
		ret.setCentro(ctrPonto.midPoint(p1, p2));
		
		// add figura a ser desenhada
		if(!redraw)retangulos_desenhados.add(ret);
		
		// desenhando cada reta do retangulo
		for (Reta r : this.getRetas(ret)) {
			ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, cor, tipoLinha,true);
		}
	}
	
	/**
	 * Método de retorno de uma lista de retas de um retângulo.
	 * @param ret
	 * @return
	 */
	public LinkedList<Reta> getRetas(Retangulo ret){
		LinkedList<Reta> retas = new LinkedList<Reta>();
		
		retas.add(ret.getLado0());
		retas.add(ret.getLado1());
		retas.add(ret.getLado2());
		retas.add(ret.getLado3());
		
		return retas;
	}
	
	/************************************************Função para figuras rotacionadas***************************************************/
	
	/**
	 * Método de calculo de pontos de retângulos rotacionado.
	 * @param panel
	 * @param cor
	 * @param p1
	 * @param p2
	 * @param tipoLinha
	 * @param redraw
	 * @param angulo
	 * @param pivo
	 */
	public void drawRetangulo(Draw panel, Color cor, Ponto p1, Ponto p2, TipoLinha tipoLinha, boolean redraw, int angulo, Ponto pivo){
		// calculando pontos auxiliares do retangulo
		Ponto p3 = new Ponto(p1.getX(),p2.getY()), p4 = new Ponto(p2.getX(),p1.getY());
		
		// criando o novo retangulo
		Retangulo ret = new Retangulo();
		ret.setRoatcao(angulo);
		ret.setLado0(new Reta(p1,p3));
		ret.setLado1(new Reta(p2,p4));
		ret.setLado2(new Reta(p2,p3));
		ret.setLado3(new Reta(p1,p4));
		ret.setCentro(ctrPonto.midPoint(p1, p2));
		
		// add figura a ser desenhada
		if(!redraw)retangulos_desenhados.add(ret);
		
		// desenhando cada reta do retangulo
		for (Reta r : this.getRetas(ret)) {
			ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, cor, tipoLinha,true, angulo, pivo);
		}
	}
	
	/************************************************Função para aux seleção***************************************************/
	
	public Retangulo gera_ret_selecao(Ponto p1, Ponto p2){
		Ponto p3 = new Ponto(p1.getX(),p2.getY()), p4 = new Ponto(p2.getX(),p1.getY());
		Retangulo ret = new Retangulo();
		ret.setRoatcao(0);
		ret.setLado0(new Reta(p1,p3));
		ret.setLado1(new Reta(p2,p4));
		ret.setLado2(new Reta(p2,p3));
		ret.setLado3(new Reta(p1,p4));
		ret.setCentro(ctrPonto.midPoint(p1, p2));
		
		return ret;
	}
}
