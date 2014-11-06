package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

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
	
	private List<Circulo> circulos_desenhados_aux = new LinkedList<Circulo>();
	private List<Circulo> arco_circulos_desenhados_aux = new LinkedList<>();
	private List<Elipse> elipses_desenhados_aux = new LinkedList<>();
	private List<Elipse> arco_elipses_desenhados_aux = new LinkedList<>();
	private List<PoligonoRegular> poligonos_desenhados_aux = new LinkedList<>();
	private List<Reta> retas_desenhados_aux = new LinkedList<>();
	private List<Retangulo> retangulos_desenhados_aux = new LinkedList<>();
	
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
		
		for (Circulo c : ctrCirculo.arcos_desenhados){
			ctrCirculo.drawCirculoArch(c.getCentro(), c.getBorda(), c.getAnguloInicial(), c.getAnguloFinal(), panel, c.getCorLinha(), c.getTipoLinha(), true);
		}
		
		for (Elipse e : ctrElipse.elipses_desenhadas) {
			ctrElipse.drawElipse(panel, e.getCorLinha(), e.getCentro(), e.getBorda(), e.getTipoLinha(), true);
		}
		
		for (Elipse e : ctrElipse.arcos_desenhados){
			ctrElipse.drawElipseArc(panel, e.getCorLinha(), e.getCentro(), e.getBorda(), e.getAnguloInicial(), e.getAnguloFinal(), e.getTipoLinha(), true);
		}
		
		for (PoligonoRegular p: ctrPoligono.poligonos_regulares_desenhados) {
			ctrPoligono.draw_poligono_regular(panel, p.getCorLinha(), p.getTipoLinha(), p.getCentro(), p.getBorda(), p.getQtdArestas(), true);
		}
		
		for (Retangulo r : ctrRetangulo.retangulos_desenhados) {
			ctrRetangulo.drawRetangulo(panel, r.getCorLinha(), r.getLado0().getPtoInicial(), r.getLado1().getPtoInicial(), r.getTipoLinha(), true);
		}
		
		for (Reta r : ctrReta.retas_desenhadas){
			ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, r.getCorLinha(), r.getTipoLinha(), true);
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
		
		// verificação de arcos de circulo
		for (Circulo c : ctrCirculo.arcos_desenhados) {
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
		
		// verificação de arcos de elipses
		for (Elipse e : ctrElipse.arcos_desenhados) {
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
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(),true);
				ctrCirculo.circulos_desenhados.remove(figura_selecionada);
			}
			else{
				ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(), true);
				ctrCirculo.arcos_desenhados.remove(figura_selecionada);
			}
		}
		
		else if (figura_selecionada instanceof Elipse) {
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				ctrElipse.drawElipse(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true);
				ctrElipse.elipses_desenhadas.remove(figura_selecionada);
			}
			else{
				ctrElipse.drawElipseArc(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true);
				ctrElipse.arcos_desenhados.remove(figura_selecionada);
			}
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			ctrRetangulo.drawRetangulo(panel, new Color(255,0,0), ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true);
			ctrRetangulo.retangulos_desenhados.remove(figura_selecionada);
		}
		
		else if (figura_selecionada instanceof Reta) {
			ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, new Color(255,0,0), ((Reta) figura_selecionada).getTipoLinha(), true);
			ctrReta.retas_desenhadas.remove(figura_selecionada);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			ctrPoligono.draw_poligono_regular(panel, new Color(255,0,0), ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true);
			ctrPoligono.poligonos_regulares_desenhados.remove(figura_selecionada);
		}
				
		return figura_selecionada;
	}
	
	// função para apagar uma figura selecionada
	// função de aux para desenhar e redesenhar
	public void apaga_figura_selecionada(Draw panel, Color bg_color){
		if (figura_selecionada instanceof Circulo)
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0)
				ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, bg_color, ((Circulo) figura_selecionada).getTipoLinha(),true);
			else
				ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, bg_color, ((Circulo) figura_selecionada).getTipoLinha(), true);
		
		else if (figura_selecionada instanceof Elipse) {
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				ctrElipse.drawElipse(panel, bg_color, ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true);
				ctrElipse.elipses_desenhadas.remove(figura_selecionada);
			}
			else{
				ctrElipse.drawElipseArc(panel, bg_color, ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true);
				ctrElipse.arcos_desenhados.remove(figura_selecionada);
			}
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			ctrRetangulo.drawRetangulo(panel, bg_color, ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true);
			ctrRetangulo.retangulos_desenhados.remove(figura_selecionada);
		}
		
		else if (figura_selecionada instanceof Reta) {
			ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, bg_color, ((Reta) figura_selecionada).getTipoLinha(), true);
			ctrReta.retas_desenhadas.remove(figura_selecionada);
		}
		else if (figura_selecionada instanceof PoligonoRegular)
			ctrPoligono.draw_poligono_regular(panel, bg_color, ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true);
	}
	
	/************************************************Função para uma única figura***************************************************/
	
	public void mover_figura_selecionada(Draw panel, Ponto novo_ponto, Color bg_color){
		// apaga figura antiga
		this.apaga_figura_selecionada(panel, bg_color);
		
		// cria nova figura
		if (figura_selecionada instanceof Circulo) {
			
			Ponto centro_inicial = ((Circulo) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Circulo) figura_selecionada).getBorda();
			
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				ctrCirculo.drawCirculoDDA(new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false);
			}
			else{
				ctrCirculo.drawCirculoArch(novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
			}
		}
		
		else if (figura_selecionada instanceof Elipse) {
			
			Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
			
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false);
			}
			else{
				ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false);
			}
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			
			Ponto medio = ((Retangulo) figura_selecionada).getCentro();
			Ponto novo_p1 = new Ponto(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX()+(novo_ponto.getX()-medio.getX()),((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY()+(novo_ponto.getY()-medio.getY()));
			Ponto novo_p2 = new Ponto(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX()+(novo_ponto.getX()-medio.getX()),((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY()+(novo_ponto.getY()-medio.getY()));
			
			ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false);
		}
		
		else if (figura_selecionada instanceof Reta) {
			
			Ponto medio = ((Reta) figura_selecionada).getPtMedio();
			Ponto novo_p1 = new Ponto(((Reta) figura_selecionada).getPtoInicial().getX() + (novo_ponto.getX() - medio.getX()),((Reta) figura_selecionada).getPtoInicial().getY() + (novo_ponto.getY() - medio.getY()));
			Ponto novo_p2 = new Ponto(((Reta) figura_selecionada).getPtoFinal().getX() + (novo_ponto.getX() - medio.getX()),((Reta) figura_selecionada).getPtoFinal().getY() + (novo_ponto.getY() - medio.getY()));
			
			
			ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			
			Ponto centro = ((PoligonoRegular) figura_selecionada).getCentro();
			Ponto borda = ((PoligonoRegular) figura_selecionada).getBorda();
			Ponto novo_p2 = new Ponto(borda.getX() + (novo_ponto.getX()-centro.getX()),borda.getY() + (novo_ponto.getY()-centro.getY()));
			
			ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), novo_ponto, novo_p2, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false);
		}
	}
	
	// função para mover a figura de um ponto ao outro usando um ponto de ref
	public void mover_figura_selecionada(Draw panel, Ponto novo_ponto, Ponto ref, Color bg_color){
		// apaga figura antiga
		this.apaga_figura_selecionada(panel, bg_color);
		
		// cria nova figura
		if (figura_selecionada instanceof Circulo) {
			
			int dif_x = ref.getX() - novo_ponto.getX();
			int dif_y = ref.getY() - novo_ponto.getY();
			
			Ponto centro = new Ponto(((Circulo) figura_selecionada).getCentro().getX()+dif_x,((Circulo) figura_selecionada).getCentro().getY() + dif_y);
			Ponto borda = new Ponto(((Circulo) figura_selecionada).getBorda().getX()+dif_x,((Circulo) figura_selecionada).getBorda().getY()+dif_y);
			
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				ctrCirculo.drawCirculoDDA(centro, borda, panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false);
			}
			else{
				ctrCirculo.drawCirculoArch(centro, borda, ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
			}
		}
		
		else if (figura_selecionada instanceof Elipse) {
			
			Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
			
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false);
			}
			else{
				ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false);
			}
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			
			Ponto medio = ((Retangulo) figura_selecionada).getCentro();
			Ponto novo_p1 = new Ponto(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX()+(novo_ponto.getX()-medio.getX()),((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY()+(novo_ponto.getY()-medio.getY()));
			Ponto novo_p2 = new Ponto(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX()+(novo_ponto.getX()-medio.getX()),((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY()+(novo_ponto.getY()-medio.getY()));
			
			ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false);
		}
		
		else if (figura_selecionada instanceof Reta) {
			
			Ponto medio = ((Reta) figura_selecionada).getPtMedio();
			Ponto novo_p1 = new Ponto(((Reta) figura_selecionada).getPtoInicial().getX() + (novo_ponto.getX() - medio.getX()),((Reta) figura_selecionada).getPtoInicial().getY() + (novo_ponto.getY() - medio.getY()));
			Ponto novo_p2 = new Ponto(((Reta) figura_selecionada).getPtoFinal().getX() + (novo_ponto.getX() - medio.getX()),((Reta) figura_selecionada).getPtoFinal().getY() + (novo_ponto.getY() - medio.getY()));
			
			
			ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			
			Ponto centro = ((PoligonoRegular) figura_selecionada).getCentro();
			Ponto borda = ((PoligonoRegular) figura_selecionada).getBorda();
			Ponto novo_p2 = new Ponto(borda.getX() + (novo_ponto.getX()-centro.getX()),borda.getY() + (novo_ponto.getY()-centro.getY()));
			
			ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), novo_ponto, novo_p2, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false);
		}
	}
	
	// função para redesenhar uma figura em uma outra escala
	public void alterar_escala_figura(Draw panel, int escala, Color bg_color){
		// apaga figura antiga
				this.apaga_figura_selecionada(panel, bg_color);
				
				// cria nova figura
				if (figura_selecionada instanceof Circulo) {
					
					Ponto centro_inicial = ((Circulo) figura_selecionada).getCentro();
					Ponto borda_inicial = ((Circulo) figura_selecionada).getBorda();
					
					if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
						ctrCirculo.drawCirculoDDA(new Ponto(centro_inicial.getX()*escala,centro_inicial.getY()*escala), new Ponto((borda_inicial.getX())*escala, (borda_inicial.getY())*escala), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false);
					}
					else{
						ctrCirculo.drawCirculoArch(new Ponto(centro_inicial.getX()*escala,centro_inicial.getY()*escala), new Ponto((borda_inicial.getX())*escala, (borda_inicial.getY())), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
					}
				}
				
				else if (figura_selecionada instanceof Elipse) {
					
					Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
					Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
					
					if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
						ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), new Ponto(centro_inicial.getX()*escala,centro_inicial.getY()*escala), new Ponto((borda_inicial.getX())*escala, (borda_inicial.getY())*escala), ((Elipse) figura_selecionada).getTipoLinha(), false);
					}
					else{
						ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), new Ponto(centro_inicial.getX()*escala,centro_inicial.getY()*escala), new Ponto((borda_inicial.getX())*escala, (borda_inicial.getY())*escala), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false);
					}
				}
				
				else if (figura_selecionada instanceof Retangulo) {
					
					Ponto novo_p1 = new Ponto(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX()*escala,((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY()*escala);
					Ponto novo_p2 = new Ponto(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX()*escala,((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY()*escala);
					
					ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false);
				}
				
				else if (figura_selecionada instanceof Reta) {
					
					Ponto novo_p1 = new Ponto(((Reta) figura_selecionada).getPtoInicial().getX()*escala,((Reta) figura_selecionada).getPtoInicial().getY()*escala);
					Ponto novo_p2 = new Ponto(((Reta) figura_selecionada).getPtoFinal().getX()*escala,((Reta) figura_selecionada).getPtoFinal().getY()*escala);
					
					
					ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false);
				}
				else if (figura_selecionada instanceof PoligonoRegular) {
					
					Ponto centro = new Ponto(((PoligonoRegular) figura_selecionada).getCentro().getX()*escala,((PoligonoRegular) figura_selecionada).getCentro().getY()*escala);
					Ponto borda = new Ponto(((PoligonoRegular) figura_selecionada).getBorda().getX()*escala,((PoligonoRegular) figura_selecionada).getBorda().getY()*escala);
					
					ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), centro, borda, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false);
				}
	}
	
	// função para rotacionar uma figura em uma outra escala
	public void rotacionar_figura(Draw panel, int angulo, Ponto pivo, Color bg_color){
		// apaga figura antiga
		this.apaga_figura_selecionada(panel, bg_color);
			
		// cria nova figura
		if (figura_selecionada instanceof Circulo) {
				
			Ponto centro_inicial = ((Circulo) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Circulo) figura_selecionada).getBorda();
			
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				ctrCirculo.drawCirculoDDA(new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false);
			}
			else{
				ctrCirculo.drawCirculoArch(new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
			}
		}
			
		else if (figura_selecionada instanceof Elipse) {
				
			Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
			pivo = new Ponto(centro_inicial.getX(), centro_inicial.getY());
				
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false, angulo, pivo);
			}
			else{
				ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false, angulo, pivo);
			}
		}
			
		else if (figura_selecionada instanceof Retangulo) {
				
			Ponto novo_p1 = new Ponto(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX(),((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY());
			Ponto novo_p2 = new Ponto(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX(),((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY());
			
			ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false);
		}
			
		else if (figura_selecionada instanceof Reta) {
				
			Ponto novo_p1 = new Ponto(((Reta) figura_selecionada).getPtoInicial().getX(),((Reta) figura_selecionada).getPtoInicial().getY());
			Ponto novo_p2 = new Ponto(((Reta) figura_selecionada).getPtoFinal().getX(),((Reta) figura_selecionada).getPtoFinal().getY());
					
				ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			
			Ponto centro = new Ponto(((PoligonoRegular) figura_selecionada).getCentro().getX(),((PoligonoRegular) figura_selecionada).getCentro().getY());
			Ponto borda = new Ponto(((PoligonoRegular) figura_selecionada).getBorda().getX(),((PoligonoRegular) figura_selecionada).getBorda().getY());
			
			ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), centro, borda, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false);
		}
	}
	
	/************************************************Função para todas as figuras***************************************************/
	
	// função para mover todos os itens da área de trabalho
	public void move_all(Draw panel, Ponto p1, Color bg_color){
		
		Ponto centro_panel = new Ponto(Math.round(panel.getWidth()/2),Math.round(panel.getHeight()/2));
		
		circulos_desenhados_aux.clear();
		circulos_desenhados_aux.addAll(ctrCirculo.circulos_desenhados);
		for (Circulo c : circulos_desenhados_aux) {
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, centro_panel, bg_color);
		}
		
		arco_circulos_desenhados_aux = ctrCirculo.arcos_desenhados; 
		for (Circulo c : arco_circulos_desenhados_aux){
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, bg_color);
		}
		
		elipses_desenhados_aux = ctrElipse.elipses_desenhadas;
		for (Elipse c : elipses_desenhados_aux) {
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, bg_color);
		}
		
		arco_elipses_desenhados_aux = ctrElipse.arcos_desenhados;
		for (Elipse c : arco_elipses_desenhados_aux){
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, bg_color);
		}
		
		poligonos_desenhados_aux = ctrPoligono.poligonos_regulares_desenhados;
		for (PoligonoRegular c: poligonos_desenhados_aux) {
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, bg_color);
		}
		
		retangulos_desenhados_aux = ctrRetangulo.retangulos_desenhados;
		for (Retangulo c : retangulos_desenhados_aux) {
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, bg_color);
		}
		
		retas_desenhados_aux = ctrReta.retas_desenhadas;
		for (Reta c : retas_desenhados_aux){
			figura_selecionada = c;
			mover_figura_selecionada(panel, p1, bg_color);
		}
	}

	// função para mudar a escala de todos os itens da área de trabalho
	public void scale_all(Draw panel, int escala, Color bg_color){
		circulos_desenhados_aux = ctrCirculo.circulos_desenhados;
		for (Circulo c : circulos_desenhados_aux) {
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		arco_circulos_desenhados_aux = ctrCirculo.arcos_desenhados; 
		for (Circulo c : arco_circulos_desenhados_aux){
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		elipses_desenhados_aux = ctrElipse.elipses_desenhadas;
		for (Elipse c : elipses_desenhados_aux) {
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		arco_elipses_desenhados_aux = ctrElipse.arcos_desenhados;
		for (Elipse c : arco_elipses_desenhados_aux){
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		poligonos_desenhados_aux = ctrPoligono.poligonos_regulares_desenhados;
		for (PoligonoRegular c: poligonos_desenhados_aux) {
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		retangulos_desenhados_aux = ctrRetangulo.retangulos_desenhados;
		for (Retangulo c : retangulos_desenhados_aux) {
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		retas_desenhados_aux = ctrReta.retas_desenhadas;
		for (Reta c : retas_desenhados_aux){
			figura_selecionada = c;
			alterar_escala_figura(panel, escala, bg_color);
		}
	}
}