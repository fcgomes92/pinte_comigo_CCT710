package pacote18762.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import pacote18762.control.ControleCirculo;
import pacote18762.control.ControleDraw;
import pacote18762.control.ControleElipse;
import pacote18762.control.ControleFigura;
import pacote18762.control.ControleLetra;
import pacote18762.control.ControlePoligonoRegular;
import pacote18762.control.ControlePonto;
import pacote18762.control.ControleReta;
import pacote18762.control.ControleRetangulo;
import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.TamanhoLetra;
import pacote18762.model.TipoLinha;

public class App {
	// botões de opção
	private JButton btExit, btLoad, btSalvar, btNovo, btInstrucoes;
	// botões de ferramentas de desenho
	private JButton bt_draw_reta_tool, bt_draw_circulo_tool, bt_draw_poligono_tool, bt_draw_elipse_tool, bt_escolher_cor_linha_tool; 
	private JButton bt_escolher_tipo_linha_tool, bt_draw_ret_tool, bt_draw_arc_circulo_tool, bt_draw_arc_elipse_tool;
	
	// botão da ferramenta de texto
	private JButton bt_txt, bt_escolher_tamanho_letra; 
	
	// botões de ferramentas de area de trabalho
	private JButton bt_escolher_cor_area_de_trabalho_tool, bt_show_pontos_ref, bt_fill, bt_selecionar_figura, bt_mover_figura, bt_escala_figura;
	
	// main window
	private JFrame mainFrame;
	private JPanel menuPanel, drawToolsPanel, workspaceToolsPanel, mainPanel;
	private Draw drawPanel;
	
	// controles
	private ControleReta ctrReta;
	private ControlePonto ctrPonto;
	private ControleCirculo ctrCirculo;
	private ControlePoligonoRegular ctrPoligono;
	private ControleElipse ctrElipse;
	private ControleRetangulo ctrRetangulo;
	private ControleLetra ctrLetra;
	private ControleDraw ctrDraw;
	private ControleFigura ctrFigura;
	
	// controle de listeners
	private MouseListener ml_reta, ml_circulo, ml_poligono, ml_elipse, ml_ret, ml_arc_circulo, ml_arc_elipse, ml_text, ml_fill, ml_mover_figura, ml_escala_figura;
	
	// key listeners para entrada de texto
	private KeyListener kl_entrada_txt;
	
	// variaveis de auxilio no desenho
	private Ponto p1, p2, pInicial, pMedio, inicio_texto;
	private LinkedList<Ponto> pontosPoligono, pontos_ref;
	private int arestasPoligono, anguloInicial, anguloFinal, incremento_texto;
	private Color corEscolhida = new Color(0,0,0);
	private Dimension btDim = new Dimension(200,25);
	private Dimension btDim2 = new Dimension(150,25);
	private TipoLinha tipoLinhaDesenho = TipoLinha.fina;
	private TamanhoLetra tamanhoLetra = TamanhoLetra.tamanho8x8;
	private Color cor_area_de_trabalho, cor_pontos_ref;
	private boolean show_pontos_ref = false;
	
