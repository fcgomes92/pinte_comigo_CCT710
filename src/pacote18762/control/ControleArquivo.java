package pacote18762.control;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import pacote18762.model.Circulo;
import pacote18762.model.Elipse;
import pacote18762.model.PoligonoRegular;
import pacote18762.model.Ponto;
import pacote18762.model.Reta;
import pacote18762.model.Retangulo;

public class ControleArquivo {
	
	private ControleCirculo ctrCirculo;
	private ControleElipse ctrElipse;
	private ControlePoligonoRegular ctrPoligono;
	private ControleReta ctrReta;
	private ControleRetangulo ctrRetangulo;
	private ControlePonto ctrPonto;
	
	public ControleArquivo(ControleCirculo circulo, ControleElipse elipse, ControlePoligonoRegular poligono, ControleReta reta, ControleRetangulo retangulo){
		this.ctrCirculo = circulo;
		this.ctrElipse = elipse;
		this.ctrPoligono = poligono;
		this.ctrReta = reta;
		this.ctrRetangulo = retangulo;
	}
	
	public boolean load_file(String path){
		try{
			String file_content = "";
			System.out.println(path);
			File file = new File(path);
			
			// Sai caso não exista.
			if(!file.exists()) return false;
			
			// Salva estruturas na forma de string.
			file_content = verifica_estruturas();
			
			// Escreve dados no arquivo.
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);
			
			String aux_linha[], aux_ponto[], tipo_escolhido="";
			while(((file_content=br.readLine()) != null) && file_content.length()!=0){
				if(file_content.contains("[")){
					// Retira os colchetes
					file_content = file_content.replace("[", "");
					file_content = file_content.replace("]", "");
					
					// Realiza o split da linha lida em pares separados por virgula
					aux_linha = file_content.split("/");
					System.out.println(aux_linha[0]);
					
					switch (tipo_escolhido){
						case "C":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1 = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2 = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Angulos
							aux_ponto = aux_linha[2].split(",");
							int anguloI = Integer.parseInt(aux_ponto[0]);
							int anguloF = Integer.parseInt(aux_ponto[1]);
							// Raio e rotação
							aux_ponto = aux_linha[3].split(",");
							double raio = Double.parseDouble(aux_ponto[0]); 
							int rot = Integer.parseInt(aux_ponto[1]);
							// Componentes da cor
							aux_ponto = aux_linha[4].split(",");
							Color cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							
							Circulo c = new Circulo();
							c.setCentro(p1);
							c.setBorda(p2);
							c.setAnguloInicial(anguloI);
							c.setAnguloFinal(anguloF);
							c.setRoatcao(rot);
							c.setRaio(raio);
							c.setCorLinha(cor);
							ctrCirculo.circulos_desenhados.add(c);
							break;
					
						case "AC":
							
							break;
							
						case "E":
							
							break;
					
						case "AE":
							
							break;
							
						case "P":
							
							break;
					
						case "Ret":
							
							break;
					
						case "Reta":
							
							break;
					}
				}
				else{
					switch (file_content){
						case "Circulo":
							System.out.println("Lendo os círculos...");
							tipo_escolhido="C";
							break;
							
						case "Arco Circulo":
							System.out.println("Lendo os arcos de círculo...");
							tipo_escolhido="AC";
							break;
							
						case "Elipse":
							System.out.println("Lendo as elipses...");
							tipo_escolhido="E";
							break;
						
						case "Arco Elipse":
							System.out.println("Lendo os arcos de elipse...");
							tipo_escolhido="AE";
							break;
							
						case "Poligono":
							System.out.println("Lendo os polígonos...");
							tipo_escolhido="P";
							break;
							
						case "Retangulo":
							System.out.println("Lendo os retângulos...");
							tipo_escolhido="Ret";
							break;
							
						case "Reta":
							System.out.println("Lendo as retas...");
							tipo_escolhido="Reta";
							break;
					}
						
				}
			}
			
			br.close();
			
			return true;
			
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean save_file(String path){		
		try{
			
			String file_content = "";
			System.out.println(path);
			File file = new File(path);
			
			// Cria arquivo caso não exista.
			if(!file.exists()) file.createNewFile();
			
			// Salva estruturas na forma de string.
			file_content = verifica_estruturas();
			
			// Escreve dados no arquivo.
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(file_content);
			bw.close();
			
			return true;
			
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String verifica_estruturas(){
		String str = "";
		str+="Circulo\n";
		for (Circulo c : ctrCirculo.circulos_desenhados) {
			str+=c.toString();
			str+="\n";
		}
		
		str+="Arco Circulo\n";
		for (Circulo c : ctrCirculo.arcos_desenhados){
			str+=c.toString();
			str+="\n";
		}
		
		str+="Elipse\n";
		for (Elipse c : ctrElipse.elipses_desenhadas) {
			str+=c.toString();
			str+="\n";
		}
		
		str+="Arco Elipse\n";
		for (Elipse c : ctrElipse.arcos_desenhados){
			str+=c.toString();
			str+="\n";
		}
		
		str+="Poligono\n";
		for (PoligonoRegular c: ctrPoligono.poligonos_regulares_desenhados) {
			str+=c.toString();
			str+="\n";
		}
		
		str+="Retangulo\n";
		for (Retangulo c : ctrRetangulo.retangulos_desenhados) {
			str+=c.toString();
			str+="\n";
		}
		
		str+="Reta\n";
		for (Reta c : ctrReta.retas_desenhadas){
			str+=c.toString();
			str+="\n";
		}
		return str;
	}
	
	public void load_estruturas(){
		
	}
}
