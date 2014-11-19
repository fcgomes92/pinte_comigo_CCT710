package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.Elipse;
import pacote18762.model.Ponto;
import pacote18762.model.TipoLinha;

/**
 * @author gomes
 *
 */
public class ControleElipse extends ControleFigura{

	// listas de figuras
	public LinkedList<Elipse> elipses_desenhadas = new LinkedList<Elipse>();
	public LinkedList<Elipse> arcos_desenhados = new LinkedList<Elipse>();	
	
	// var auxilio
	private ControlePonto ctrPonto = new ControlePonto();
	private int count = 0;

	/**
	 * @param panel
	 * @param cor
	 * @param centro
	 * @param borda
	 * @param tipoLinha
	 * @param redraw
	 */
	public void drawElipse(Draw panel, Color cor, Ponto centro, Ponto borda, TipoLinha tipoLinha, boolean redraw) {
		Ponto eixoMaior = new Ponto(borda.getX(),centro.getY()), eixoMenor = new Ponto(centro.getX(), borda.getY());
		
		// objeto a ser desenhado
		Elipse elipse = new Elipse();
		elipse.setRoatcao(0);
		elipse.setTipoLinha(tipoLinha);
		elipse.setCorLinha(cor);
		elipse.setCentro(centro);
		elipse.setBorda(borda);
		elipse.setAnguloFinal(0);
		elipse.setAnguloInicial(0);
		elipse.setEixoMaior(2*ctrPonto.dist(centro, eixoMaior));
		elipse.setEixoMenor(2*ctrPonto.dist(centro, eixoMenor));
		elipse.setDistFocal(Math.pow(elipse.getEixoMaior()/2, 2)-Math.pow(elipse.getEixoMenor()/2, 2));
		
		// add a lista obj a ser desenhado
		if(!redraw)elipses_desenhadas.add(elipse);
		
		int x = 0, y = 0;
		 	
		double a2 = Math.pow((elipse.getEixoMaior()), 2);
		double b2 = Math.pow((elipse.getEixoMenor()), 2);
		double s = 0;
		
		for (x = 0, y = Math.round((float)(elipse.getEixoMenor())), s = 2*b2+a2*(1-2*(elipse.getEixoMenor())); b2*x <= a2*y; x++){
			plotPontoPixel(centro, panel, cor, x, y, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixel(centro, panel, cor, x+i, y, tipoLinha);
					plotPontoPixel(centro, panel, cor, x, y-i, tipoLinha);
				}
			
			if (s >= 0){
	            s += (4*a2) * (1 - y);
	            y--;
	        }
	        s += b2 * ((4 * x) + 6);
		}
		
		for (x = Math.round((float)elipse.getEixoMaior()), y = 0, s = 2*a2+b2*(1-2*(elipse.getEixoMaior())); a2*y <= b2*x; y++){
			plotPontoPixel(centro, panel, cor, x, y, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixel(centro, panel, cor, x, y+i, tipoLinha);
					plotPontoPixel(centro, panel, cor, x-i, y, tipoLinha);
				}
			
	        if (s >= 0){
	            s += (4*b2) * (1 - x);
	            x--;
	        }
	        s += a2 * ((4 * y) + 6);
	    }
	}
	