	public App() throws FileNotFoundException, IOException{		
		// inicialização botões
		btExit = new JButton("Sair");
		btExit.setPreferredSize(btDim2);
		btExit.setMaximumSize(btDim2);
		
		btLoad = new JButton("Carregar");
		btLoad.setPreferredSize(btDim2);
		btLoad.setMaximumSize(btDim2);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setPreferredSize(btDim2);
		btSalvar.setMaximumSize(btDim2);
		
		btNovo = new JButton("Novo");
		btNovo.setPreferredSize(btDim2);
		btNovo.setMaximumSize(btDim2);
		
		btInstrucoes = new JButton("Intruções");
		btInstrucoes.setPreferredSize(btDim2);
		btInstrucoes.setMaximumSize(btDim2);
		
		// inicialização dos botões
		bt_draw_reta_tool = new JButton("Reta");
		bt_draw_reta_tool.setPreferredSize(btDim);
		bt_draw_reta_tool.setMaximumSize(btDim);
		
		bt_draw_circulo_tool = new JButton("Circulo");
		bt_draw_circulo_tool.setPreferredSize(btDim);
		bt_draw_circulo_tool.setMaximumSize(btDim);
		
		bt_draw_poligono_tool = new JButton("Poligono");
		bt_draw_poligono_tool.setPreferredSize(btDim);
		bt_draw_poligono_tool.setMaximumSize(btDim);
		
		bt_draw_elipse_tool = new JButton("Elipse");
		bt_draw_elipse_tool.setPreferredSize(btDim);
		bt_draw_elipse_tool.setMaximumSize(btDim);
		
		bt_draw_ret_tool = new JButton("Retangulo");
		bt_draw_ret_tool.setPreferredSize(btDim);
		bt_draw_ret_tool.setMaximumSize(btDim);
		
		bt_draw_arc_circulo_tool = new JButton("Arco de Círculo");
		bt_draw_arc_circulo_tool.setPreferredSize(btDim);
		bt_draw_arc_circulo_tool.setMaximumSize(btDim);
		
		bt_draw_arc_elipse_tool = new JButton("Arco de Elipse");
		bt_draw_arc_elipse_tool.setPreferredSize(btDim);
		bt_draw_arc_elipse_tool.setMaximumSize(btDim);
		
		bt_escolher_cor_linha_tool = new JButton("Escolher Cor");
		bt_escolher_cor_linha_tool.setPreferredSize(btDim);
		bt_escolher_cor_linha_tool.setMaximumSize(btDim);
		
		bt_escolher_tipo_linha_tool = new JButton("Esco"
				+ "lher Linha");
		bt_escolher_tipo_linha_tool.setPreferredSize(btDim);
		bt_escolher_tipo_linha_tool.setMaximumSize(btDim);
		
		bt_escolher_cor_area_de_trabalho_tool = new JButton("Cor A.T.");
		bt_escolher_cor_area_de_trabalho_tool.setPreferredSize(btDim);
		bt_escolher_cor_area_de_trabalho_tool.setMaximumSize(btDim);
		
		bt_txt = new JButton("Escrever Texto");
		bt_txt.setPreferredSize(btDim);
		bt_txt.setMaximumSize(btDim);
		
		bt_escolher_tamanho_letra = new JButton("Escolher Tam. Letra");
		bt_escolher_tamanho_letra.setPreferredSize(btDim);
		bt_escolher_tamanho_letra.setMaximumSize(btDim);
		
		bt_show_pontos_ref = new JButton("Mostrar Pontos Ref.");
		bt_show_pontos_ref.setPreferredSize(btDim);
		bt_show_pontos_ref.setMaximumSize(btDim);
		
		bt_fill = new JButton("Preencher");
		bt_fill.setPreferredSize(btDim);
		bt_fill.setMaximumSize(btDim);
		
		bt_selecionar_figura = new JButton("Mover Figura Única");
		bt_selecionar_figura.setPreferredSize(btDim);
		bt_selecionar_figura.setMaximumSize(btDim);
		
		bt_mover_figura = new JButton("F11");
		bt_mover_figura.setPreferredSize(btDim);
		bt_mover_figura.setMaximumSize(btDim);
		
		bt_escala_figura = new JButton("Alterar Escala Figura");
		bt_escala_figura.setPreferredSize(btDim);
		bt_escala_figura.setMaximumSize(btDim);
		
		// inicialização frame principal
		mainFrame = new JFrame("Pinte Comigo!");
		
		// inicialização paineis
		drawPanel = new Draw();
		menuPanel = new JPanel();
		drawToolsPanel = new JPanel();
		workspaceToolsPanel = new JPanel();
		mainPanel = new JPanel();
		
		// inicialização controles
		ctrReta = new ControleReta();
		ctrCirculo = new ControleCirculo();
		ctrElipse = new ControleElipse();
		ctrPonto = new ControlePonto();
		ctrPoligono = new ControlePoligonoRegular();
		ctrRetangulo = new ControleRetangulo();
		ctrLetra = new ControleLetra();
		ctrDraw = new ControleDraw();
		ctrFigura = new ControleFigura(ctrCirculo,ctrElipse,ctrPoligono,ctrReta,ctrRetangulo);
		
		// inicialização das variaveis de auxilio
		p1 = new Ponto(-1,-1);
		p2 = new Ponto(-1,-1);
		inicio_texto = new Ponto(0,0);
		pontosPoligono = new LinkedList<Ponto>();
		arestasPoligono = 1;
		anguloInicial = -1;
		anguloFinal = -1;
		incremento_texto = 0;
		cor_area_de_trabalho = new Color(255,255,255);
		
		//pontos de referência por clique
		cor_pontos_ref = new Color(255,0,0);
		pontos_ref = new LinkedList<Ponto>();
		
		// inicializa letras do alfabeto
		ctrLetra.lerArquivoLetras("mapeamento-letras.DATA");
		
		// inicialização listener de entrada de texto
		kl_entrada_txt = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Código letra: "+e.getKeyChar());
				if(e.getKeyCode() == 13) inicio_texto.setY(inicio_texto.getY()+((tamanhoLetra == TamanhoLetra.tamanho8x8)?8:(tamanhoLetra == TamanhoLetra.tamanho16x16)?16:32));
				else if(e.getKeyChar()!=32)ctrLetra.drawLetra(drawPanel, corEscolhida, new Ponto(inicio_texto.getX()+incremento_texto,inicio_texto.getY()), e.getKeyChar(), tipoLinhaDesenho, tamanhoLetra);
				incremento_texto +=(tamanhoLetra == TamanhoLetra.tamanho8x8)?8:(tamanhoLetra == TamanhoLetra.tamanho16x16)?16:32;
			}	
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
		
