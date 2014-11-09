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
import pacote18762.model.TipoLinha;

/**
 * Classe de controle de objetos do tipo figura.
 * @author gomes
 *
 */
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
	private List<Object> figuras_selecao_multipla;
	private Retangulo selecao_multipla;
	
	// contrutor vazio para outras figuras
	public ControleFigura(){
		
	}
	
	/**
	 * Construtor da classe.
	 * @param circulo
	 * @param elipse
	 * @param poligono
	 * @param reta
	 * @param retangulo
	 */
	public ControleFigura(ControleCirculo circulo, ControleElipse elipse, ControlePoligonoRegular poligono, ControleReta reta, ControleRetangulo retangulo){
		this.ctrCirculo = circulo;
		this.ctrElipse = elipse;
		this.ctrPoligono = poligono;
		this.ctrReta = reta;
		this.ctrRetangulo = retangulo;
		this.ctrPonto = new ControlePonto();
	}
	
	/************************************************Função para uma única figura***************************************************/
	
	/**
	 * função para mover uma figura única
	 * @param panel
	 * @param novo_ponto
	 * @param bg_color
	 */
	public void mover_figura_selecionada(Draw panel, Ponto novo_ponto, Color bg_color){
		// apaga figura antiga
		this.apaga_figura_selecionada(panel, bg_color);
		
		// cria nova figura
		if (figura_selecionada instanceof Circulo) {
			
			Ponto centro_inicial = ((Circulo) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Circulo) figura_selecionada).getBorda();
			
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				if(((Circulo) figura_selecionada).getRoatcao() == 0) ctrCirculo.drawCirculoDDA(novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false);
				else ctrCirculo.drawCirculoDDA(novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false, ((Circulo) figura_selecionada).getRoatcao(), novo_ponto);
			}
			else{
				if(((Circulo) figura_selecionada).getRoatcao() == 0) ctrCirculo.drawCirculoArch(novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
				else ctrCirculo.drawCirculoArch(novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false, ((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro());
			}
		}
		
		else if (figura_selecionada instanceof Elipse) {
			
			Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
			
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false);
				else ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false, ((Elipse) figura_selecionada).getRoatcao(), novo_ponto);
			}
			else{
				if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false);
				else ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false, ((Elipse) figura_selecionada).getRoatcao(), novo_ponto);
			}
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			
			Ponto medio = ((Retangulo) figura_selecionada).getCentro();
			Ponto novo_p1 = new Ponto(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX()+(novo_ponto.getX()-medio.getX()),((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY()+(novo_ponto.getY()-medio.getY()));
			Ponto novo_p2 = new Ponto(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX()+(novo_ponto.getX()-medio.getX()),((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY()+(novo_ponto.getY()-medio.getY()));
			Ponto novo_medio = new Ponto((Math.abs(Math.round((novo_p1.getX()+novo_p2.getX())/2))),(Math.abs(Math.round((novo_p1.getY()+novo_p2.getY())/2)))); 
			
			if(((Retangulo) figura_selecionada).getRoatcao()==0) ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false);
			else ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false, ((Retangulo) figura_selecionada).getRoatcao(), novo_medio);
		}
		
		else if (figura_selecionada instanceof Reta) {
			
			Ponto medio = ((Reta) figura_selecionada).getPtMedio();
			Ponto novo_p1 = new Ponto(((Reta) figura_selecionada).getPtoInicial().getX() + (novo_ponto.getX() - medio.getX()),((Reta) figura_selecionada).getPtoInicial().getY() + (novo_ponto.getY() - medio.getY()));
			Ponto novo_p2 = new Ponto(((Reta) figura_selecionada).getPtoFinal().getX() + (novo_ponto.getX() - medio.getX()),((Reta) figura_selecionada).getPtoFinal().getY() + (novo_ponto.getY() - medio.getY()));
			Ponto novo_medio = new Ponto((Math.abs(Math.round((novo_p1.getX()+novo_p2.getX())/2))),(Math.abs(Math.round((novo_p1.getY()+novo_p2.getY())/2))));
			
			if(((Reta) figura_selecionada).getRoatcao()==0) ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false);
			else ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false, ((Reta) figura_selecionada).getRoatcao(), novo_medio);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			
			Ponto centro = ((PoligonoRegular) figura_selecionada).getCentro();
			Ponto borda = ((PoligonoRegular) figura_selecionada).getBorda();
			Ponto novo_p2 = new Ponto(borda.getX() + (novo_ponto.getX()-centro.getX()),borda.getY() + (novo_ponto.getY()-centro.getY()));
			
			if(((PoligonoRegular) figura_selecionada).getRoatcao()==0) ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), novo_ponto, novo_p2, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false);
			else ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), novo_ponto, novo_p2, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false, ((PoligonoRegular) figura_selecionada).getRoatcao(), novo_ponto);
		}
	}
	
	/**
	 * função para redesenhar uma figura em uma outra escala
	 * @param panel
	 * @param escala
	 * @param bg_color
	 */
	public void alterar_escala_figura(Draw panel, double escala, Color bg_color){
		// apaga figura antiga
				this.apaga_figura_selecionada(panel, bg_color);
				
				// cria nova figura
				if (figura_selecionada instanceof Circulo) {
					
					Ponto centro_inicial = ((Circulo) figura_selecionada).getCentro();
					centro_inicial = new Ponto((int)Math.round(centro_inicial.getX()*escala),(int)Math.round(centro_inicial.getY()*escala));
					Ponto borda_inicial = ((Circulo) figura_selecionada).getBorda();
					borda_inicial = new Ponto((int)Math.round(borda_inicial.getX()*escala),(int)Math.round(borda_inicial.getY()*escala));
					
					if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
						if(((Circulo) figura_selecionada).getRoatcao() == 0) ctrCirculo.drawCirculoDDA(centro_inicial, borda_inicial, panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false);
						else ctrCirculo.drawCirculoDDA(centro_inicial, borda_inicial, panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false,((Circulo) figura_selecionada).getRoatcao(),centro_inicial);
					}
					else{
						if(((Circulo) figura_selecionada).getRoatcao() == 0) ctrCirculo.drawCirculoArch(centro_inicial, borda_inicial, ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
						else ctrCirculo.drawCirculoArch(centro_inicial, borda_inicial, ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false, ((Circulo) figura_selecionada).getRoatcao(), centro_inicial);
					}
				}
				
				else if (figura_selecionada instanceof Elipse) {
					
					Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
					centro_inicial = new Ponto((int)Math.round(centro_inicial.getX()*escala),(int)Math.round(centro_inicial.getY()*escala));
					Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
					borda_inicial = new Ponto((int)Math.round(borda_inicial.getX()*escala),(int)Math.round(borda_inicial.getY()*escala));
					
					if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
						if(((Elipse) figura_selecionada).getRoatcao() == 0) ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), centro_inicial, borda_inicial, ((Elipse) figura_selecionada).getTipoLinha(), false);
						else ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), centro_inicial, borda_inicial, ((Elipse) figura_selecionada).getTipoLinha(), false, ((Elipse) figura_selecionada).getRoatcao(), centro_inicial);
					}
					else{
						if(((Elipse) figura_selecionada).getRoatcao() == 0) ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), centro_inicial, borda_inicial, ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false);
						else ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), centro_inicial, borda_inicial, ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false, ((Elipse) figura_selecionada).getRoatcao(), centro_inicial);
					}
				}
				
				else if (figura_selecionada instanceof Retangulo) {
					
					Ponto novo_p1 = new Ponto((int)Math.round(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX()*escala),(int)Math.round(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY()*escala));
					Ponto novo_p2 = new Ponto((int)Math.round(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX()*escala),(int)Math.round(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY()*escala));
					Ponto novo_medio = ctrPonto.midPoint(novo_p1, novo_p2);
					
					if(((Retangulo) figura_selecionada).getRoatcao() == 0) ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false);
					else ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false,((Retangulo) figura_selecionada).getRoatcao(),novo_medio);
				}
				
				else if (figura_selecionada instanceof Reta) {
					
					Ponto novo_p1 = new Ponto((int)Math.round(((Reta) figura_selecionada).getPtoInicial().getX()*escala),(int)Math.round(((Reta) figura_selecionada).getPtoInicial().getY()*escala));
					Ponto novo_p2 = new Ponto((int)Math.round(((Reta) figura_selecionada).getPtoFinal().getX()*escala),(int)Math.round(((Reta) figura_selecionada).getPtoFinal().getY()*escala));
					Ponto novo_medio = ctrPonto.midPoint(novo_p1, novo_p2);
					
					if(((Reta) figura_selecionada).getRoatcao() == 0) ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false);
					else ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false, ((Reta) figura_selecionada).getRoatcao(), novo_medio);
				}
				else if (figura_selecionada instanceof PoligonoRegular) {
					
					Ponto centro = new Ponto((int)Math.round(((PoligonoRegular) figura_selecionada).getCentro().getX()*escala),(int)Math.round(((PoligonoRegular) figura_selecionada).getCentro().getY()*escala));
					Ponto borda = new Ponto((int)Math.round(((PoligonoRegular) figura_selecionada).getBorda().getX()*escala),(int)Math.round(((PoligonoRegular) figura_selecionada).getBorda().getY()*escala));
					
					if(((PoligonoRegular) figura_selecionada).getRoatcao() == 0) ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), centro, borda, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false);
					else ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), centro, borda, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false, ((PoligonoRegular) figura_selecionada).getRoatcao(), centro);
				}
	}
	
	/**
	 * função para rotacionar uma figura
	 * @param panel
	 * @param angulo
	 * @param pivo
	 * @param bg_color
	 */
	public void rotacionar_figura(Draw panel, int angulo, Ponto pivo, Color bg_color){
		// apaga figura antiga
		this.apaga_figura_selecionada(panel, bg_color);
			
		// cria nova figura
		if (figura_selecionada instanceof Circulo) {
				
			Ponto centro_inicial = ((Circulo) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Circulo) figura_selecionada).getBorda();
			pivo = new Ponto(centro_inicial.getX(), centro_inicial.getY());
			
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				ctrCirculo.drawCirculoDDA(new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(),false, angulo, pivo);
			}
			else{
				ctrCirculo.drawCirculoArch(new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), ((Circulo) figura_selecionada).getAnguloInicial()+angulo, ((Circulo) figura_selecionada).getAnguloFinal()+angulo, panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false, angulo, pivo);
			}
		}
			
		else if (figura_selecionada instanceof Elipse) {
//			System.out.println("RODA RODA!");
			Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
			pivo = new Ponto(centro_inicial.getX(), centro_inicial.getY());
				
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false, angulo, pivo);
			}
			else{
				ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), new Ponto(centro_inicial.getX(),centro_inicial.getY()), new Ponto((borda_inicial.getX()), (borda_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial()+angulo, ((Elipse) figura_selecionada).getAnguloFinal()+angulo, ((Elipse) figura_selecionada).getTipoLinha(), false, angulo, pivo);
			}
		}
			
		else if (figura_selecionada instanceof Retangulo) {
				
			Ponto novo_p1 = new Ponto(((Retangulo) figura_selecionada).getLado0().getPtoInicial().getX(),((Retangulo) figura_selecionada).getLado0().getPtoInicial().getY());
			Ponto novo_p2 = new Ponto(((Retangulo) figura_selecionada).getLado1().getPtoInicial().getX(),((Retangulo) figura_selecionada).getLado1().getPtoInicial().getY());
			pivo = new Ponto(((Retangulo) figura_selecionada).getCentro());
			
			ctrRetangulo.drawRetangulo(panel, ((Retangulo) figura_selecionada).getCorLinha(), novo_p1,  novo_p2, ((Retangulo) figura_selecionada).getTipoLinha(),false, angulo, pivo);
		}
			
		else if (figura_selecionada instanceof Reta) {
				
			Ponto novo_p1 = new Ponto(((Reta) figura_selecionada).getPtoInicial().getX(),((Reta) figura_selecionada).getPtoInicial().getY());
			Ponto novo_p2 = new Ponto(((Reta) figura_selecionada).getPtoFinal().getX(),((Reta) figura_selecionada).getPtoFinal().getY());
			pivo = ((Reta) figura_selecionada).getPtMedio();
			
			ctrReta.drawReta(novo_p1, novo_p2, panel, ((Reta) figura_selecionada).getCorLinha(), ((Reta) figura_selecionada).getTipoLinha(), false, angulo, pivo);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			
			Ponto centro = new Ponto(((PoligonoRegular) figura_selecionada).getCentro().getX(),((PoligonoRegular) figura_selecionada).getCentro().getY());
			Ponto borda = new Ponto(((PoligonoRegular) figura_selecionada).getBorda().getX(),((PoligonoRegular) figura_selecionada).getBorda().getY());
			
			ctrPoligono.draw_poligono_regular(panel, ((PoligonoRegular) figura_selecionada).getCorLinha(), ((PoligonoRegular) figura_selecionada).getTipoLinha(), centro, borda, ((PoligonoRegular) figura_selecionada).getQtdArestas(), false, angulo, centro);
		}
	}
	
	/************************************************Função para todas as figuras***************************************************/
	
	/**
	 * função para mover todos os itens da área de trabalho
	 * @param panel
	 * @param p1
	 * @param bg_color
	 */
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
	
	/**
	 * função para mudar a escala de todos os itens da área de trabalho
	 * @param panel
	 * @param escala
	 * @param bg_color
	 */
	public void scale_all(Draw panel, double escala, Color bg_color){
		
		circulos_desenhados_aux = new LinkedList<Circulo>();
		circulos_desenhados_aux.addAll(ctrCirculo.circulos_desenhados);
		for (Circulo c : circulos_desenhados_aux) {
			figura_selecionada = c;
			ctrCirculo.circulos_desenhados.remove(c);
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		
		arco_circulos_desenhados_aux = new LinkedList<Circulo>();
		arco_circulos_desenhados_aux.addAll(ctrCirculo.arcos_desenhados); 
		for (Circulo c : arco_circulos_desenhados_aux){
			figura_selecionada = c;
			ctrCirculo.arcos_desenhados.remove(c);
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		elipses_desenhados_aux = new LinkedList<Elipse>();
		elipses_desenhados_aux.addAll(ctrElipse.elipses_desenhadas);
		for (Elipse c : elipses_desenhados_aux) {
			figura_selecionada = c;
			ctrElipse.elipses_desenhadas.remove(c);
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		arco_elipses_desenhados_aux = new LinkedList<Elipse>(); 
		arco_elipses_desenhados_aux.addAll(ctrElipse.arcos_desenhados);
		for (Elipse c : arco_elipses_desenhados_aux){
			figura_selecionada = c;
			 	
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		poligonos_desenhados_aux = new LinkedList<PoligonoRegular>(); 
		poligonos_desenhados_aux.addAll(ctrPoligono.poligonos_regulares_desenhados);
		for (PoligonoRegular c: poligonos_desenhados_aux) {
			figura_selecionada = c;
			ctrPoligono.poligonos_regulares_desenhados.remove(c);
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		retangulos_desenhados_aux = new LinkedList<Retangulo>(); 
		retangulos_desenhados_aux.addAll(ctrRetangulo.retangulos_desenhados);
		for (Retangulo c : retangulos_desenhados_aux) {
			figura_selecionada = c;
			ctrRetangulo.retangulos_desenhados.remove(c);
			alterar_escala_figura(panel, escala, bg_color);
		}
		
		retas_desenhados_aux = new LinkedList<Reta>(); 
		retas_desenhados_aux.addAll(ctrReta.retas_desenhadas);
		for (Reta c : retas_desenhados_aux){
			figura_selecionada = c;
			ctrReta.retas_desenhadas.remove(c);
			alterar_escala_figura(panel, escala, bg_color);
		}
	}

	/************************************************Função aux***************************************************/
	
	public void selecao_multipla(Draw panel, Ponto p1, Ponto p2, Color bg_color){
		
		// gerando o retângulo de seleção de área
		selecao_multipla = ctrRetangulo.gera_ret_selecao(p1, p2);
		ctrRetangulo.drawRetangulo(panel, new Color(255), p1, p2, TipoLinha.fina, true);
		// seleciona as figuras dentro do retângulo, e pinta elas
		for (Circulo c : ctrCirculo.circulos_desenhados) {
			if(this.interno_selecao(selecao_multipla, c.getCentro())){
				System.out.println("Circulo dentro");
			}
		}
		// verificação de arcos de circulo
		for (Circulo c : ctrCirculo.arcos_desenhados) {
			if(this.interno_selecao(selecao_multipla, c.getCentro())){
				System.out.println("Arco de circulo dentro");
			}
		}
		
		// verificação de elipses
		for (Elipse e : ctrElipse.elipses_desenhadas) {
			if(this.interno_selecao(selecao_multipla, e.getCentro())){
				System.out.println("Elipse dentro");
			}
		}
		
		// verificação de arcos de elipses
		for (Elipse e : ctrElipse.arcos_desenhados) {
			if(this.interno_selecao(selecao_multipla, e.getCentro())){
				System.out.println("Arco de elipse dentro");
			}
		}
		
		// verificação de poligonos
		for (PoligonoRegular p: ctrPoligono.poligonos_regulares_desenhados) {
			if(this.interno_selecao(selecao_multipla, p.getCentro())){
				System.out.println("Poligono dentro");
			}
		}
		
		// verificação de retangulos
		for (Retangulo r : ctrRetangulo.retangulos_desenhados) {
			if(this.interno_selecao(selecao_multipla, r.getCentro())){
				System.out.println("Retangulo dentro");
			}
		}
		
		// verificação de retas
		for (Reta r : ctrReta.retas_desenhadas) {
			if(this.interno_selecao(selecao_multipla, r.getPtMedio())){
				System.out.println("Reta dentro");
			}
		}
		System.out.println();
	}
	
	public void get_figura_dentro_seleção(Ponto p1, Draw panel){
		
		// validação do tipo de figura
//		System.out.println(figura_selecionada.getClass());
		// objetos de comparação
		if (figura_selecionada instanceof Circulo) {
			if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
				if(((Circulo) figura_selecionada).getRoatcao()==0) ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(),true);
				else ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(),true, ((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro());
				ctrCirculo.circulos_desenhados.remove(figura_selecionada);
			}
			else{
				if(((Circulo) figura_selecionada).getRoatcao()==0) ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(), true);
				else ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(), true, ((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro());
				ctrCirculo.arcos_desenhados.remove(figura_selecionada);
			}
		}
		
		else if (figura_selecionada instanceof Elipse) {
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipse(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true);
				else ctrElipse.drawElipse(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true, ((Elipse) figura_selecionada).getRoatcao(), ((Elipse) figura_selecionada).getCentro());
				ctrElipse.elipses_desenhadas.remove(figura_selecionada);
			}
			else{
				if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipseArc(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true);
				else ctrElipse.drawElipseArc(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true, ((Elipse) figura_selecionada).getRoatcao(), ((Elipse) figura_selecionada).getCentro());
				ctrElipse.arcos_desenhados.remove(figura_selecionada);
			}
		}
		
		else if (figura_selecionada instanceof Retangulo) {
			if(((Retangulo) figura_selecionada).getRoatcao() == 0) ctrRetangulo.drawRetangulo(panel, new Color(255,0,0), ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true);
			else ctrRetangulo.drawRetangulo(panel, new Color(255,0,0), ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true, ((Retangulo) figura_selecionada).getRoatcao(), ((Retangulo) figura_selecionada).getCentro());
			ctrRetangulo.retangulos_desenhados.remove(figura_selecionada);
		}
		
		else if (figura_selecionada instanceof Reta) {
			if(((Reta) figura_selecionada).getRoatcao() == 0) ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, new Color(255,0,0), ((Reta) figura_selecionada).getTipoLinha(), true);
			else ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, new Color(255,0,0), ((Reta) figura_selecionada).getTipoLinha(), true, ((Reta) figura_selecionada).getRoatcao(), ((Reta) figura_selecionada).getPtMedio());
			ctrReta.retas_desenhadas.remove(figura_selecionada);
		}
		else if (figura_selecionada instanceof PoligonoRegular) {
			if(((PoligonoRegular) figura_selecionada).getRoatcao() == 0) ctrPoligono.draw_poligono_regular(panel, new Color(255,0,0), ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true);
			else ctrPoligono.draw_poligono_regular(panel, new Color(255,0,0), ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true, ((PoligonoRegular) figura_selecionada).getRoatcao(), ((PoligonoRegular) figura_selecionada).getCentro());
			ctrPoligono.poligonos_regulares_desenhados.remove(figura_selecionada);
		}
	}
	
	/**
	 * função auxiliar para calculo de ponto de rotacionamento
	 * função para mover a figura de um ponto ao outro usando um ponto de ref
	 * @param panel
	 * @param novo_ponto
	 * @param ref
	 * @param bg_color
	 */
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
				if(((Circulo) figura_selecionada).getRoatcao() == 0) ctrCirculo.drawCirculoArch(centro, borda, ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false);
				else ctrCirculo.drawCirculoArch(centro, borda, ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, ((Circulo) figura_selecionada).getCorLinha(), ((Circulo) figura_selecionada).getTipoLinha(), false, ((Circulo) figura_selecionada).getRoatcao(), centro);
			}
		}
		
		else if (figura_selecionada instanceof Elipse) {
			
			Ponto centro_inicial = ((Elipse) figura_selecionada).getCentro();
			Ponto borda_inicial = ((Elipse) figura_selecionada).getBorda();
			
			if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
				if(((Elipse) figura_selecionada).getRoatcao() == 0) ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false);
				else ctrElipse.drawElipse(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getTipoLinha(), false, ((Elipse) figura_selecionada).getRoatcao(), centro_inicial);
			}
			else{
				if(((Elipse) figura_selecionada).getRoatcao() == 0) ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false);
				else ctrElipse.drawElipseArc(panel, ((Elipse) figura_selecionada).getCorLinha(), novo_ponto, new Ponto(borda_inicial.getX()+(novo_ponto.getX()-centro_inicial.getX()),borda_inicial.getY()+(novo_ponto.getY()-centro_inicial.getY())), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), false, ((Elipse) figura_selecionada).getRoatcao(), centro_inicial);
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
	
		/**
		 * redraw de todas as figuras já desenhadas
		 * função de auxilio em alguns desenhos
		 * @param panel
		 */
		public void draw_all_again(Draw panel){
			for (Circulo c : ctrCirculo.circulos_desenhados) {
				if(c.getRoatcao()==0)ctrCirculo.drawCirculoDDA(c.getCentro(), c.getBorda(), panel, c.getCorLinha(), c.getTipoLinha(), true);
				else ctrCirculo.drawCirculoDDA(c.getCentro(), c.getBorda(), panel, c.getCorLinha(), c.getTipoLinha(), true, c.getRoatcao(), c.getCentro());
			}
			
			for (Circulo c : ctrCirculo.arcos_desenhados){
				if(c.getRoatcao()==0) ctrCirculo.drawCirculoArch(c.getCentro(), c.getBorda(), c.getAnguloInicial(), c.getAnguloFinal(), panel, c.getCorLinha(), c.getTipoLinha(), true);
				else ctrCirculo.drawCirculoArch(c.getCentro(), c.getBorda(), c.getAnguloInicial(), c.getAnguloFinal(), panel, c.getCorLinha(), c.getTipoLinha(), true, c.getRoatcao(), c.getCentro());
			}
			
			for (Elipse e : ctrElipse.elipses_desenhadas) {
				if(e.getRoatcao()==0) ctrElipse.drawElipse(panel, e.getCorLinha(), e.getCentro(), e.getBorda(), e.getTipoLinha(), true);
				else ctrElipse.drawElipse(panel, e.getCorLinha(), e.getCentro(), e.getBorda(), e.getTipoLinha(), true, e.getRoatcao(), e.getCentro());
			}
			
			for (Elipse e : ctrElipse.arcos_desenhados){
				if(e.getRoatcao()==0) ctrElipse.drawElipseArc(panel, e.getCorLinha(), e.getCentro(), e.getBorda(), e.getAnguloInicial(), e.getAnguloFinal(), e.getTipoLinha(), true);
				else ctrElipse.drawElipseArc(panel, e.getCorLinha(), e.getCentro(), e.getBorda(), e.getAnguloInicial(), e.getAnguloFinal(), e.getTipoLinha(), true, e.getRoatcao(), e.getCentro());
			}
			
			for (PoligonoRegular p: ctrPoligono.poligonos_regulares_desenhados) {
				if(p.getRoatcao()==0) ctrPoligono.draw_poligono_regular(panel, p.getCorLinha(), p.getTipoLinha(), p.getCentro(), p.getBorda(), p.getQtdArestas(), true);
				else ctrPoligono.draw_poligono_regular(panel, p.getCorLinha(), p.getTipoLinha(), p.getCentro(), p.getBorda(), p.getQtdArestas(), true, p.getRoatcao(), p.getCentro());
			}
			
			for (Retangulo r : ctrRetangulo.retangulos_desenhados) {
				if(r.getRoatcao()==0) ctrRetangulo.drawRetangulo(panel, r.getCorLinha(), r.getLado0().getPtoInicial(), r.getLado1().getPtoInicial(), r.getTipoLinha(), true);
				else ctrRetangulo.drawRetangulo(panel, r.getCorLinha(), r.getLado0().getPtoInicial(), r.getLado1().getPtoInicial(), r.getTipoLinha(), true, r.getRoatcao(), r.getCentro());
			}
			
			for (Reta r : ctrReta.retas_desenhadas){
				if(r.getRoatcao()==0) ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, r.getCorLinha(), r.getTipoLinha(), true);
				else ctrReta.drawReta(r.getPtoInicial(), r.getPtoFinal(), panel, r.getCorLinha(), r.getTipoLinha(), true, r.getRoatcao(), r.getPtMedio());
			}
		}
		
		/**
		 * @param p1
		 * @param panel
		 */
		public void get_figura_proxima(Ponto p1, Draw panel){
			
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
//			System.out.println(figura_selecionada.getClass());
			// objetos de comparação
			if (figura_selecionada instanceof Circulo) {
				if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0){
					if(((Circulo) figura_selecionada).getRoatcao()==0) ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(),true);
					else ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(),true, ((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro());
					ctrCirculo.circulos_desenhados.remove(figura_selecionada);
				}
				else{
					if(((Circulo) figura_selecionada).getRoatcao()==0) ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(), true);
					else ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, new Color(255,0,0), ((Circulo) figura_selecionada).getTipoLinha(), true, ((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro());
					ctrCirculo.arcos_desenhados.remove(figura_selecionada);
				}
			}
			
			else if (figura_selecionada instanceof Elipse) {
				if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
					if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipse(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true);
					else ctrElipse.drawElipse(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true, ((Elipse) figura_selecionada).getRoatcao(), ((Elipse) figura_selecionada).getCentro());
					ctrElipse.elipses_desenhadas.remove(figura_selecionada);
				}
				else{
					if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipseArc(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true);
					else ctrElipse.drawElipseArc(panel, new Color(255,0,0), ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true, ((Elipse) figura_selecionada).getRoatcao(), ((Elipse) figura_selecionada).getCentro());
					ctrElipse.arcos_desenhados.remove(figura_selecionada);
				}
			}
			
			else if (figura_selecionada instanceof Retangulo) {
				if(((Retangulo) figura_selecionada).getRoatcao() == 0) ctrRetangulo.drawRetangulo(panel, new Color(255,0,0), ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true);
				else ctrRetangulo.drawRetangulo(panel, new Color(255,0,0), ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true, ((Retangulo) figura_selecionada).getRoatcao(), ((Retangulo) figura_selecionada).getCentro());
				ctrRetangulo.retangulos_desenhados.remove(figura_selecionada);
			}
			
			else if (figura_selecionada instanceof Reta) {
				if(((Reta) figura_selecionada).getRoatcao() == 0) ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, new Color(255,0,0), ((Reta) figura_selecionada).getTipoLinha(), true);
				else ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, new Color(255,0,0), ((Reta) figura_selecionada).getTipoLinha(), true, ((Reta) figura_selecionada).getRoatcao(), ((Reta) figura_selecionada).getPtMedio());
				ctrReta.retas_desenhadas.remove(figura_selecionada);
			}
			else if (figura_selecionada instanceof PoligonoRegular) {
				if(((PoligonoRegular) figura_selecionada).getRoatcao() == 0) ctrPoligono.draw_poligono_regular(panel, new Color(255,0,0), ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true);
				else ctrPoligono.draw_poligono_regular(panel, new Color(255,0,0), ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true, ((PoligonoRegular) figura_selecionada).getRoatcao(), ((PoligonoRegular) figura_selecionada).getCentro());
				ctrPoligono.poligonos_regulares_desenhados.remove(figura_selecionada);
			}
		}
		 
		/**
		 * função para apagar uma figura selecionada
		 * função de aux para desenhar e redesenhar
		 * @param panel
		 * @param bg_color
		 */
		public void apaga_figura_selecionada(Draw panel, Color bg_color){
			if (figura_selecionada instanceof Circulo)
				if(((Circulo) figura_selecionada).getAnguloInicial()==0 && ((Circulo) figura_selecionada).getAnguloFinal() ==0)
					if(((Circulo) figura_selecionada).getRoatcao()==0)ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, bg_color, ((Circulo) figura_selecionada).getTipoLinha(),true);
					else ctrCirculo.drawCirculoDDA(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), panel, bg_color, ((Circulo) figura_selecionada).getTipoLinha(),true, ((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro()); 
				else
					if(((Circulo) figura_selecionada).getRoatcao()==0) ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, bg_color, ((Circulo) figura_selecionada).getTipoLinha(), true);
					else ctrCirculo.drawCirculoArch(((Circulo) figura_selecionada).getCentro(), ((Circulo) figura_selecionada).getBorda(), ((Circulo) figura_selecionada).getAnguloInicial(), ((Circulo) figura_selecionada).getAnguloFinal(), panel, bg_color, ((Circulo) figura_selecionada).getTipoLinha(), true,((Circulo) figura_selecionada).getRoatcao(), ((Circulo) figura_selecionada).getCentro());
			
			else if (figura_selecionada instanceof Elipse) {
				if(((Elipse) figura_selecionada).getAnguloInicial()==0 && ((Elipse) figura_selecionada).getAnguloFinal() ==0){
					if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipse(panel, bg_color, ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true);
					else ctrElipse.drawElipse(panel, bg_color, ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getTipoLinha(), true, ((Elipse) figura_selecionada).getRoatcao(), ((Elipse) figura_selecionada).getCentro());
					
				}
				else{
					if(((Elipse) figura_selecionada).getRoatcao()==0) ctrElipse.drawElipseArc(panel, bg_color, ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true);
					else ctrElipse.drawElipseArc(panel, bg_color, ((Elipse) figura_selecionada).getCentro(), ((Elipse) figura_selecionada).getBorda(), ((Elipse) figura_selecionada).getAnguloInicial(), ((Elipse) figura_selecionada).getAnguloFinal(), ((Elipse) figura_selecionada).getTipoLinha(), true, ((Elipse) figura_selecionada).getRoatcao(), ((Elipse) figura_selecionada).getCentro());
				}
			}
			
			else if (figura_selecionada instanceof Retangulo) {
				if(((Retangulo) figura_selecionada).getRoatcao() == 0) ctrRetangulo.drawRetangulo(panel, bg_color, ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true);
				else ctrRetangulo.drawRetangulo(panel, bg_color, ((Retangulo) figura_selecionada).getLado0().getPtoInicial(), ((Retangulo) figura_selecionada).getLado1().getPtoInicial(), ((Retangulo) figura_selecionada).getTipoLinha(),true,((Retangulo) figura_selecionada).getRoatcao(), ((Retangulo) figura_selecionada).getCentro());
			}
			
			else if (figura_selecionada instanceof Reta) {
				if(((Reta) figura_selecionada).getRoatcao() == 0) ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, bg_color, ((Reta) figura_selecionada).getTipoLinha(), true);
				else ctrReta.drawReta(((Reta) figura_selecionada).getPtoInicial(), ((Reta) figura_selecionada).getPtoFinal(), panel, bg_color, ((Reta) figura_selecionada).getTipoLinha(), true, ((Reta) figura_selecionada).getRoatcao(), ((Reta) figura_selecionada).getPtMedio());
			}
			else if (figura_selecionada instanceof PoligonoRegular)
				if(((PoligonoRegular) figura_selecionada).getRoatcao() == 0) ctrPoligono.draw_poligono_regular(panel, bg_color, ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true);
				else ctrPoligono.draw_poligono_regular(panel, bg_color, ((PoligonoRegular) figura_selecionada).getTipoLinha(), ((PoligonoRegular) figura_selecionada).getCentro(), ((PoligonoRegular) figura_selecionada).getBorda(), ((PoligonoRegular) figura_selecionada).getQtdArestas(), true, ((PoligonoRegular) figura_selecionada).getRoatcao(), ((PoligonoRegular) figura_selecionada).getCentro());
		}
	
	/**
	 * @param plot
	 * @param pivo
	 * @param angulo
	 * @return
	 */
	public Ponto novo_ponto(Ponto plot, Ponto pivo, int angulo){
		
		double cos = Math.cos(Math.toRadians(angulo));
		double sen = Math.sin(Math.toRadians(angulo));
		
		plot.setX(plot.getX()-pivo.getX());
		plot.setY(plot.getY()-pivo.getY());
		
		int x = ((int) Math.round((plot.getX()*cos - plot.getY()*sen)));
		int y = ((int) Math.round((plot.getX()*sen + plot.getY()*cos)));
		
		x = x + pivo.getX();
		y = y + pivo.getY();
		
		return new Ponto(x, y);
	}
	
	/**
	 * Método de calculo de produto vetorial
	 * para verificar se um ponto esta à esquerda ou direita da reta(p1,p2).
	 * @param p1
	 * @param p2
	 * @param ptTeste
	 * @return
	 */
	public boolean produtoVetorial(Ponto p1, Ponto p2, Ponto ptTeste){
		// (Bx-Ax)*(Y-Ay) - (By-Ay)*(X-Ax)
		if( ( ((p2.getX() - p1.getX())*(ptTeste.getY() - p1.getY()))-((p2.getY() - p1.getY())*(ptTeste.getX() - p2.getX())) ) >= 0)
			return false;
		return true;
	}
	
	/**
	 * Método de calculo de produto vetorial
	 * para verificar se um ponto esta à esquerda ou direita da reta(r).
	 * @param r
	 * @param ptTeste
	 * @return
	 */
	public boolean produtoVetorial(Reta r, Ponto ptTeste){
		// (Bx-Ax)*(Y-Ay) - (By-Ay)*(X-Ax)
		if( ( ((r.getPtoFinal().getX() - r.getPtoInicial().getX())*(ptTeste.getY() - r.getPtoInicial().getY()))-((r.getPtoFinal().getY() - r.getPtoInicial().getY())*(ptTeste.getX() - r.getPtoFinal().getX())) ) >= 0)
			return false;
		return true;
	}
	
	public boolean interno_selecao(Retangulo ret, Ponto p){
		if(produtoVetorial(ret.getLado0(), p) 
			&& produtoVetorial(ret.getLado1(), p) 
			&& !produtoVetorial(ret.getLado2(), p) 
			&& !produtoVetorial(ret.getLado3(), p))
			return true;
		return false;
	}
}