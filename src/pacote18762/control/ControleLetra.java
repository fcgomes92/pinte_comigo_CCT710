package pacote18762.control;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pacote18762.model.Draw;
import pacote18762.model.Letra;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.TamanhoLetra;
import pacote18762.model.TipoLinha;

public class ControleLetra {
	
	private List<Letra> alfabeto = new LinkedList<Letra>();
	private ControleReta ctrReta = new ControleReta();
	
	public void drawLetra(Draw panel, Color cor, Ponto ptIncial, int valor_letra, TipoLinha tipoLinha, TamanhoLetra tamanho_letra){
		
		Ponto p1Temp, p2Temp;
		
		if(tamanho_letra == TamanhoLetra.tamanho8x8){
			for (Reta r : alfabeto.get(valor_letra-47).getRetas()){
				p1Temp = new Ponto(r.getPtoInicial().getX() + ptIncial.getX(), r.getPtoInicial().getY() + ptIncial.getY());
				p2Temp = new Ponto(r.getPtoFinal().getX() + ptIncial.getX(), r.getPtoFinal().getY() + ptIncial.getY());
				ctrReta.drawReta(p1Temp,p2Temp, panel, cor, tipoLinha);
			}
		}
		else if(tamanho_letra == TamanhoLetra.tamanho16x16){
			for (Reta r : alfabeto.get(valor_letra-47).getRetas()){
				p1Temp = new Ponto(r.getPtoInicial().getX()*2 + ptIncial.getX(), r.getPtoInicial().getY()*2 + ptIncial.getY());
				p2Temp = new Ponto(r.getPtoFinal().getX()*2 + ptIncial.getX(), r.getPtoFinal().getY()*2 + ptIncial.getY());
				ctrReta.drawReta(p1Temp,p2Temp, panel, cor, tipoLinha);
			}
		}else{
			for (Reta r : alfabeto.get(valor_letra-47).getRetas()){
				p1Temp = new Ponto(r.getPtoInicial().getX()*4 + ptIncial.getX(), r.getPtoInicial().getY()*4 + ptIncial.getY());
				p2Temp = new Ponto(r.getPtoFinal().getX()*4 + ptIncial.getX(), r.getPtoFinal().getY()*4 + ptIncial.getY());
				ctrReta.drawReta(p1Temp,p2Temp, panel, cor, tipoLinha);
			}
		}
	}
	
	public List<Letra> lerArquivoLetras(String nomeArquivo) throws FileNotFoundException, IOException{
		Letra tempL = new Letra();
		FileReader fr = new FileReader(nomeArquivo);
		String str = "";
		String [] splt;
		int x1=0, y1=0, x2=0, y2=0, tempxy1, tempxy2;
		
		
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(fr);

		// Leitura das retas
		while ((str = bf.readLine()) != null) {
			tempL = new Letra();
			splt = str.split(" ");
			for(int i = 0 ; i < splt.length ; i = i + 2){
				tempxy1 = Integer.parseInt(splt[i]);
				tempxy2 = Integer.parseInt(splt[i+1]);
				x1 = tempxy1/10;
				y1 = tempxy1%10;
				x2 = tempxy2/10;
				y2 = tempxy2%10;	
				tempL.getRetas().add(new Reta(new Ponto(y1,x1),new Ponto(y2,x2)));
			}
			alfabeto.add(tempL);
		}
		return alfabeto;
	}
}