		// inicialização dos listeners de desenho
		ml_reta = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				ctrReta.drawReta(p1,p2,drawPanel,corEscolhida, tipoLinhaDesenho,false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// pega o primeiro ponto
				p1 = new Ponto(e.getX(),e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		ml_circulo = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
				ctrCirculo.drawCirculoDDA(p1, p2, drawPanel, corEscolhida, tipoLinhaDesenho,false);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// pega o primeiro ponto
				p1 = new Ponto(e.getX(),e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		ml_poligono = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
				do {
					arestasPoligono = Integer.parseInt(JOptionPane.showInputDialog("Entre com a quantidade de lados do polígono:"));
				}while(arestasPoligono < 0 && arestasPoligono > 360);
				ctrPoligono.draw_poligono_regular(drawPanel, corEscolhida, tipoLinhaDesenho, p1, p2, arestasPoligono, false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// pega o primeiro ponto
				p1 = new Ponto(e.getX(),e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Poligonos não regulares
//				if(arestasPoligono<0){
//					pontosPoligono = new LinkedList<Ponto>();
//					pInicial = new Ponto(e.getX(),e.getY());
//					pontosPoligono.add(pInicial);
//					if(show_pontos_ref)ctrCirculo.drawCirculoDDA(pInicial, new Ponto(pInicial.getX()+3, pInicial.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina);
//					arestasPoligono++;
//				}
//				else{
//					if(e.getButton() == MouseEvent.BUTTON1){
//						pMedio = new Ponto(e.getX(),e.getY());
//						pontosPoligono.add(pMedio);
//						if(show_pontos_ref)ctrCirculo.drawCirculoDDA(pMedio, new Ponto(pMedio.getX()+3, pMedio.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina);
//						arestasPoligono++;
//					}
//					if(ctrPonto.isEqual(pMedio, pInicial) || e.getButton() == MouseEvent.BUTTON3){
//						pontosPoligono.add(pInicial);
//						pontos_ref.addAll(pontosPoligono);
//						ctrPoligono.drawPoligono(pontosPoligono, arestasPoligono, drawPanel, corEscolhida, tipoLinhaDesenho);
//						arestasPoligono = -1;
//					}
//				}
				
			}
		};
		ml_elipse = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(), e.getY()); 
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
				ctrElipse.drawElipse(drawPanel, corEscolhida, p1, p2, tipoLinhaDesenho,false);				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub				
			}
		};
		ml_ret = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
				ctrRetangulo.drawRetangulo(drawPanel, corEscolhida, p1, p2, tipoLinhaDesenho, false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// pega o primeiro ponto
				p1 = new Ponto(e.getX(),e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		ml_arc_circulo = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(), e.getY());
				
				// captura do angulo final e inicial
				anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo inicial (0<aI<360):"));
				anguloFinal = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo final (0<aF<360):"));
				
				// verificação dos valores para evitar erro
				while(anguloInicial < 0 || anguloInicial > 360) anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo inicial (0<aI<360):"));
				while(anguloFinal < 0 || anguloFinal > 360) anguloFinal = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo final (0<aF<360):"));
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
				ctrCirculo.drawCirculoArch(p1, p2, Math.toRadians(anguloInicial), Math.toRadians(anguloFinal), drawPanel, corEscolhida, tipoLinhaDesenho, false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, new Color(255,0,0), TipoLinha.fina,true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		};
		ml_arc_elipse = new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(), e.getY());
				
