package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Circulo;
import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.TipoLinha;

public class ControleCirculo extends ControleFigura{
	
	// lista de figuras já desenhadas
	public LinkedList<Circulo> circulos_desenhados = new LinkedList<Circulo>();
	public LinkedList<Circulo> arcos_desenhados = new LinkedList<Circulo>();
	
	// variaveis de aux
	private ControlePonto ctrPonto = new ControlePonto();
	private int count = 0;
	
	public Circulo drawCirculoDDA(Ponto p1, Ponto p2, Draw panel, Color cor, TipoLinha tipoLinha){
		double raio = ctrPonto.dist(p1, p2);
		int x = 0, y = Math.round((float)raio);
		double a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
		double b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
		double s = a+b;
		
		// objeto desenhado
		Circulo circulo = new Circulo(p1,p2);
		circulo.setAnguloInicial(0);
		circulo.setAnguloFinal(0);
		circulo.setRaio(raio);
		
		// add nos circulos desenhados
		circulos_desenhados.add(circulo);
		
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
		
		return circulo;
	}
	
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
	
	public Circulo drawCirculoArch(Ponto p1, Ponto p2, double anguloInicial, double anguloFinal, Draw panel, Color cor, TipoLinha tipoLinha){
		double raio = ctrPonto.dist(p1, p2);
		int x = 0, y = Math.round((float)raio);
		double a = ((Math.pow(((x+1)), 2)+Math.pow(((y)), 2)))-Math.pow(raio, 2);
		double b = ((Math.pow(((x+1)), 2)+Math.pow(((y-1)), 2)))-Math.pow(raio, 2);
		double s = a+b;
		
		
		// calculo dos pontos referentes ao angulo
		Ponto angI, angF;
		int xTemp = 0 , yTemp = 0;
		xTemp = (int) ((Math.cos(anguloInicial))*raio);
		yTemp = (int) ((Math.sin(anguloInicial))*raio);
		angI = new Ponto(p1.getX()+xTemp,p1.getY()+yTemp);
		
		xTemp = (int) ((Math.cos(anguloFinal))*raio);
		yTemp = (int) ((Math.sin(anguloFinal))*raio);
		angF = new Ponto(p1.getX()+xTemp,p1.getY()+yTemp);
		
		// objeto desenhado
		Circulo arco = new Circulo(p1,p2);
		arco.setAnguloInicial(anguloInicial);
		arco.setAnguloFinal(anguloFinal);
		arco.setRaio(raio);
				
		// add nos circulos desenhados
		arcos_desenhados.add(arco);
		
		
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
		
		return arco;
	}
	
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
	
	public boolean produtoVetorial(Ponto anguloI, Ponto anguloF, Ponto ptTeste){
		// (Bx-Ax)*(Y-Ay) - (By-Ay)*(X-Ax)
		if( ( ((anguloF.getX() - anguloI.getX())*(ptTeste.getY() - anguloI.getY()))-((anguloF.getY() - anguloI.getY())*(ptTeste.getX() - anguloI.getX())) ) >= 0)
			return false;
		return true;
	}
}