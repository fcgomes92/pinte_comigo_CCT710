package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.Poligono;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.TipoLinha;

/**
 * @author gomes
 *
 */
public class ControlePoligono extends ControleDraw{
	
	public Poligono tempPol = new Poligono();
	private ControleReta ctrReta = new ControleReta();
	
	/**
	 * @param pontos
	 * @param qtdLados
	 * @param panel
	 * @param cor
	 * @param tipoLinha
	 */
	public void drawPoligono(LinkedList<Ponto> pontos, int qtdLados, Draw panel, Color cor, TipoLinha tipoLinha){
		Poligono pol = new Poligono();
		LinkedList<Reta> retas = new LinkedList<Reta>();
		pol.setQtdLados(qtdLados);
		for(int i = 0 ; i <= qtdLados; i++){
			retas.add(new Reta(pontos.get(i),pontos.get(i+1)));
		}
		
		pol.setLados(retas);
		pol.setTipoLinha(tipoLinha);
		
		for (Reta r : pol.getLados()) {
			ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, cor, tipoLinha,true);
		}
	}
}