	/**
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param tipoLinha
	 */
	public void plotPontoPixel(Ponto p1, Draw panel, Color cor, int x, int y, TipoLinha tipoLinha){
		
		Ponto quad1 = new Ponto(p1.getX()+x,p1.getY()-y); 
		Ponto quad2 = new Ponto(p1.getX()-x,p1.getY()-y); 
		Ponto quad3 = new Ponto(p1.getX()-x,p1.getY()+y); 
		Ponto quad4 = new Ponto(p1.getX()+x,p1.getY()+y);
		
		if (tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count = 3;
			}
			else if(count == 3) count = 0;
			else{
				this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
				this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
				this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
				this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
				count++;
			}
		}
		else{
			this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
			this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
			this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
			this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
		}
	}
	
	/**************************************************Desenho de arco da elipse************************************************************************************/
	
	/**
	 * @param panel
	 * @param cor
	 * @param centro
	 * @param borda
	 * @param anguloInicial
	 * @param anguloFinal
	 * @param tipoLinha
	 * @param redraw
	 */
	public void drawElipseArc(Draw panel, Color cor, Ponto centro, Ponto borda, int anguloInicial, int anguloFinal, TipoLinha tipoLinha, boolean redraw) {
		Ponto eixoMaior = new Ponto(borda.getX(),centro.getY()), eixoMenor = new Ponto(centro.getX(), borda.getY());
		// objeto a ser desenhado
		Elipse arco = new Elipse();
		arco.setRoatcao(0);
		arco.setTipoLinha(tipoLinha);
		arco.setCorLinha(cor);
		arco.setCentro(centro);
		arco.setBorda(borda);
		arco.setAnguloFinal(anguloFinal);
		arco.setAnguloInicial(anguloInicial);
		arco.setEixoMaior(2*ctrPonto.dist(centro, eixoMaior));
		arco.setEixoMenor(2*ctrPonto.dist(centro, eixoMenor));
		arco.setDistFocal(Math.pow(arco.getEixoMaior()/2, 2)-Math.pow(arco.getEixoMenor()/2, 2));
				
		// add a lista obj a ser desenhado
		if(!redraw)arcos_desenhados.add(arco);
		
		// calculo dos pontos referentes ao angulo
		Ponto angI, angF;
		int xTemp = 0 , yTemp = 0;
		xTemp = (int) Math.round(((arco.getEixoMaior()/2)*(Math.cos(Math.toRadians(anguloInicial)))));
		yTemp = (int) Math.round(((arco.getEixoMenor()/2)*(Math.sin(Math.toRadians(anguloInicial)))));
		angI = new Ponto(centro.getX()+xTemp,centro.getY()+yTemp);
		
		xTemp = (int) Math.round(((arco.getEixoMaior()/2)*(Math.cos(Math.toRadians(anguloFinal)))));
		yTemp = (int) Math.round(((arco.getEixoMenor()/2)*(Math.sin(Math.toRadians(anguloFinal)))));
		angF = new Ponto(centro.getX()+xTemp,centro.getY()+yTemp);
		
		// inicio do calculo dos pontos
		int x = 0, y = 0;
		 	
		double a2 = Math.pow((arco.getEixoMaior()), 2);
		double b2 = Math.pow((arco.getEixoMenor()), 2);
		double s = 0;
		
		for (x = 0, y = Math.round((float)(arco.getEixoMenor())), s = 2*b2+a2*(1-2*(arco.getEixoMenor())); b2*x <= a2*y; x++){
			
			//draw frist pixels
			plotPontoPixelArc(centro, panel, cor, x, y, angI, angF, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixelArc(centro, panel, cor, x+i, y, angI, angF, tipoLinha);
					plotPontoPixelArc(centro, panel, cor, x, y-i, angI, angF, tipoLinha);
				}
			
			
			if (s >= 0){
	            s += (4*a2) * (1 - y);
	            y--;
	        }
	        s += b2 * ((4 * x) + 6);
		}
		
		for (x = Math.round((float)arco.getEixoMaior()), y = 0, s = 2*a2+b2*(1-2*(arco.getEixoMaior())); a2*y <= b2*x; y++){
			
			plotPontoPixelArc(centro, panel, cor, x, y, angI, angF, tipoLinha);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixelArc(centro, panel, cor, x, y+i, angI, angF, tipoLinha);
					plotPontoPixelArc(centro, panel, cor, x-i, y, angI, angF, tipoLinha);
				}
			
	        if (s >= 0){
	            s += (4*b2) * (1 - x);
	            x--;
	        }
	        s += a2 * ((4 * y) + 6);
	    }
	}
	
	/**
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param angI
	 * @param angF
	 * @param tipoLinha
	 */
	public void plotPontoPixelArc(Ponto p1, Draw panel, Color cor, int x, int y, Ponto angI, Ponto angF, TipoLinha tipoLinha){		
		Ponto quad1 = new Ponto(p1.getX()+x,p1.getY()-y); 
		Ponto quad2 = new Ponto(p1.getX()-x,p1.getY()-y); 
		Ponto quad3 = new Ponto(p1.getX()-x,p1.getY()+y); 
		Ponto quad4 = new Ponto(p1.getX()+x,p1.getY()+y);
		
		if (tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count = 3;
			}
			else if(count == 3) count = 0;
			else{
				if(this.produtoVetorial(angI, angF, quad1)) this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
				if(this.produtoVetorial(angI, angF, quad2)) this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
				if(this.produtoVetorial(angI, angF, quad3)) this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
				if(this.produtoVetorial(angI, angF, quad4)) this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
				count++;
			}
		}
		else{
			if(this.produtoVetorial(angI, angF, quad1)) this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
			if(this.produtoVetorial(angI, angF, quad2)) this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
			if(this.produtoVetorial(angI, angF, quad3)) this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
			if(this.produtoVetorial(angI, angF, quad4)) this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
		}
	}
	
	/************************************************Função para figuras rotacionadas***************************************************/
	
	/**
	 * @param panel
	 * @param cor
	 * @param centro
	 * @param borda
	 * @param tipoLinha
	 * @param redraw
	 * @param angulo
	 * @param pivo
	 */
	public void drawElipse(Draw panel, Color cor, Ponto centro, Ponto borda, TipoLinha tipoLinha, boolean redraw, int angulo, Ponto pivo) {
		Ponto eixoMaior = new Ponto(borda.getX(),centro.getY()), eixoMenor = new Ponto(centro.getX(), borda.getY());
		
		// objeto a ser desenhado
		Elipse elipse = new Elipse();
		elipse.setRoatcao(angulo);
		elipse.setTipoLinha(tipoLinha);
		elipse.setCorLinha(cor);
		elipse.setCentro(centro);
		elipse.setBorda(borda);
		elipse.setAnguloFinal(0);
		elipse.setAnguloInicial(0);
		elipse.setEixoMaior(2*ctrPonto.dist(centro, eixoMaior));
		elipse.setEixoMenor(2*ctrPonto.dist(centro, eixoMenor));
		elipse.setDistFocal(Math.pow(elipse.getEixoMaior()/2, 2)-Math.pow(elipse.getEixoMenor()/2, 2));
		
		// add a lista obj a ser desenhado
		if(!redraw)elipses_desenhadas.add(elipse);
		
		int x = 0, y = 0;
		 	
		double a2 = Math.pow((elipse.getEixoMaior()), 2);
		double b2 = Math.pow((elipse.getEixoMenor()), 2);
		double s = 0;
		
		for (x = 0, y = Math.round((float)(elipse.getEixoMenor())), s = 2*b2+a2*(1-2*(elipse.getEixoMenor())); b2*x <= a2*y; x++){
			plotPontoPixel(centro, panel, cor, x, y, tipoLinha, angulo, pivo);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixel(centro, panel, cor, x+i, y, tipoLinha, angulo, pivo);
					plotPontoPixel(centro, panel, cor, x, y-i, tipoLinha, angulo, pivo);
				}
			
			if (s >= 0){
	            s += (4*a2) * (1 - y);
	            y--;
	        }
	        s += b2 * ((4 * x) + 6);
		}
		
		for (x = Math.round((float)elipse.getEixoMaior()), y = 0, s = 2*a2+b2*(1-2*(elipse.getEixoMaior())); a2*y <= b2*x; y++){
			plotPontoPixel(centro, panel, cor, x, y, tipoLinha, angulo, pivo);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixel(centro, panel, cor, x, y+i, tipoLinha, angulo, pivo);
					plotPontoPixel(centro, panel, cor, x-i, y, tipoLinha, angulo, pivo);
				}
			
	        if (s >= 0){
	            s += (4*b2) * (1 - x);
	            x--;
	        }
	        s += a2 * ((4 * y) + 6);
	    }
	}
	
	/**
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
		
		Ponto quad1 = new Ponto(p1.getX()+x,p1.getY()-y); 
		Ponto quad2 = new Ponto(p1.getX()-x,p1.getY()-y); 
		Ponto quad3 = new Ponto(p1.getX()-x,p1.getY()+y); 
		Ponto quad4 = new Ponto(p1.getX()+x,p1.getY()+y);
		
		quad1 = new Ponto(this.novo_ponto(quad1, pivo, angulo));
		quad2 = new Ponto(this.novo_ponto(quad2, pivo, angulo));
		quad3 = new Ponto(this.novo_ponto(quad3, pivo, angulo)); 
		quad4 = new Ponto(this.novo_ponto(quad4, pivo, angulo));
		
		if (tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count = 3;
			}
			else if(count == 3) count = 0;
			else{
				this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
				this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
				this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
				this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
				count++;
			}
		}
		else{
			this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
			this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
			this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
			this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
		}
	}
	
	/**
	 * @param panel
	 * @param cor
	 * @param centro
	 * @param borda
	 * @param anguloInicial
	 * @param anguloFinal
	 * @param tipoLinha
	 * @param redraw
	 * @param angulo
	 * @param pivo
	 */
	public void drawElipseArc(Draw panel, Color cor, Ponto centro, Ponto borda, int anguloInicial, int anguloFinal, TipoLinha tipoLinha, boolean redraw, int angulo, Ponto pivo) {
		Ponto eixoMaior = new Ponto(borda.getX(),centro.getY()), eixoMenor = new Ponto(centro.getX(), borda.getY());
		// objeto a ser desenhado
		Elipse arco = new Elipse();
		arco.setRoatcao(angulo);
		arco.setTipoLinha(tipoLinha);
		arco.setCorLinha(cor);
		arco.setCentro(centro);
		arco.setBorda(borda);
		arco.setAnguloFinal(anguloFinal);
		arco.setAnguloInicial(anguloInicial);
		arco.setEixoMaior(2*ctrPonto.dist(centro, eixoMaior));
		arco.setEixoMenor(2*ctrPonto.dist(centro, eixoMenor));
		arco.setDistFocal(Math.pow(arco.getEixoMaior()/2, 2)-Math.pow(arco.getEixoMenor()/2, 2));
				
		// add a lista obj a ser desenhado
		if(!redraw)arcos_desenhados.add(arco);
		
		// calculo dos pontos referentes ao angulo
				Ponto angI, angF;
				int xTemp = 0 , yTemp = 0;
				xTemp = (int) Math.round(((arco.getEixoMaior()/2)*(Math.cos(Math.toRadians(anguloInicial)))));
				yTemp = (int) Math.round(((arco.getEixoMenor()/2)*(Math.sin(Math.toRadians(anguloInicial)))));
				angI = new Ponto(centro.getX()+xTemp,centro.getY()+yTemp);
				
				xTemp = (int) Math.round(((arco.getEixoMaior()/2)*(Math.cos(Math.toRadians(anguloFinal)))));
				yTemp = (int) Math.round(((arco.getEixoMenor()/2)*(Math.sin(Math.toRadians(anguloFinal)))));
				angF = new Ponto(centro.getX()+xTemp,centro.getY()+yTemp);
		
		// inicio do calculo dos pontos
		int x = 0, y = 0;
		 	
		double a2 = Math.pow((arco.getEixoMaior()), 2);
		double b2 = Math.pow((arco.getEixoMenor()), 2);
		double s = 0;
		
		for (x = 0, y = Math.round((float)(arco.getEixoMenor())), s = 2*b2+a2*(1-2*(arco.getEixoMenor())); b2*x <= a2*y; x++){
			
			//draw frist pixels
			plotPontoPixelArc(centro, panel, cor, x, y, angI, angF, tipoLinha, angulo, pivo);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixelArc(centro, panel, cor, x+i, y, angI, angF, tipoLinha, angulo, pivo);
					plotPontoPixelArc(centro, panel, cor, x, y-i, angI, angF, tipoLinha, angulo, pivo);
				}
			
			
			if (s >= 0){
	            s += (4*a2) * (1 - y);
	            y--;
	        }
	        s += b2 * ((4 * x) + 6);
		}
		
		for (x = Math.round((float)arco.getEixoMaior()), y = 0, s = 2*a2+b2*(1-2*(arco.getEixoMaior())); a2*y <= b2*x; y++){
			
			plotPontoPixelArc(centro, panel, cor, x, y, angI, angF, tipoLinha, angulo, pivo);
			if(tipoLinha == TipoLinha.grossa)
				for (int i = 1; i < 5; i++) {
					plotPontoPixelArc(centro, panel, cor, x, y+i, angI, angF, tipoLinha, angulo, pivo);
					plotPontoPixelArc(centro, panel, cor, x-i, y, angI, angF, tipoLinha, angulo, pivo);
				}
			
	        if (s >= 0){
	            s += (4*b2) * (1 - x);
	            x--;
	        }
	        s += a2 * ((4 * y) + 6);
	    }
	}
	
	/**
	 * @param p1
	 * @param panel
	 * @param cor
	 * @param x
	 * @param y
	 * @param angI
	 * @param angF
	 * @param tipoLinha
	 * @param angulo
	 * @param pivo
	 */
	public void plotPontoPixelArc(Ponto p1, Draw panel, Color cor, int x, int y, Ponto angI, Ponto angF, TipoLinha tipoLinha, int angulo, Ponto pivo){		
		Ponto quad1 = new Ponto(p1.getX()+x,p1.getY()-y); 
		Ponto quad2 = new Ponto(p1.getX()-x,p1.getY()-y); 
		Ponto quad3 = new Ponto(p1.getX()-x,p1.getY()+y); 
		Ponto quad4 = new Ponto(p1.getX()+x,p1.getY()+y);
		
		quad1 = new Ponto(this.novo_ponto(quad1, pivo, angulo));
		quad2 = new Ponto(this.novo_ponto(quad2, pivo, angulo));
		quad3 = new Ponto(this.novo_ponto(quad3, pivo, angulo)); 
		quad4 = new Ponto(this.novo_ponto(quad4, pivo, angulo));
		
		if (tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count = 3;
			}
			else if(count == 3) count = 0;
			else{
				if(this.produtoVetorial(angI, angF, quad1)) this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
				if(this.produtoVetorial(angI, angF, quad2)) this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
				if(this.produtoVetorial(angI, angF, quad3)) this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
				if(this.produtoVetorial(angI, angF, quad4)) this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
				count++;
			}
		}
		else{
			if(this.produtoVetorial(angI, angF, quad1)) this.drawPixel(panel, quad1, cor, tipoLinha); // quadrante 1 
			if(this.produtoVetorial(angI, angF, quad2)) this.drawPixel(panel, quad2, cor, tipoLinha); // quadrante 2
			if(this.produtoVetorial(angI, angF, quad3)) this.drawPixel(panel, quad3, cor, tipoLinha); // quadrante 3
			if(this.produtoVetorial(angI, angF, quad4)) this.drawPixel(panel, quad4, cor, tipoLinha); // quadrante 4
		}
	}
}
