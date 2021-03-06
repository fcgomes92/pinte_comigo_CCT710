package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Circulo;
import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.TipoLinha;

/**
 * Classe de controle de objetos Circulo
 * @author gomes
 *
 */
public class ControleCirculo extends ControleFigura{
	
	// lista de figuras já desenhadas
	public LinkedList<Circulo> circulos_desenhados = new LinkedList<Circulo>();
	public LinkedList<Circulo> arcos_desenhados = new LinkedList<Circulo>();
	
	// variaveis de aux
	private ControlePonto ctrPonto = new ControlePonto();
	private int count = 0;
	
	/**
	 * Método de desenho de círculos utilizando o
	 * o método DDA.
	 * @param p1
	 * @param p2
	 * @param panel
	 * @param cor
	 * @param tipoLinha
	 * @param redraw
	 */
	public void drawCirculoDDA(Ponto p1, Ponto p2, Draw panel, Color cor, TipoLinha tipoLinha, boolean redraw){
		double raio = ctrPonto.dist(p1, p2);
		int x = 0, y = Math.round((float)raio);
		double a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
		double b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
		double s = a+b;
		
		// objeto desenhado
		Circulo circulo = new Circulo(p1,p2);
		circulo.setRoatcao(0);
		circulo.setAnguloInicial(0);
		circulo.setAnguloFinal(0);
		circulo.setRaio(raio);
		circulo.setCorLinha(cor);
		circulo.setTipoLinha(tipoLinha);
		
		// add nos circulos desenhados
		if(!redraw)circulos_desenhados.add(circulo);
		
		// parte para desenahar
		//draw frist pixels
		plotPontoPixel(p1, panel, cor, x, y, tipoLinha);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixel(p1, panel, cor, x+i, y, tipoLinha);
				plotPontoPixel(p1, panel, cor, x, y-i, tipoLinha);
			}
		
		while(x < y){
			x++;
			if(s > 0)y--;
			// draw pixels
			plotPontoPixel(p1, panel, cor, x, y, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixel(p1, panel, cor, x+i, y, tipoLinha);
					plotPontoPixel(p1, panel, cor, x, y-i, tipoLinha);
				}
			
			a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
			b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
			s=a+b;
		}
		
