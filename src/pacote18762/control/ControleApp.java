package pacote18762.control;

import java.io.IOException;
import pacote18762.view.App;

/**
 * Classe de controle do aplicativo
 * @author gomes
 */
public class ControleApp {
	
	/**
	 * Método de chamada da interface e inicialização do sistema.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new App();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
