package pacote18762.control;

import java.awt.Color;

import pacote18762.model.Circulo;
import pacote18762.model.Draw;
import pacote18762.model.Elipse;
import pacote18762.model.PoligonoRegular;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.Retangulo;


public class ControleFigura extends ControleDraw{
	
	private ControleCirculo ctrCirculo;
	private ControleElipse ctrElipse;
	private ControlePoligonoRegular ctrPoligono;
	private ControleReta ctrReta;
	private ControleRetangulo ctrRetangulo;
	private ControlePonto ctrPonto;
	private Object figura_selecionada;
	
	// contrutor vazio para outras figuras
	public ControleFigura(){
		
	}
	
	// contrutor para o app
	public ControleFigura(ControleCirculo circulo, ControleElipse elipse, ControlePoligonoRegular poligono, ControleReta reta, ControleRetangulo retangulo){
		this.ctrCirculo = circulo;
		this.ctrElipse = elipse;
		this.ctrPoligono = poligono;
		this.ctrReta = reta;
		this.ctrRetangulo = retangulo;
		this.ctrPonto = new ControlePonto();
	}
	
	// redraw de todas as figuras já desenhadas
	// função de auxilio em alguns desenhos
	public void draw_all_again(Draw panel){
		for (Circulo c : ctrCirculo.circulos_desenhados) {
			ctrCirculo.drawCirculoDDA(c.getCentro(), c.getBorda(), panel, c.getCorLinha(), c.getTipoLinha(), true);
		}
		
		// verificação de elipses
		for (Elipse e : ctrElipse.elipses_desenhadas) {

		}
		
		
		// verificação de poligonos
		for (PoligonoRegular p: ctrPoligono.poligonos_regulares_desenhados) {

		}
		
		// verificação de retangulos
		for (Retangulo r : ctrRetangulo.retangulos_desenhados) {

		}
		
		// verificação de retas
		for (Reta r : ctrReta.retas_desenhadas){
			
		}
	}
	
	public Object get_figura_proxima(Ponto p1, Draw panel){
		
		double distancia = Double.MAX_VALUE, distancia_temp;
		
		/*
		 *  Inicio da verificação dos objetos desenhados no painel
		 */
		// verificação de ciruclos		
		for (Circulo c : ctrCirculo.circulos_desenhados) {
			distancia_temp = ctrPonto.dist(c.getCentro(),p1);
			if(distancia_temp<distancia){
				distancia = distancia_temp;
				figura_selecionada = c;
			}
		}
		
		// verificação de elipses
		for (Elipse e : ctrElipse.elipses_desenhadas) {
			distancia_temp = ctrPonto.dist(e.getCentro(),p1);
			if(distancia_temp<distancia){
				distancia = distancia_temp;
				figura_selecionada = e;
			}
		}
		
		
		// verificação de poligonos
		for (PoligonoRegular p: ctrPoligono.poligonos_regulares_desenhados) {
			distancia_temp = ctrPonto.dist(p.getCentro(),p1);
			if(distancia_temp<distancia){
				distancia = distancia_temp;
				figura_selecionada = p;
			}
		}
		
		// verificação de retangulos
		for (Retangulo r : ctrRetangulo.retangulos_desenhados) {
			distancia_temp = ctrPonto.dist(r.getCentro(),p1);
			if(distancia_temp<distancia){
				distancia = distancia_temp;
				figura_selecionada = r;
			}
		}
		
		// verificação de retas
		for (Reta r : ctrReta.retas_desenhadas) {
			distancia_temp = ctrPonto.dist(r.getPtMedio(),p1);
			if(distancia_temp<distancia){
				distancia = distancia_temp;
				figura_selecionada = r;
			}
		}
		
		// validação do tipo de figura
//		System.out.println(figura_selecionada.getClass());
		// objetos de comparação
		if (figura_selecionada instanceof Circulo) {
			ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(),true);
			ctrCirculo.circulos_desenhados.remove(figura_selecionada);
			this.draw_all_again(panel);
		}
		
		else if (figura_selecionada instanceof Elipse) {
			ctrElipse.drawElipse(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true);
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			ctrRetangulo.drawRetangulo(panel, new Color(255,0,0), ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true);
		}
				
		
		return figura_selecionada;
	}
	
}
