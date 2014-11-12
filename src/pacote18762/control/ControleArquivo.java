package pacote18762.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import pacote18762.model.Circulo;
import pacote18762.model.Elipse;
import pacote18762.model.PoligonoRegular;
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
	
	public void load_file(){
		
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
}
