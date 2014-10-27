package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.Retangulo;
import pacote18762.model.TipoLinha;

public class ControleRetangulo extends ControleFigura {
	private LinkedList<Retangulo> retangulos_desenhados = new LinkedList<Retangulo>();
	
	public  ControleReta ctrReta = new ControleReta();
	
	public void drawRetangulo(Draw panel, Color cor, Ponto p1, Ponto p2, TipoLinha tipoLinha){
		// calculando pontos auxiliares do retangulo
		Ponto p3 = new Ponto(p1.getX(),p2.getY()), p4 = new Ponto(p2.getX(),p1.getY());
		
		// criando o novo retangulo
		Retangulo ret = new Retangulo();
		ret.setLado0(new Reta(p1,p3));
		ret.setLado1(new Reta(p2,p4));
		ret.setLado2(new Reta(p2,p3));
		ret.setLado3(new Reta(p1,p4));
		
		// add figura a ser desenhada
		retangulos_desenhados.add(ret);
		
		// desenhando cada reta do retangulo
		for (Reta r : this.getRetas(ret)) {
			ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, cor, tipoLinha);
		}
	}
	
	public LinkedList<Reta> getRetas(Retangulo ret){
		LinkedList<Reta> retas = new LinkedList<Reta>();
		
		retas.add(ret.getLado0());
		retas.add(ret.getLado1());
		retas.add(ret.getLado2());
		retas.add(ret.getLado3());
		
		return retas;
	}
}
