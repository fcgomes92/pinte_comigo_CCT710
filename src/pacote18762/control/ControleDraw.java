package pacote18762.control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.TipoLinha;

public class ControleDraw {
	
	private Graphics g;
	public BufferedImage img;
	public LinkedList<Ponto> pontos = new LinkedList<Ponto>();
	
	public void drawPixel(Draw panel, Ponto ponto, Color c, TipoLinha tipoLinha){
		g = panel.getGraphics();
		g.setColor(c);
		g.drawLine(ponto.getX(), ponto.getY(), ponto.getX(), ponto.getY());
	}

	// metodos de pintura
	public void preencher(Draw panel, Ponto p, Color cor_nova, Color cor_velha){
		if(p.getX() > 0 && p.getX() < panel.getWidth() && p.getY() > 0 && p.getY() < panel.getHeight()){
			Ponto p1 = new Ponto(p.getX()+1,p.getY()), p2 = new Ponto(p.getX()-1,p.getY()), p3 = new Ponto(p.getX(),p.getY()+1), p4 = new Ponto(p.getX(),p.getY()-1);
//			Color cor_ponto = panel.getGraphics().create(p.getX(),p.getY(),0,0).getColor();
//			Color cor_ponto = panel.getComponentAt(p.getX(), p.getY()).getBackground();
			if (p.getX() < panel.getWidth() && p.getX() > 0 && p.getY() < panel.getHeight() && p.getY() > 0){
				Color cor_ponto = new Color(img.getRGB(p.getX(), p.getY()));
				if(cor_ponto.getRGB()==cor_velha.getRGB()){
					this.drawPixel(panel, p, cor_nova, TipoLinha.fina);
					img.setRGB(p.getX(), p.getY(), cor_nova.getRGB());
					if(img.getRGB(p1.getX(), p1.getY())==cor_velha.getRGB() && 
							p1.getX() < panel.getWidth() && p1.getX() > 0 && p1.getY() < panel.getHeight() && p1.getY() > 0)this.preencher(panel, p1, cor_nova, cor_velha);
					if(img.getRGB(p2.getX(), p2.getY())==cor_velha.getRGB() &&
							p2.getX() < panel.getWidth() && p2.getX() > 0 && p2.getY() < panel.getHeight() && p2.getY() > 0)this.preencher(panel, p2, cor_nova, cor_velha);
					if(img.getRGB(p3.getX(), p3.getY())==cor_velha.getRGB() &&
							p3.getX() < panel.getWidth() && p3.getX() > 0 && p3.getY() < panel.getHeight() && p3.getY() > 0)this.preencher(panel, p3, cor_nova, cor_velha);
					if(img.getRGB(p4.getX(), p4.getY())==cor_velha.getRGB() &&
							p4.getX() < panel.getWidth() && p4.getX() > 0 && p4.getY() < panel.getHeight() && p4.getY() > 0)this.preencher(panel, p4, cor_nova, cor_velha);
				}
			}
		}
	}
	
	public void fill(Draw panel, Ponto p, Color cor_nova, Color cor_inicial){
		
		if(cor_nova.getRGB() == cor_inicial.getRGB()) return;
		
		int x_inicial = p.getX(), y_inicial = p.getY();
		int x_incremental = x_inicial, y_incremental = y_inicial; 
		
		Color cor_ponto_teste1 = new Color(img.getRGB(x_inicial, y_inicial));
		Color cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
		
		do{
			
			cor_ponto_teste1 = new Color(img.getRGB(x_incremental, y_incremental));
			this.drawPixel(panel, new Ponto(x_incremental,y_incremental), cor_nova, TipoLinha.fina);
			img.setRGB(x_incremental, y_incremental, cor_nova.getRGB());
			
			do{
				cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
				this.drawPixel(panel, new Ponto(x_incremental,y_incremental), cor_nova, TipoLinha.fina);
				img.setRGB(x_incremental, y_incremental, cor_nova.getRGB());
				y_incremental++;
			}while(cor_ponto_teste2.getRGB() == cor_inicial.getRGB() && y_incremental > 0 && y_incremental  < panel.getHeight());
			
			y_incremental = y_inicial -1;
			cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
			
			do{
				cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
				this.drawPixel(panel, new Ponto(x_incremental,y_incremental), cor_nova, TipoLinha.fina);
				img.setRGB(x_incremental, y_incremental, cor_nova.getRGB());
				y_incremental--;
			}while(cor_ponto_teste2.getRGB() == cor_inicial.getRGB() && y_incremental > 0 && y_incremental  < panel.getHeight());
			
			x_incremental--;
		}while((cor_ponto_teste1.getRGB() == cor_inicial.getRGB()) && x_incremental > 0 && x_incremental < panel.getWidth());
		
		x_incremental = x_inicial;
		y_incremental = y_inicial;
		
		do{

			cor_ponto_teste1 = new Color(img.getRGB(x_incremental, y_incremental));
			this.drawPixel(panel, new Ponto(x_incremental,y_incremental), cor_nova, TipoLinha.fina);
			img.setRGB(x_incremental, y_incremental, cor_nova.getRGB());
			
			do{
				cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
				this.drawPixel(panel, new Ponto(x_incremental,y_incremental), cor_nova, TipoLinha.fina);
				img.setRGB(x_incremental, y_incremental, cor_nova.getRGB());
				y_incremental++;
			}while(cor_ponto_teste2.getRGB() == cor_inicial.getRGB() && y_incremental > 0 && y_incremental  < panel.getHeight());
			
			y_incremental = y_inicial -1;
			cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
			
			do{
				cor_ponto_teste2 = new Color(img.getRGB(x_incremental, y_incremental));
				this.drawPixel(panel, new Ponto(x_incremental,y_incremental), cor_nova, TipoLinha.fina);
				img.setRGB(x_incremental, y_incremental, cor_nova.getRGB());
				y_incremental--;
			}while(cor_ponto_teste2.getRGB() == cor_inicial.getRGB() && y_incremental > 0 && y_incremental  < panel.getHeight());
			
			x_incremental++;
		}while((cor_ponto_teste1.getRGB() == cor_inicial.getRGB()) && x_incremental > 0 && x_incremental < panel.getWidth());
		
	}
}