				// captura do angulo final e inicial
				anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo inicial (0<aI<360):"));
				anguloFinal = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo final (0<aF<360):"));
				
				// verificação dos valores para evitar erro
				while(anguloInicial < 0 || anguloInicial > 360) anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo inicial (0<aI<360):"));
				while(anguloFinal < 0 || anguloFinal > 360) anguloFinal = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo final (0<aF<360):"));
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
				ctrElipse.drawElipseArc(drawPanel, corEscolhida, p1, p2, Math.toRadians(anguloInicial), Math.toRadians(anguloFinal), tipoLinhaDesenho, false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		};
		
		// listener de texto
		ml_text = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				incremento_texto=0;
				inicio_texto = new Ponto(arg0.getX(),arg0.getY());
			}
		};
		
		// listener do balde de tinta
		ml_fill = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				Robot rb = null;
				BufferedImage img;
					try {
						rb = new Robot();
						img = rb.createScreenCapture(new Rectangle(drawPanel.getLocationOnScreen(),new Dimension(drawPanel.getWidth(),drawPanel.getHeight())));
						Color corPonto = new Color(img.getRGB(p1.getX(), p1.getY()));
//						drawToolsPanel.setBackground(corPonto);
						ctrDraw.img = img;
						ctrDraw.fill(drawPanel, p1, new Color(255,0,0), corPonto);
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		};
		
		// listener da seleção de figura
		ml_mover_figura = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(),e.getY());
				
				ctrFigura.mover_figura_selecionada(drawPanel, p2, cor_area_de_trabalho);				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				ctrFigura.get_figura_proxima(p1,drawPanel);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		};
		
		
		//listenerr da escala da figura
		ml_escala_figura = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {	
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				p1 = new Ponto(arg0.getX(),arg0.getY());
				ctrFigura.get_figura_proxima(p1, drawPanel);
				ctrFigura.alterar_escala_figura(drawPanel, 2, cor_area_de_trabalho);
			}
		};
		
		// listeners dos botões
		// ferramenta desenho retas
		bt_draw_reta_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// remove outros listeners e adciona o referente
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_reta);
			}
		});
		
		// ferramente desenho circulo
		bt_draw_circulo_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_circulo);
			}
		});
		
		// desenho de poligonos
		bt_draw_poligono_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_poligono);
			}
		});
		
		// ferramenta de desenho de elipses
		bt_draw_elipse_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_elipse);
			}
		});
		
		// ferramenta desenho de retangulos
		bt_draw_ret_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_ret);
			}
		});
		
		// ferramenta desenho de arco de circulo
		bt_draw_arc_circulo_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_arc_circulo);
			}
		});
		
		// ferramenta desenho de arco de elipse
		bt_draw_arc_elipse_tool.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						remove_all_listeners(drawPanel);
						drawPanel.addMouseListener(ml_arc_elipse);
					}
				});
		
		// escolher cor da linha
		bt_escolher_cor_linha_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				corEscolhida = JColorChooser.showDialog(null, "Escolha a cor:", corEscolhida);
				if (corEscolhida==null) corEscolhida = new Color(0,0,0);
			}
		});
		
		// escolher tipo de linha
		bt_escolher_tipo_linha_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> list = new JComboBox<String>(new String[] {"Fina", "Grossa", "Pontilhada"});
				JOptionPane.showMessageDialog(null, list, "Escolha o tipo da Linha:", JOptionPane.PLAIN_MESSAGE);
				if((list.getSelectedItem()).equals("Fina")) tipoLinhaDesenho = TipoLinha.fina;
				else if((list.getSelectedItem()).equals("Grossa")) tipoLinhaDesenho = TipoLinha.grossa;
				else if((list.getSelectedItem()).equals("Pontilhada")) tipoLinhaDesenho = TipoLinha.pontilhada;
				//System.out.println("Tipo de linha: "+tipoLinhaDesenho.toString());
			}
		});
		
		// iniciar função de texto
		bt_txt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				incremento_texto=0;
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_text);
				drawPanel.addKeyListener(kl_entrada_txt);
				drawPanel.requestFocus();
			}
		});
		
		// escolher tamanho texto
		bt_escolher_tamanho_letra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> list = new JComboBox<String>(new String[] {"Tamanho 1", "Tamanho 2", "Tamanho 3"});
				JOptionPane.showMessageDialog(null, list, "Escolha o tamanho letra:", JOptionPane.PLAIN_MESSAGE);
				if((list.getSelectedItem()).equals("Tamanho 1")) tamanhoLetra = TamanhoLetra.tamanho8x8; 
				else if((list.getSelectedItem()).equals("Tamanho 2")) tamanhoLetra = TamanhoLetra.tamanho16x16;
				else if((list.getSelectedItem()).equals("Tamanho 3")) tamanhoLetra = TamanhoLetra.tamanho32x32;
				//System.out.println("Tipo de linha: "+tipoLinhaDesenho.toString());
			}
		});
		
		// botão para alterar tamanho figura
		bt_escala_figura.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_escala_figura);
			}
		});
		
		// trocar cor área de trabalho
		bt_escolher_cor_area_de_trabalho_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cor_area_de_trabalho = new Color(255,255,255); 
				cor_area_de_trabalho= JColorChooser.showDialog(null, "Escolha a cor da A.T.:", cor_area_de_trabalho);
				if (cor_area_de_trabalho==null) cor_area_de_trabalho = new Color(255,255,255);
				drawPanel.setBackground(cor_area_de_trabalho);
				drawPanel.setForeground(cor_area_de_trabalho);
			}
		});
		
		// botão para preencher a opartir do ponto
		bt_fill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.addMouseListener(ml_fill);
			}
		});
		
		// botão para mostrar, ou não, os pontos de referẽncia
		bt_show_pontos_ref.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(show_pontos_ref) 
					for (Ponto p : pontos_ref) 
						ctrCirculo.drawCirculoDDA(p, new Ponto(p.getX()+3, p.getY()+3), drawPanel, cor_area_de_trabalho, TipoLinha.fina,true);
				
				else 
					for (Ponto p : pontos_ref) 
						ctrCirculo.drawCirculoDDA(p, new Ponto(p.getX()+3, p.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true); 
				
				show_pontos_ref = !show_pontos_ref;
			}
		});
		
		// botão para selecionar uma figura
		bt_selecionar_figura.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_mover_figura);
			}
		});
		
		// botão de novo desenho
		btNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    Object[] options = { "Sim, desejo apagar tudo!","Espere! Esqueci uma coisa..." };  
			    int result = JOptionPane.showOptionDialog(null,  
			                    "Deseja apagar tudo?",  
			                    "Novo desenho?", JOptionPane.YES_NO_OPTION,  
			                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			    if (result==0){
			    	drawPanel.update(drawPanel.getGraphics());
			    }
			    
			}
		});
		
		// botão de sair
		btExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// gerando painel de ferramentas de desenho
		drawToolsPanel.setLayout(new BoxLayout(drawToolsPanel,BoxLayout.Y_AXIS));
		
		drawToolsPanel.add(bt_draw_reta_tool);
		drawToolsPanel.add(bt_draw_circulo_tool);
		drawToolsPanel.add(bt_draw_arc_circulo_tool);
		drawToolsPanel.add(bt_draw_ret_tool);
		drawToolsPanel.add(bt_draw_poligono_tool);
		drawToolsPanel.add(bt_draw_elipse_tool);
		drawToolsPanel.add(bt_draw_arc_elipse_tool);
		drawToolsPanel.add(bt_txt);
		drawToolsPanel.add(bt_escolher_tamanho_letra);
		drawToolsPanel.add(bt_escolher_cor_linha_tool);
		drawToolsPanel.add(bt_escolher_tipo_linha_tool);
		
		drawToolsPanel.setBackground(new Color(0,0,0));
		
		// gerando painel de ferramentas de área de trabalho
		workspaceToolsPanel.setLayout(new BoxLayout(workspaceToolsPanel,BoxLayout.Y_AXIS));
		
		workspaceToolsPanel.add(bt_escolher_cor_area_de_trabalho_tool);
		workspaceToolsPanel.add(bt_show_pontos_ref);
		workspaceToolsPanel.add(bt_fill);
		workspaceToolsPanel.add(bt_selecionar_figura);
		workspaceToolsPanel.add(bt_mover_figura);
		workspaceToolsPanel.add(bt_escala_figura);
		
		workspaceToolsPanel.setBackground(new Color(0,0,0));
		
		// gerando painel de menu
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		menuPanel.add(btInstrucoes);
		menuPanel.add(btNovo);
		menuPanel.add(btSalvar);
		menuPanel.add(btLoad);
		menuPanel.add(btExit);
		menuPanel.setBackground(new Color(0,0,0));
		
		//	gerando painel de desenho
		drawPanel.setOpaque(true);
		drawPanel.setBackground(cor_area_de_trabalho);
		drawPanel.setForeground(cor_area_de_trabalho);
		
		SpringLayout sLayout = new SpringLayout();
		mainPanel.setLayout(sLayout);
		
		// set posição painel ferramentas de área de trabal
		sLayout.putConstraint(SpringLayout.SOUTH, workspaceToolsPanel, 0, SpringLayout.SOUTH, mainPanel);
		sLayout.putConstraint(SpringLayout.NORTH, workspaceToolsPanel, 0, SpringLayout.SOUTH, menuPanel);
		sLayout.putConstraint(SpringLayout.EAST, workspaceToolsPanel, 0, SpringLayout.EAST, mainPanel);
		
		// set posicao painel ferramentas de desenho
		sLayout.putConstraint(SpringLayout.SOUTH, drawToolsPanel, 0, SpringLayout.SOUTH, mainPanel);
		sLayout.putConstraint(SpringLayout.NORTH, drawToolsPanel, 0, SpringLayout.SOUTH, menuPanel);
		sLayout.putConstraint(SpringLayout.WEST, drawToolsPanel, 0, SpringLayout.WEST, mainPanel);	
		
		// set posição painel de desenho principal
		sLayout.putConstraint(SpringLayout.SOUTH, drawPanel, 0, SpringLayout.SOUTH, mainPanel);
		sLayout.putConstraint(SpringLayout.NORTH, drawPanel, 0, SpringLayout.SOUTH, menuPanel);
		sLayout.putConstraint(SpringLayout.WEST, drawPanel, 0, SpringLayout.EAST, drawToolsPanel);	
		sLayout.putConstraint(SpringLayout.EAST, drawPanel, 0, SpringLayout.WEST, workspaceToolsPanel);
		
		// set posição painel menu
		sLayout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.NORTH, mainPanel);
		sLayout.putConstraint(SpringLayout.WEST, menuPanel, 0, SpringLayout.WEST, mainPanel);
		sLayout.putConstraint(SpringLayout.EAST, menuPanel, 0, SpringLayout.EAST, mainPanel);
		
		mainPanel.add(drawToolsPanel);
		mainPanel.add(workspaceToolsPanel);
		mainPanel.add(drawPanel);
		mainPanel.add(menuPanel);
		
		mainFrame.setUndecorated(true);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(500,500));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setVisible(true);
	}
	
	public void remove_all_listeners(Draw panel){
		drawPanel.removeMouseListener(ml_reta);
		drawPanel.removeMouseListener(ml_circulo);
		drawPanel.removeMouseListener(ml_poligono);
		drawPanel.removeMouseListener(ml_elipse);
		drawPanel.removeMouseListener(ml_ret);
		drawPanel.removeMouseListener(ml_arc_elipse);
		drawPanel.removeMouseListener(ml_arc_circulo);
		drawPanel.removeMouseListener(ml_text);
		drawPanel.removeKeyListener(kl_entrada_txt);
		drawPanel.removeMouseListener(ml_mover_figura);
		drawPanel.removeMouseListener(ml_escala_figura);
	}
}