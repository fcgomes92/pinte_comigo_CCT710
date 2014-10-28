package pacote18762.control;

import java.awt.Color;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.TipoLinha;

public class ControleReta  extends ControleFigura{
	
	// lista de objetos
	public LinkedList<Reta> retas_desenhadas = new LinkedList<Reta>();
	
	// var aux
	private int count = 0;
	
	public void drawReta(Ponto p1, Ponto p2, Draw panel, Color cor, TipoLinha tipoLinha, boolean redraw){
		Ponto auxI = null, auxF = null, temp = null;
		int dx = 0, dy = 0, erro = 0, x = 0 , y = 0,tempXi = 0, tempYi = 0,tempXf = 0, tempYf = 0; 
			// clonando o ponto original na reta em um pto temporario
			tempXi = p1.getX();
			tempXf = p2.getX();
			tempYi = p1.getY();
			tempYf = p2.getY();
			
			auxI = new Ponto(tempXi,tempYi);
			auxF = new Ponto(tempXf, tempYf);
			
			if(auxI.getX() == auxF.getX()){
				auxI.setX(tempXi);
				auxF.setX(tempXf);
			}
			else {
				if(auxI.getY() == auxF.getY()){
					auxI.setY(tempYi);
					auxF.setY(tempYf);
				}
				else{
					auxI.setY(tempYi);
					auxF.setY(tempYf);
					auxI.setX(tempXi);
					auxF.setX(tempXf);
				}
			}
			
			// add obj a ser desenhado
			Reta r = new Reta(auxI, auxF);
			r.setPtMedio(new Ponto(
					(Math.abs(Math.round(
								(auxI.getX()+auxF.getX())/2)
							)), 
					(Math.abs(Math.round(
								(auxI.getY()+auxF.getY())/2)
							))
					));
			if(!redraw)retas_desenhadas.add(r);
			
			// desenhado os primeiros pontos
			drawP(panel, auxI, cor, tipoLinha);
			drawP(panel, auxF, cor, tipoLinha);
			
			// primeiro calculo de dx dy
			dx = auxF.getX() - auxI.getX();
			dy = auxF.getY() - auxI.getY();
			
			// caso dy negativo deve-se trocar os pontos
			if(dy < 0){
				temp = auxF;
				auxF = auxI;
				auxI = temp;
				
				dx = auxF.getX() - auxI.getX();
				dy = auxF.getY() - auxI.getY();
			}
			
			// pontos iniciais e finais de calculo
			x = auxI.getX();
			y = auxI.getY();
			
			erro = 0;
			
			if(dx >= 0){
				// caso 1 ou 2
				if(Math.abs(dx) >= Math.abs(dy)){
					// caso 1
//					System.out.println("Caso 1");
					for(int i = 1 ; i < Math.abs(dx); i++){
						if(erro <= 0){
							x += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dy;
						}
						else{
							x += 1;
							y += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dy - dx;
						}
					}
				}
				else{	
					// caso 2
//					System.out.println("Caso 2");
					for(int i = 1 ; i < Math.abs(dy); i++){
						if(erro < 0){
							x += 1;
							y += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dy - dx;
						}
						else{
							y += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro -= dx;
						}
					}
				}
			}
			else{
				if(Math.abs(dx) >= Math.abs(dy)){
					// caso 3
//					System.out.println("Caso 3");
					for(int i = 1 ; i < Math.abs(dx); i++){
						if(erro < 0){
							x -= 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dy;
						}
						else{
							x -= 1;
							y += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dy + dx;
						}
					}
				}
				else{
					// caso 4
//					System.out.println("Caso 4");
					for(int i = 1 ; i < Math.abs(dy); i++){
						if(erro < 0){
							x -= 1;
							y += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dy + dx;
						}
						else{
							y += 1;
							drawP(panel, new Ponto(x,y), cor, tipoLinha);
							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
							erro += dx;
						}
					}
				}
			}
	}
	
	public void drawP(Draw panel, Ponto p1, Color cor, TipoLinha tipoLinha){
		if(tipoLinha == TipoLinha.pontilhada){
			if(count==2){
				count=3;
			}else if(count==3){
			count=0;
			}else{
				this.drawPixel(panel, p1, cor, tipoLinha);
				count++;
			}
		}
		else{
			this.drawPixel(panel, p1, cor, tipoLinha);
		}
	}
	
	/*
	 * Não será utilizado esse método, mas ficou bonito, então deixei aqui :3
	 * O método utiliza a distância de um ponto a uma reta
	 * Sendo que tambem são aplicados os conceitos de retas perpendiculares
	 * P1 e P2 definem a r1, P1 e P3 definem a reta perpendicular (r2)
	 * A reta pode ser definida pela equação: Ax + By + C
	 * Lembrando que o coeficiente angular de r1, mr1, quando multiplicado pelo
	 * coeficiente angular de r2, mr2, temos: mr1*mr2 = -1
	 * 
	 * O coeficiente angular de uma reta pode ser definido pela equação reduzida da reta : -A/B
	 */
//	public Ponto calcula_ponto_perpend(Ponto p1, Ponto p2, int dist){
//		int x1 = p1.getX(), x2 = p2.getX(), x3 = 0;
//		int y1 = p1.getY(), y2 = p2.getY(), y3 = 0;
//		
//		// coeficientes da equação da reta 
//		int a = y1 - y2, b = x2 - x1 , c = x1*y2 - x2*y1;
//		
//		double d = dist*Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
//		
//		
//		x3 = (int) Math.round(((d - b*y1 - (x1*Math.pow(b,2))/a)*a)/(Math.pow(a, 2) - Math.pow(b, 2)));
//		y3 = (int) Math.round(y1 + (x1 - x3)*b/a);
//		
//		return new Ponto(x3,y3);
//	}
	
