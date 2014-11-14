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
import pacote18762.model.TipoLinha;

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
			TipoLinha tp_linha;
			Color cor;
			
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
							Ponto p1C = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2C = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Angulos
							aux_ponto = aux_linha[2].split(",");
							int anguloIC = Integer.parseInt(aux_ponto[0]);
							int anguloFC = Integer.parseInt(aux_ponto[1]);
							// Raio e rotação
							aux_ponto = aux_linha[3].split(",");
							double raioC = Double.parseDouble(aux_ponto[0]); 
							int rotC = Integer.parseInt(aux_ponto[1]);
							// Componentes da cor
							aux_ponto = aux_linha[4].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							
							Circulo cC = new Circulo();
							cC.setCentro(p1C);
							cC.setBorda(p2C);
							cC.setAnguloInicial(anguloIC);
							cC.setAnguloFinal(anguloFC);
							cC.setRoatcao(rotC);
							cC.setRaio(raioC);
							cC.setCorLinha(cor);
							ctrCirculo.circulos_desenhados.add(cC);
							break;
					
						case "AC":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1AC = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2AC = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Angulos
							aux_ponto = aux_linha[2].split(",");
							int anguloIAC = Integer.parseInt(aux_ponto[0]);
							int anguloFAC = Integer.parseInt(aux_ponto[1]);
							// Raio e rotação
							aux_ponto = aux_linha[3].split(",");
							double raioAC = Double.parseDouble(aux_ponto[0]); 
							int rotAC = Integer.parseInt(aux_ponto[1]);
							// Componentes da cor
							aux_ponto = aux_linha[4].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							
							Circulo cAC = new Circulo();
							cAC.setCentro(p1AC);
							cAC.setBorda(p2AC);
							cAC.setAnguloInicial(anguloIAC);
							cAC.setAnguloFinal(anguloFAC);
							cAC.setRoatcao(rotAC);
							cAC.setRaio(raioAC);
							cAC.setCorLinha(cor);
							ctrCirculo.arcos_desenhados.add(cAC);
							break;
							
						case "E":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1E =	 new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2E = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Angulos
							aux_ponto = aux_linha[2].split(",");
							int anguloIE = Integer.parseInt(aux_ponto[0]);
							int anguloFE = Integer.parseInt(aux_ponto[1]);
							// Raio e rotação 
							int rotE = Integer.parseInt(aux_linha[3]);
							// Componentes da cor
							aux_ponto = aux_linha[4].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							// Componente de linha
							tp_linha = aux_linha[5].equals("fina")?TipoLinha.fina:aux_linha[5].equals("grossa")?TipoLinha.grossa:TipoLinha.pontilhada;
							
							Elipse eE = new Elipse();
							eE.setCentro(p1E);
							eE.setBorda(p2E);
							eE.setAnguloInicial(anguloIE);
							eE.setAnguloFinal(anguloFE);
							eE.setRoatcao(rotE);
							eE.setCorLinha(cor);
							eE.setTipoLinha(tp_linha);
							ctrElipse.elipses_desenhadas.add(eE);
							break;
					
						case "AE":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1AE =	 new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2AE = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Angulos
							aux_ponto = aux_linha[2].split(",");
							int anguloIAE = Integer.parseInt(aux_ponto[0]);
							int anguloFAE = Integer.parseInt(aux_ponto[1]);
							// Raio e rotação 
							int rotAE = Integer.parseInt(aux_linha[3]);
							// Componentes da cor
							aux_ponto = aux_linha[4].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							tp_linha = aux_linha[5].equals("fina")?TipoLinha.fina:aux_linha[5].equals("grossa")?TipoLinha.grossa:TipoLinha.pontilhada;
							
							Elipse eAE = new Elipse();
							eAE.setCentro(p1AE);
							eAE.setBorda(p2AE);
							eAE.setAnguloInicial(anguloIAE);
							eAE.setAnguloFinal(anguloFAE);
							eAE.setRoatcao(rotAE);
							eAE.setCorLinha(cor);
							eAE.setTipoLinha(tp_linha);
							ctrElipse.arcos_desenhados.add(eAE);
							break;
							
						case "P":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1R =	 new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2R = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Rotação 
							int rotR = Integer.parseInt(aux_linha[2]);
							// Componentes da cor
							aux_ponto = aux_linha[3].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							tp_linha = aux_linha[4].equals("fina")?TipoLinha.fina:aux_linha[4].equals("grossa")?TipoLinha.grossa:TipoLinha.pontilhada;
							// Quantidade de arestas
							int qtdArestas = Integer.parseInt(aux_linha[5]);
							
							PoligonoRegular polR = new PoligonoRegular();
							polR.setCentro(p1R);
							polR.setBorda(p2R);
							polR.setRoatcao(rotR);
							polR.setCorLinha(cor);
							polR.setTipoLinha(tp_linha);
							polR.setQtdArestas(qtdArestas);
							ctrPoligono.poligonos_regulares_desenhados.add(polR);
							break;
					
						case "Ret":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1Ret =	 new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2Ret = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Rotação 
							int rotRet = Integer.parseInt(aux_linha[2]);
							// Componentes da cor
							aux_ponto = aux_linha[3].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							tp_linha = aux_linha[4].equals("fina")?TipoLinha.fina:aux_linha[4].equals("grossa")?TipoLinha.grossa:TipoLinha.pontilhada;
							
							Retangulo ret = new Retangulo();
							ret.setLado0(new Reta(p1Ret,null));
							ret.setLado1(new Reta(p2Ret,null));
							ret.setRoatcao(rotRet);
							ret.setCorLinha(cor);
							ret.setTipoLinha(tp_linha);
							ctrRetangulo.retangulos_desenhados.add(ret);
							break;
					
						case "Reta":
							// Ponto 1
							aux_ponto = aux_linha[0].split(",");
							Ponto p1Reta =	 new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Ponto 2
							aux_ponto = aux_linha[1].split(",");
							Ponto p2Reta = new Ponto(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]));
							// Rotação 
							int rotReta = Integer.parseInt(aux_linha[2]);
							// Componentes da cor
							aux_ponto = aux_linha[3].split(",");
							cor = new Color(Integer.parseInt(aux_ponto[0]),Integer.parseInt(aux_ponto[1]),Integer.parseInt(aux_ponto[2]));
							tp_linha = aux_linha[4].equals("fina")?TipoLinha.fina:aux_linha[4].equals("grossa")?TipoLinha.grossa:TipoLinha.pontilhada;
							
							Reta reta = new Reta();
							reta.setPtoInicial(p1Reta);
							reta.setPtoFinal(p2Reta);
							reta.setRoatcao(rotReta);
							reta.setCorLinha(cor);
							reta.setTipoLinha(tp_linha);
							ctrReta.retas_desenhadas.add(reta);
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