		// desenha ultimos pixels
		plotPontoPixel(p1, panel, cor, x, y, tipoLinha);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixel(p1, panel, cor, x+i, y, tipoLinha);
				plotPontoPixel(p1, panel, cor, x, y-i, tipoLinha);
			}
	}
	
	/**
	 * Método de plotagem de pixels.
	 * Utilizado como método auxiliar de drawCirculoDDA.
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param tipoLinha
	 */
	public void plotPontoPixel(Ponto p1, Draw panel, Color cor, int x, int y, TipoLinha tipoLinha){
		// pontos por octante
				Ponto oct1 = new Ponto(p1.getX()+x,p1.getY()+y);
				Ponto oct2 = new Ponto(p1.getX()-x,p1.getY()+y);
				Ponto oct3 = new Ponto(p1.getX()+x,p1.getY()-y);
				Ponto oct4 = new Ponto(p1.getX()-x,p1.getY()-y);
				Ponto oct5 = new Ponto(p1.getX()+y,p1.getY()+x);
				Ponto oct6 = new Ponto(p1.getX()-y,p1.getY()+x);
				Ponto oct7 = new Ponto(p1.getX()+y,p1.getY()-x);
				Ponto oct8 = new Ponto(p1.getX()-y,p1.getY()-x);
				
				if (tipoLinha == TipoLinha.pontilhada){
					if(count==2){
						count = 3;
					}
					else if(count == 3) count = 0;
					else{
						this.drawPixel(panel, oct1, cor, tipoLinha);
						this.drawPixel(panel, oct2, cor, tipoLinha);
						this.drawPixel(panel, oct3, cor, tipoLinha);
						this.drawPixel(panel, oct4, cor, tipoLinha);
						this.drawPixel(panel, oct5, cor, tipoLinha);
						this.drawPixel(panel, oct6, cor, tipoLinha);
						this.drawPixel(panel, oct7, cor, tipoLinha);
						this.drawPixel(panel, oct8, cor, tipoLinha);
						count++;
					}
				}
				else{
					this.drawPixel(panel, oct1, cor, tipoLinha);
					this.drawPixel(panel, oct2, cor, tipoLinha);
					this.drawPixel(panel, oct3, cor, tipoLinha);
					this.drawPixel(panel, oct4, cor, tipoLinha);
					this.drawPixel(panel, oct5, cor, tipoLinha);
					this.drawPixel(panel, oct6, cor, tipoLinha);
					this.drawPixel(panel, oct7, cor, tipoLinha);
					this.drawPixel(panel, oct8, cor, tipoLinha);
				}
	}
	
	
	/*********************************************************************************************************************************************/
	
	/**
	 * Método de calculo de pontos do arco de cículo.
	 * @param p1
	 * @param p2
	 * @param anguloInicial
	 * @param anguloFinal
	 * @param panel
	 * @param cor
	 * @param tipoLinha
	 * @param redraw
	 */
	public void drawCirculoArch(Ponto p1, Ponto p2, int anguloInicial, int anguloFinal, Draw panel, Color cor, TipoLinha tipoLinha, boolean redraw){
		double raio = ctrPonto.dist(p1, p2);
		int x = 0, y = Math.round((float)raio);
		double a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
		double b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
		double s = a+b;
		
		
		// calculo dos pontos referentes ao angulo
		Ponto angI, angF;
		int xTemp = 0 , yTemp = 0;
		xTemp = (int) Math.round(((Math.cos(Math.toRadians(anguloInicial)))*raio));
		yTemp = (int) Math.round(((Math.sin(Math.toRadians(anguloInicial)))*raio));
		angI = new Ponto(p1.getX()+xTemp,p1.getY()+yTemp);
		
		xTemp = (int) Math.round(((Math.cos(Math.toRadians(anguloFinal)))*raio));
		yTemp = (int) Math.round(((Math.sin(Math.toRadians(anguloFinal)))*raio));
		angF = new Ponto(p1.getX()+xTemp,p1.getY()+yTemp);
		
		// objeto desenhado
		Circulo arco = new Circulo(p1,p2);
		arco.setRoatcao(0);
		arco.setAnguloInicial(anguloInicial);
		arco.setAnguloFinal(anguloFinal);
		arco.setRaio(raio);
		arco.setCorLinha(cor);
		arco.setTipoLinha(tipoLinha);
				
		// add nos circulos desenhados
		if(!redraw)arcos_desenhados.add(arco);
		
		
		//draw frist pixels
		plotPontoPixelArch(p1, panel, cor, x, y, angI, angF, tipoLinha);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixelArch(p1, panel, cor, x+i, y, angI, angF, tipoLinha);
				plotPontoPixelArch(p1, panel, cor, x, y-i, angI, angF, tipoLinha);
			}
		
		while(x < y){
			x++;
			if(s > 0)y--;
			// draw pixels
			plotPontoPixelArch(p1, panel, cor, x, y, angI, angF, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixelArch(p1, panel, cor, x+i, y, angI, angF, tipoLinha);
					plotPontoPixelArch(p1, panel, cor, x, y-i, angI, angF, tipoLinha);
				}
			
			a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
			b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
			s=a+b;
		}
		
		// desenha ultimos pixels
		plotPontoPixelArch(p1, panel, cor, x, y, angI, angF, tipoLinha);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixelArch(p1, panel, cor, x+i, y, angI, angF, tipoLinha);
				plotPontoPixelArch(p1, panel, cor, x, y-i, angI, angF, tipoLinha);
			}
	}
	
	/**
	 * Método de plot de pontos do arco de círculo.
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param ptAnguloI
	 * @param ptAnguloF
	 * @param tipoLinha
	 */
	public void plotPontoPixelArch(Ponto p1, Draw panel, Color cor, int x, int y, Ponto ptAnguloI, Ponto ptAnguloF, TipoLinha tipoLinha){
		
		// pontos por octante
		Ponto oct1 = new Ponto(p1.getX()+y,p1.getY()-x); // octante 1
		Ponto oct2 = new Ponto(p1.getX()+x,p1.getY()-y); // octante 2
		Ponto oct3 = new Ponto(p1.getX()-x,p1.getY()-y); // octante 3
		Ponto oct4 = new Ponto(p1.getX()-y,p1.getY()-x); // octante 4
		Ponto oct5 = new Ponto(p1.getX()-y,p1.getY()+x); // octante 5
		Ponto oct6 = new Ponto(p1.getX()-x,p1.getY()+y); // octante 6
		Ponto oct7 = new Ponto(p1.getX()+x,p1.getY()+y); // octante 7
		Ponto oct8 = new Ponto(p1.getX()+y,p1.getY()+x); // octante 8
		
		if (tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count = 3;
			}
			else if(count == 3) count = 0;
			else{
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct1)) this.drawPixel(panel, oct1, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct2)) this.drawPixel(panel, oct2, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct3)) this.drawPixel(panel, oct3, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct4)) this.drawPixel(panel, oct4, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct5)) this.drawPixel(panel, oct5, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct6)) this.drawPixel(panel, oct6, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct7)) this.drawPixel(panel, oct7, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct8)) this.drawPixel(panel, oct8, cor, tipoLinha);
				count++;
			}
		}
		else{
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct1)) this.drawPixel(panel, oct1, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct2)) this.drawPixel(panel, oct2, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct3)) this.drawPixel(panel, oct3, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct4)) this.drawPixel(panel, oct4, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct5)) this.drawPixel(panel, oct5, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct6)) this.drawPixel(panel, oct6, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct7)) this.drawPixel(panel, oct7, cor, tipoLinha);				
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct8)) this.drawPixel(panel, oct8, cor, tipoLinha);
		}
	}
	
	/************************************************Função para figuras rotacionadas***************************************************/
	
	
	/**
	 * Método de cálculo de pontos de circulo
	 * por DDA para figuras rotacionadas.
	 * @param p1
	 * @param p2
	 * @param panel
	 * @param cor
	 * @param tipoLinha
	 * @param redraw
	 * @param angulo
	 * @param pivo
	 */
	public void drawCirculoDDA(Ponto p1, Ponto p2, Draw panel, Color cor, TipoLinha tipoLinha, boolean redraw, int angulo, Ponto pivo){
		double raio = ctrPonto.dist(p1, p2);
		int x = 0, y = Math.round((float)raio);
		double a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
		double b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
		double s = a+b;
		
		// objeto desenhado
		Circulo circulo = new Circulo(p1,p2);
		circulo.setRoatcao(angulo);
		circulo.setAnguloInicial(0);
		circulo.setAnguloFinal(0);
		circulo.setRaio(raio);
		circulo.setCorLinha(cor);
		circulo.setTipoLinha(tipoLinha);
		
		// add nos circulos desenhados
		if(!redraw)circulos_desenhados.add(circulo);
		
		// parte para desenahar
		//draw frist pixels
		plotPontoPixel(p1, panel, cor, x, y, tipoLinha, angulo, pivo);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixel(p1, panel, cor, x+i, y, tipoLinha, angulo, pivo);
				plotPontoPixel(p1, panel, cor, x, y-i, tipoLinha, angulo, pivo);
			}
		
		while(x < y){
			x++;
			if(s > 0)y--;
			// draw pixels
			plotPontoPixel(p1, panel, cor, x, y, tipoLinha, angulo, pivo);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixel(p1, panel, cor, x+i, y, tipoLinha, angulo, pivo);
					plotPontoPixel(p1, panel, cor, x, y-i, tipoLinha, angulo, pivo);
				}
			
			a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
			b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
			s=a+b;
		}
		
		// desenha ultimos pixels
		plotPontoPixel(p1, panel, cor, x, y, tipoLinha);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixel(p1, panel, cor, x+i, y, tipoLinha, angulo, pivo);
				plotPontoPixel(p1, panel, cor, x, y-i, tipoLinha, angulo, pivo);
			}
	}
	
	/**
	 * Método de plot de pontos para figuras rotacionadas.
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param tipoLinha
	 * @param angulo
	 * @param pivo
	 */
	public void plotPontoPixel(Ponto p1, Draw panel, Color cor, int x, int y, TipoLinha tipoLinha, int angulo, Ponto pivo){
		// pontos por octante
				Ponto oct1 = new Ponto(p1.getX()+x,p1.getY()+y);
				Ponto oct2 = new Ponto(p1.getX()-x,p1.getY()+y);
				Ponto oct3 = new Ponto(p1.getX()+x,p1.getY()-y);
				Ponto oct4 = new Ponto(p1.getX()-x,p1.getY()-y);
				Ponto oct5 = new Ponto(p1.getX()+y,p1.getY()+x);
				Ponto oct6 = new Ponto(p1.getX()-y,p1.getY()+x);
				Ponto oct7 = new Ponto(p1.getX()+y,p1.getY()-x);
				Ponto oct8 = new Ponto(p1.getX()-y,p1.getY()-x);
				
				oct1 = new Ponto(this.novo_ponto(oct1, pivo, angulo));
				oct2 = new Ponto(this.novo_ponto(oct2, pivo, angulo));
				oct3 = new Ponto(this.novo_ponto(oct3, pivo, angulo));
				oct4 = new Ponto(this.novo_ponto(oct4, pivo, angulo));
				oct5 = new Ponto(this.novo_ponto(oct5, pivo, angulo));
				oct6 = new Ponto(this.novo_ponto(oct6, pivo, angulo));
				oct7 = new Ponto(this.novo_ponto(oct7, pivo, angulo));
				oct8 = new Ponto(this.novo_ponto(oct8, pivo, angulo));
				
				if (tipoLinha == TipoLinha.pontilhada){
					if(count==2){
						count = 3;
					}
					else if(count == 3) count = 0;
					else{
						this.drawPixel(panel, oct1, cor, tipoLinha);
						this.drawPixel(panel, oct2, cor, tipoLinha);
						this.drawPixel(panel, oct3, cor, tipoLinha);
						this.drawPixel(panel, oct4, cor, tipoLinha);
						this.drawPixel(panel, oct5, cor, tipoLinha);
						this.drawPixel(panel, oct6, cor, tipoLinha);
						this.drawPixel(panel, oct7, cor, tipoLinha);
						this.drawPixel(panel, oct8, cor, tipoLinha);
						count++;
					}
				}
				else{
					this.drawPixel(panel, oct1, cor, tipoLinha);
					this.drawPixel(panel, oct2, cor, tipoLinha);
					this.drawPixel(panel, oct3, cor, tipoLinha);
					this.drawPixel(panel, oct4, cor, tipoLinha);
					this.drawPixel(panel, oct5, cor, tipoLinha);
					this.drawPixel(panel, oct6, cor, tipoLinha);
					this.drawPixel(panel, oct7, cor, tipoLinha);
					this.drawPixel(panel, oct8, cor, tipoLinha);
				}
	}
	
	/**
	 * Método de calculo de pontos para arcos
	 * de círculos rotacionados.
	 * @param p1
	 * @param p2
	 * @param anguloInicial
	 * @param anguloFinal
	 * @param panel
	 * @param cor
	 * @param tipoLinha
	 * @param redraw
	 * @param angulo
	 * @param pivo
	 */
	public void drawCirculoArch(Ponto p1, Ponto p2, int anguloInicial, int anguloFinal, Draw panel, Color cor, TipoLinha tipoLinha, boolean redraw, int angulo, Ponto pivo){
		double raio = ctrPonto.dist(p1, p2);
		int x = 0, y = Math.round((float)raio);
		double a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
		double b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
		double s = a+b;
		
		
		// calculo dos pontos referentes ao angulo
		Ponto angI, angF;
		int xTemp = 0 , yTemp = 0;
		xTemp = (int) Math.round(((Math.cos(Math.toRadians(anguloInicial)))*raio));
		yTemp = (int) Math.round(((Math.sin(Math.toRadians(anguloInicial)))*raio));
		angI = new Ponto(p1.getX()+xTemp,p1.getY()+yTemp);
				
		xTemp = (int) Math.round(((Math.cos(Math.toRadians(anguloFinal)))*raio));
		yTemp = (int) Math.round(((Math.sin(Math.toRadians(anguloFinal)))*raio));
		angF = new Ponto(p1.getX()+xTemp,p1.getY()+yTemp);
		
		// objeto desenhado
		Circulo arco = new Circulo(p1,p2);
		arco.setRoatcao(angulo);
		arco.setAnguloInicial(anguloInicial);
		arco.setAnguloFinal(anguloFinal);
		arco.setRaio(raio);
		arco.setCorLinha(cor);
		arco.setTipoLinha(tipoLinha);
				
		// add nos circulos desenhados
		if(!redraw)arcos_desenhados.add(arco);
		
		
		//draw frist pixels
		plotPontoPixelArch(p1, panel, cor, x, y, angI, angF, tipoLinha, angulo, pivo);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixelArch(p1, panel, cor, x+i, y, angI, angF, tipoLinha, angulo, pivo);
				plotPontoPixelArch(p1, panel, cor, x, y-i, angI, angF, tipoLinha, angulo, pivo);
			}
		
		while(x < y){
			x++;
			if(s > 0)y--;
			// draw pixels
			plotPontoPixelArch(p1, panel, cor, x, y, angI, angF, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixelArch(p1, panel, cor, x+i, y, angI, angF, tipoLinha, angulo, pivo);
					plotPontoPixelArch(p1, panel, cor, x, y-i, angI, angF, tipoLinha, angulo, pivo);
				}
			
			a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
			b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
			s=a+b;
		}
		
		// desenha ultimos pixels
		plotPontoPixelArch(p1, panel, cor, x, y, angI, angF, tipoLinha, angulo, pivo);
		if(tipoLinha == TipoLinha.grossa)
			for (int i = 1; i < 5; i++) {
				plotPontoPixelArch(p1, panel, cor, x+i, y, angI, angF, tipoLinha, angulo, pivo);
				plotPontoPixelArch(p1, panel, cor, x, y-i, angI, angF, tipoLinha, angulo, pivo);
			}
	}
	
	/**
	 * Método de plot de pontos de arco de círculo rotacionado.
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param ptAnguloI
	 * @param ptAnguloF
	 * @param tipoLinha
	 * @param angulo
	 * @param pivo
	 */
	public void plotPontoPixelArch(Ponto p1, Draw panel, Color cor, int x, int y, Ponto ptAnguloI, Ponto ptAnguloF, TipoLinha tipoLinha, int angulo, Ponto pivo){
		
		// pontos por octante
		Ponto oct1 = new Ponto(p1.getX()-x,p1.getY()-y); // octante 1
		Ponto oct2 = new Ponto(p1.getX()+x,p1.getY()-y); // octante 2
		Ponto oct3 = new Ponto(p1.getX()-x,p1.getY()-y); // octante 3
		Ponto oct4 = new Ponto(p1.getX()-y,p1.getY()-x); // octante 4
		Ponto oct5 = new Ponto(p1.getX()-y,p1.getY()+x); // octante 5
		Ponto oct6 = new Ponto(p1.getX()-x,p1.getY()+y); // octante 6
		Ponto oct7 = new Ponto(p1.getX()+x,p1.getY()+y); // octante 7
		Ponto oct8 = new Ponto(p1.getX()+y,p1.getY()+x); // octante 8
		
		oct1 = new Ponto(this.novo_ponto(oct1, pivo, angulo));
		oct2 = new Ponto(this.novo_ponto(oct2, pivo, angulo));
		oct3 = new Ponto(this.novo_ponto(oct3, pivo, angulo));
		oct4 = new Ponto(this.novo_ponto(oct4, pivo, angulo));
		oct5 = new Ponto(this.novo_ponto(oct5, pivo, angulo));
		oct6 = new Ponto(this.novo_ponto(oct6, pivo, angulo));
		oct7 = new Ponto(this.novo_ponto(oct7, pivo, angulo));
		oct8 = new Ponto(this.novo_ponto(oct8, pivo, angulo));
		
		if (tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count = 3;
			}
			else if(count == 3) count = 0;
			else{
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct1)) this.drawPixel(panel, oct1, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct2)) this.drawPixel(panel, oct2, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct3)) this.drawPixel(panel, oct3, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct4)) this.drawPixel(panel, oct4, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct5)) this.drawPixel(panel, oct5, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct6)) this.drawPixel(panel, oct6, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct7)) this.drawPixel(panel, oct7, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct8)) this.drawPixel(panel, oct8, cor, tipoLinha);
				count++;
			}
		}
		else{
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct1)) this.drawPixel(panel, oct1, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct2)) this.drawPixel(panel, oct2, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct3)) this.drawPixel(panel, oct3, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct4)) this.drawPixel(panel, oct4, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct5)) this.drawPixel(panel, oct5, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct6)) this.drawPixel(panel, oct6, cor, tipoLinha);
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct7)) this.drawPixel(panel, oct7, cor, tipoLinha);				
				if(this.produtoVetorial(ptAnguloI, ptAnguloF, oct8)) this.drawPixel(panel, oct8, cor, tipoLinha);
		}
	}	
}