	public void get_pontos_paralelos(Draw painel, Ponto ponto, Color cor, int deltaX, int deltaY, TipoLinha tipoLinha){
		if(deltaX == 0){
			for (int i = 1; i < 5; i++)
				this.drawP(painel, new Ponto(ponto.getX()+i, ponto.getY()), cor, tipoLinha);
		} 
		else if(deltaY == 0){
			for (int i = 1; i < 5; i++)
			this.drawP(painel, new Ponto(ponto.getX(), ponto.getY()+i), cor, tipoLinha);
		} 
		else if(deltaX <= deltaY){
			for (int i = 1; i < 5; i++)				
				this.drawP(painel, new Ponto(ponto.getX()+i, ponto.getY()), cor, tipoLinha);
			
			if (deltaX < 0){
				for (int i = 1; i < 5; i++)
					this.drawP(painel, new Ponto(ponto.getX(), ponto.getY()+i), cor, tipoLinha);
			}
		} 
		else if (deltaX > deltaY){
			for (int i = 1; i < 5; i++)
				this.drawP(painel, new Ponto(ponto.getX(), ponto.getY()-i), cor, tipoLinha);
		}
		else{
			System.out.println("Não peguei nenhum... #xatiado");
		}
	}
	
	
//	// este método é utilizado por outros controles que utilizam retas
//	// ele se difere do primeiro método de desenho pelo fato de não add
//	// as retas desenhadas em uma lista.
//	public void drawReta(Ponto p1, Ponto p2, Draw panel, Color cor, TipoLinha tipoLinha){
//		Ponto auxI = null, auxF = null, temp = null;
//		int dx = 0, dy = 0, erro = 0, x = 0 , y = 0,tempXi = 0, tempYi = 0,tempXf = 0, tempYf = 0; 
//			// clonando o ponto original na reta em um pto temporario
//			tempXi = p1.getX();
//			tempXf = p2.getX();
//			tempYi = p1.getY();
//			tempYf = p2.getY();
//			
//			auxI = new Ponto(tempXi,tempYi);
//			auxF = new Ponto(tempXf, tempYf);
//			
//			if(auxI.getX() == auxF.getX()){
//				auxI.setX(tempXi);
//				auxF.setX(tempXf);
//			}
//			else {
//				if(auxI.getY() == auxF.getY()){
//					auxI.setY(tempYi);
//					auxF.setY(tempYf);
//				}
//				else{
//					auxI.setY(tempYi);
//					auxF.setY(tempYf);
//					auxI.setX(tempXi);
//					auxF.setX(tempXf);
//				}
//			}
//			
//			// desenhado os primeiros pontos
//			drawP(panel, auxI, cor, tipoLinha);
//			drawP(panel, auxF, cor, tipoLinha);
//			
//			// primeiro calculo de dx dy
//			dx = auxF.getX() - auxI.getX();
//			dy = auxF.getY() - auxI.getY();
//			
//			// caso dy negativo deve-se trocar os pontos
//			if(dy < 0){
//				temp = auxF;
//				auxF = auxI;
//				auxI = temp;
//				
//				dx = auxF.getX() - auxI.getX();
//				dy = auxF.getY() - auxI.getY();
//			}
//			
//			// pontos iniciais e finais de calculo
//			x = auxI.getX();
//			y = auxI.getY();
//			
//			erro = 0;
//			
//			if(dx >= 0){
//				// caso 1 ou 2
//				if(Math.abs(dx) >= Math.abs(dy)){
//					// caso 1
////					System.out.println("Caso 1");
//					for(int i = 1 ; i < Math.abs(dx); i++){
//						if(erro <= 0){
//							x += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dy;
//						}
//						else{
//							x += 1;
//							y += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dy - dx;
//						}
//					}
//				}
//				else{	
//					// caso 2
////					System.out.println("Caso 2");
//					for(int i = 1 ; i < Math.abs(dy); i++){
//						if(erro < 0){
//							x += 1;
//							y += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dy - dx;
//						}
//						else{
//							y += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro -= dx;
//						}
//					}
//				}
//			}
//			else{
//				if(Math.abs(dx) >= Math.abs(dy)){
//					// caso 3
////					System.out.println("Caso 3");
//					for(int i = 1 ; i < Math.abs(dx); i++){
//						if(erro < 0){
//							x -= 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dy;
//						}
//						else{
//							x -= 1;
//							y += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dy + dx;
//						}
//					}
//				}
//				else{
//					// caso 4
////					System.out.println("Caso 4");
//					for(int i = 1 ; i < Math.abs(dy); i++){
//						if(erro < 0){
//							x -= 1;
//							y += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dy + dx;
//						}
//						else{
//							y += 1;
//							drawP(panel, new Ponto(x,y), cor, tipoLinha);
//							if(tipoLinha == TipoLinha.grossa) this.get_pontos_paralelos(panel, new Ponto(x,y), cor, dx, dy, tipoLinha);
//							erro += dx;
//						}
//					}
//				}
//			}
//	}
	
}