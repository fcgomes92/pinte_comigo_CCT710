package pacote18762.control;

import java.io.IOException;

import pacote18762.view.App;

public class ControleApp {
	public static void main(String[] args) {
		try {
			new App();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
