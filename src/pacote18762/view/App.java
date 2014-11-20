package pacote18762.view;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import pacote18762.control.ControleApp;
import pacote18762.control.ControleArquivo;
import pacote18762.control.ControleCirculo;
import pacote18762.control.ControleElipse;
import pacote18762.control.ControleFigura;
import pacote18762.control.ControleGrade;
import pacote18762.control.ControleLetra;
import pacote18762.control.ControlePoligonoRegular;
import pacote18762.control.ControlePonto;
import pacote18762.control.ControleReta;
import pacote18762.control.ControleRetangulo;
import pacote18762.model.Draw;
import pacote18762.model.Ponto;
import pacote18762.model.TamanhoLetra;
import pacote18762.model.TipoLinha;

/**
 *  Classe principal para gerar a interface 
 *  do programa.
 * 
 *  @author gomes
 *  @version 1.5.1-16
 */
public class App {
	// botões de opção
	private JMenuBar menu_bar; // barra de menu principal
	private JMenu menu; // primeiro menu da barra
	private JMenuItem mi_exit, mi_load, mi_salvar, mi_novo, mi_instrucoes, mi_export, mi_export_selecao;
	private JMenuItem mi_rotacionar, mi_mover, mi_escala;
	private JPopupMenu popup;
	
	// botões de ferramentas de desenho
	private JButton bt_draw_reta_tool, bt_draw_circulo_tool, bt_draw_poligono_tool, bt_draw_elipse_tool, bt_escolher_cor_linha_tool, bt_apagar_figura; 
	private JButton bt_escolher_tipo_linha_tool, bt_draw_ret_tool, bt_draw_arc_circulo_tool, bt_draw_arc_elipse_tool;
	private JButton bt_selecionar_area;
	
	// botão da ferramenta de texto
	private JButton bt_txt, bt_escolher_tamanho_letra; 
	
	// botões de ferramentas de area de trabalho
	private JButton bt_escolher_cor_area_de_trabalho_tool, bt_show_pontos_ref, bt_fill, bt_mover_figura_unica, bt_rotacionar_figura_unica, bt_escala_figura;
	private JButton bt_scale_all, bt_move_all, bt_rotate_all, bt_show_grid;
	
	// main window
	private JFrame mainFrame;
	private JPanel drawToolsPanel, drawToolsPanel1, drawToolsPanel2, drawToolsPanel3, workspaceToolsPanel, mainPanel, tools_panel, pane;
	private Draw drawPanel;
	private JComboBox<String> cb;
	
	// controles
	private ControleReta ctrReta;
	private ControlePonto ctrPonto;
	private ControleCirculo ctrCirculo;
	private ControlePoligonoRegular ctrPoligono;
	private ControleElipse ctrElipse;
	private ControleRetangulo ctrRetangulo;
	private ControleLetra ctrLetra;
//	private ControleDraw ctrDraw;
	private ControleFigura ctrFigura;
	private ControleGrade ctrGrade;
	private ControleArquivo ctrArquivo;
	
	// controle de listeners
	private MouseAdapter ml_reta, ml_circulo, ml_poligono, ml_elipse, ml_ret, ml_arc_circulo, ml_arc_elipse;
	private MouseAdapter ml_text, ml_fill, ml_mover_figura, ml_escala_figura, ml_rotacionar_figura, ml_selecionar_area;
	private MouseAdapter ml_scale_all, ml_move_all, ml_rotate_all, ml_apagar_figura, ml_rotaciona_selecao, ml_move_selecao, ml_scale_selecao;
	
	// key listeners para entrada de texto
	private KeyListener kl_entrada_txt;
	
	// variaveis de auxilio no desenho
	private Ponto p1, p2, inicio_texto;
	private LinkedList<Ponto> pontos_ref;
//	private LinkedList<Ponto> pontosPoligono;
	private int arestasPoligono, anguloInicial, anguloFinal, incremento_texto;
	private Color corEscolhida = new Color(0,0,0);
	private Dimension btDim = new Dimension(250,25);
	private TipoLinha tipoLinhaDesenho = TipoLinha.fina;
	private TamanhoLetra tamanhoLetra = TamanhoLetra.tamanho8x8;
	private Color cor_area_de_trabalho, cor_pontos_ref;
	private boolean show_pontos_ref = false;
	
	
	/**
	 *  Construtor da classe principal. Gera a interface.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public App() throws FileNotFoundException, IOException{		
		// inicialização dos menus		
		mi_exit = new JMenuItem("Sair");
		mi_exit.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_Q, ActionEvent.CTRL_MASK)); // set CTRL_Q = Sair
		
		mi_load = new JMenuItem("Load");
		mi_load.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_L, ActionEvent.CTRL_MASK)); // set CTRL_L = Load
		
		mi_salvar = new JMenuItem("Salvar");
		mi_salvar.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // set CTRL_S = Save
		
		mi_export = new JMenuItem("Exportar");
		mi_export.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // set CTRL_E = Export
		
		mi_novo = new JMenuItem("Novo");
		mi_novo.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // set CTRL_N = Novo
		
		mi_instrucoes = new JMenuItem("Instruções");
		mi_instrucoes.setPreferredSize(btDim);
		mi_instrucoes.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_I, ActionEvent.CTRL_MASK)); // set CTRL_I = Intruções
		
		menu = new JMenu("File");
		menu.add(mi_novo);
		menu.add(mi_salvar);
		menu.add(mi_export);
		menu.add(mi_load);
		menu.add(mi_exit);
		
		menu_bar = new JMenuBar();
		menu_bar.add(menu);
		menu_bar.add(mi_instrucoes);

		// inicialização dos menus popup
		mi_escala = new JMenuItem("Alterar Escala");
		mi_escala.setPreferredSize(btDim);
		mi_mover = new JMenuItem("Mover");
		mi_mover.setPreferredSize(btDim);
		mi_rotacionar = new JMenuItem("Rotacionar");
		mi_rotacionar.setPreferredSize(btDim);
		mi_export_selecao = new JMenuItem("Exportar");
		mi_export_selecao.setPreferredSize(btDim);
		
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
		
		bt_escolher_tipo_linha_tool = new JButton("Escolher Linha");
		bt_escolher_tipo_linha_tool.setPreferredSize(btDim);
		bt_escolher_tipo_linha_tool.setMaximumSize(btDim);
		
		bt_apagar_figura = new JButton("Borracha");
		bt_apagar_figura.setPreferredSize(btDim);
		bt_apagar_figura.setMaximumSize(btDim);
		
		bt_escolher_cor_area_de_trabalho_tool = new JButton("Cor de fundo");
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
		
		bt_mover_figura_unica = new JButton("Mover Figura Única");
		bt_mover_figura_unica.setPreferredSize(btDim);
		bt_mover_figura_unica.setMaximumSize(btDim);
		
		bt_rotacionar_figura_unica = new JButton("Rotacionar Figura Única");
		bt_rotacionar_figura_unica.setPreferredSize(btDim);
		bt_rotacionar_figura_unica.setMaximumSize(btDim);
		
		bt_escala_figura = new JButton("Alterar Escala Figura");
		bt_escala_figura.setPreferredSize(btDim);
		bt_escala_figura.setMaximumSize(btDim);
		
		bt_scale_all = new JButton("Alterar Escala Geral");
		bt_scale_all.setPreferredSize(btDim);
		bt_scale_all.setMaximumSize(btDim);
		
		bt_move_all = new JButton("Mover todas as figuras");
		bt_move_all.setPreferredSize(btDim);
		bt_move_all.setMaximumSize(btDim);
		
		bt_rotate_all = new JButton("Rotacionar todas as figuras");
		bt_rotate_all.setPreferredSize(btDim);
		bt_rotate_all.setMaximumSize(btDim);
		
		bt_selecionar_area = new JButton("Selecionar Área");
		bt_selecionar_area.setPreferredSize(btDim);
		bt_selecionar_area.setMaximumSize(btDim);
		
		bt_show_grid = new JButton("Show Grid");
		bt_show_grid.setPreferredSize(btDim);
		bt_show_grid.setMaximumSize(btDim);
		
		// inicialização frame principal
		mainFrame = new JFrame("Pinte Comigo!");
		
		// inicialização paineis
		popup = new JPopupMenu();
		drawPanel = new Draw();
		drawToolsPanel = new JPanel();
		drawToolsPanel1 = new JPanel();
		drawToolsPanel2 = new JPanel();
		drawToolsPanel3 = new JPanel();
		workspaceToolsPanel = new JPanel();
		mainPanel = new JPanel();
		tools_panel = new JPanel(new CardLayout());
		
		// inicialização controles
		ctrReta = new ControleReta();
		ctrCirculo = new ControleCirculo();
		ctrElipse = new ControleElipse();
		ctrPonto = new ControlePonto();
		ctrPoligono = new ControlePoligonoRegular();
		ctrRetangulo = new ControleRetangulo();
		ctrLetra = new ControleLetra();
//		ctrDraw = new ControleDraw();
		ctrGrade = new ControleGrade(drawPanel);
		ctrFigura = new ControleFigura(ctrCirculo,ctrElipse,ctrPoligono,ctrReta,ctrRetangulo,ctrLetra);
		ctrArquivo = new ControleArquivo(ctrCirculo,ctrElipse,ctrPoligono,ctrReta,ctrRetangulo,ctrLetra);
		
		// inicialização das variaveis de auxilio
		p1 = new Ponto(-1,-1);
		p2 = new Ponto(-1,-1);
		inicio_texto = new Ponto(0,0);
//		pontosPoligono = new LinkedList<Ponto>();
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
				//System.out.println("Código letra: "+e.getKeyChar());
				if(e.getKeyCode() == 13) inicio_texto.setY(inicio_texto.getY()+((tamanhoLetra == TamanhoLetra.tamanho8x8)?8:(tamanhoLetra == TamanhoLetra.tamanho16x16)?16:32));
				else if(e.getKeyChar()!=32)ctrLetra.drawLetra(drawPanel, corEscolhida, new Ponto(inicio_texto.getX()+incremento_texto,inicio_texto.getY()), e.getKeyChar(), tipoLinhaDesenho, tamanhoLetra, false);
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
		// listener sobre a reta
		ml_reta = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
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
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
			}
		};
		
		ml_circulo = new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
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
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
		};
		
		ml_poligono = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
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
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
		};
		
		ml_elipse = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(), e.getY()); 
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
				// add os pontos de click
				pontos_ref.add(p1);
				pontos_ref.add(p2);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
				ctrElipse.drawElipse(drawPanel, corEscolhida, p1, p2, tipoLinhaDesenho,false);				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
		};
		
		ml_ret = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// pega o ultimo ponto e chama o método de desenho
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
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
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
		};
		
		ml_arc_circulo = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(), e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
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
				ctrCirculo.drawCirculoArch(p1, p2, anguloInicial, anguloFinal, drawPanel, corEscolhida, tipoLinhaDesenho, false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, new Color(255,0,0), TipoLinha.fina,true);
			}
		};

		ml_arc_elipse = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(), e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
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
				ctrElipse.drawElipseArc(drawPanel, corEscolhida, p1, p2, anguloInicial, anguloFinal, tipoLinhaDesenho, false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(), e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina,true);
			}
		};
		
		ml_apagar_figura = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				p1 = new Ponto(arg0.getX(), arg0.getY());
				ctrFigura.get_figura_proxima(p1, drawPanel);
				ctrFigura.apaga_figura_selecionada(drawPanel, cor_area_de_trabalho);
				ctrFigura.set_figura_selecionada_empty();
			}
		};
		
		// listener de texto
		ml_text = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				incremento_texto=0;
				inicio_texto = new Ponto(arg0.getX(),arg0.getY());
				if(ctrGrade.isVisible()) inicio_texto = ctrGrade.get_ponto_prox(inicio_texto);
			}
		};
		
		// listener do balde de tinta
		ml_fill = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				p1 = new Ponto(e.getX(), e.getY());
//				Robot rb = null;
//				BufferedImage img;
//					try {
//						rb = new Robot();
//						img = rb.createScreenCapture(new Rectangle(drawPanel.getLocationOnScreen(),new Dimension(drawPanel.getWidth(),drawPanel.getHeight())));
//						Color corPonto = new Color(img.getRGB(p1.getX(), p1.getY()));
////						drawToolsPanel.setBackground(corPonto);
//						ctrDraw.img = img;
//						ctrDraw.fill(drawPanel, p1, new Color(255,0,0), corPonto);
//					} catch (AWTException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
			}
		};
		
		// listener da seleção de figura
		ml_mover_figura = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
				ctrFigura.mover_figura_selecionada(drawPanel, p2, cor_area_de_trabalho);
				
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				ctrFigura.get_figura_proxima(p1,drawPanel);
			}
		};
		
		// listener para mover seleção
		ml_move_selecao = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
				ctrFigura.move_figura_dentro_selecao(p1, p2, drawPanel, cor_area_de_trabalho);
				
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
			}
		};
		
		// listener da seleção de figura
		ml_move_all = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
				
				ctrFigura.move_all(drawPanel, p1, p2, cor_area_de_trabalho);
				
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
			}
		};
		
		// listener da rotação de figura
		ml_rotate_all = new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				anguloInicial = -1;
				while(anguloInicial < 0 || anguloInicial > 360) anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo (0<a<360):"));
				ctrFigura.rotate_all(drawPanel, anguloInicial, cor_area_de_trabalho);
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
			}
		};
		
		//listener da rotação de figura
		ml_rotacionar_figura = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				p1 = new Ponto(arg0.getX(),arg0.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				ctrFigura.get_figura_proxima(p1, drawPanel);
				anguloInicial = -1;
				while(anguloInicial < 0 || anguloInicial > 360) anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo (0<a<360):"));
				ctrFigura.rotacionar_figura(drawPanel, anguloInicial, p1, cor_area_de_trabalho);
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
			}
		};
		
		// listener de rotação da seleção
		ml_rotaciona_selecao = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				p1 = new Ponto(arg0.getX(),arg0.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				anguloInicial = -1;
				while(anguloInicial < 0 || anguloInicial > 360) anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo (0<a<360):"));
				ctrFigura.rotaciona_figura_dentro_selecao(p1, drawPanel, cor_area_de_trabalho, anguloInicial);
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
			}
		};
		
		//listenerr da escala da figura
		ml_escala_figura = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(e.getButton() == MouseEvent.BUTTON1){
					ctrFigura.get_figura_proxima(p1, drawPanel);
					ctrFigura.alterar_escala_figura(drawPanel, 1.6, cor_area_de_trabalho);
				}
				else if(e.getButton() == MouseEvent.BUTTON3){
					ctrFigura.get_figura_proxima(p1, drawPanel);
					ctrFigura.alterar_escala_figura(drawPanel, 0.625, cor_area_de_trabalho);
				}
				
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
				
			}
		};
		
		// listener de rotação de seleção
		ml_scale_selecao = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(e.getButton() == MouseEvent.BUTTON1){
					ctrFigura.muda_escala_figura_dentro_seleção(1.6, cor_area_de_trabalho, drawPanel);
				}
				else if(e.getButton() == MouseEvent.BUTTON3){
					ctrFigura.muda_escala_figura_dentro_seleção(0.625, cor_area_de_trabalho, drawPanel);
				}
				
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
				
			}
		};
		
		//listenerr da escala da figura
		ml_scale_all = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p1 = new Ponto(e.getX(),e.getY());
				if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
				if(e.getButton() == MouseEvent.BUTTON1){
					ctrFigura.scale_all(drawPanel, 1.6, cor_area_de_trabalho);
				}
				else if(e.getButton() == MouseEvent.BUTTON3){
					ctrFigura.scale_all(drawPanel, 0.625, cor_area_de_trabalho);
				}
				
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
				
			}
		};
		
		// listener sobre a selecção de área
		ml_selecionar_area = new MouseAdapter() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// pega o ultimo ponto e chama o método de desenho
						p2 = new Ponto(e.getX(),e.getY());
						
						popup.show(pane,p2.getX(),p2.getY());
						
						if(ctrGrade.isVisible()) p2 = ctrGrade.get_ponto_prox(p2);
						if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p2, new Ponto(p2.getX()+3, p2.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
						
						// add os pontos de click
						pontos_ref.add(p1);
						pontos_ref.add(p2);
						
						ctrFigura.selecao_multipla(drawPanel, p1, p2, cor_area_de_trabalho);
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// pega o primeiro ponto
						p1 = new Ponto(e.getX(),e.getY());
						if(ctrGrade.isVisible()) p1 = ctrGrade.get_ponto_prox(p1);
						if(show_pontos_ref)ctrCirculo.drawCirculoDDA(p1, new Ponto(p1.getX()+3, p1.getY()+3), drawPanel, cor_pontos_ref, TipoLinha.fina, true);
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
		
		// apagar figura
		bt_apagar_figura.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_apagar_figura);
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
		
		bt_scale_all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_scale_all);
			}
		});
		
		// trocar cor área de trabalho
		bt_escolher_cor_area_de_trabalho_tool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cor_area_de_trabalho = new Color(255,255,255); 
				cor_area_de_trabalho= JColorChooser.showDialog(null, "Escolha a cor de fundo:", cor_area_de_trabalho);
				if (cor_area_de_trabalho==null) cor_area_de_trabalho = new Color(255,255,255);
				drawPanel.setBackground(cor_area_de_trabalho);
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
		
		// botão para rotacionar figura única
		bt_rotacionar_figura_unica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_rotacionar_figura);
			}
		});
		
		// botão para selecionar uma figura
		bt_mover_figura_unica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_mover_figura);
			}
		});
		
		// botão para selecionar uma figura
		bt_move_all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_move_all);
			}
		});
		
		// botão para selecionar uma figura
		bt_rotate_all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_rotate_all);
			}
		});
		
		// ferramenta de selecionar área
		bt_selecionar_area.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// remove outros listeners e adciona o referente
				remove_all_listeners(drawPanel);
				drawPanel.addMouseListener(ml_selecionar_area);
			}
		});
			
		// botão para selecionar uma figura
		bt_show_grid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, new Color(255-cor_area_de_trabalho.getRed(),255-cor_area_de_trabalho.getGreen(),255-cor_area_de_trabalho.getBlue()));
					ctrGrade.setVisibleGrade(false);
					ctrFigura.draw_all_again(drawPanel);
				}
				else {
					JComboBox<String> list = new JComboBox<String>(new String[] {"Tamanho 1", "Tamanho 2", "Tamanho 3"});
					JOptionPane.showMessageDialog(null, list, "Escolha o tamanho da grade:", JOptionPane.PLAIN_MESSAGE);
					switch(list.getSelectedItem().toString()){
						case "Tamanho 1":
							ctrGrade.set_gap_grid(20);
							break;
						case "Tamanho 2":
							ctrGrade.set_gap_grid(40);
							break;
						case "Tamanho 3":
							ctrGrade.set_gap_grid(80);
							break;
						default:
							ctrGrade.set_gap_grid(80);
							break;
								
					}
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrGrade.setVisibleGrade(true);
					ctrFigura.draw_all_again(drawPanel);
				}
			}
		});
		
		// botão de novo desenho
		mi_novo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//			    Object[] options = { "Sim, desejo apagar tudo!","Espere! Esqueci uma coisa..." };  
//			    int result = JOptionPane.showOptionDialog(null,  
//			                    "Deseja apagar tudo?",  
//			                    "Novo desenho?", JOptionPane.YES_NO_OPTION,  
//			                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//			    if (result==0){
//			    	drawPanel.update(drawPanel.getGraphics());
//			    }
				new ControleApp().main(null);
			    
			}
		});
		
		// botão de salvar
		mi_salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showSaveDialog(drawPanel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	if(ctrArquivo.save_file(chooser.getSelectedFile().getAbsolutePath())) System.out.println("Sucesso no save!");
					else System.out.println("Fail :(");
			    }
			}
		});
		
		// botão de exportar a tela toda
		mi_export.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Robot rb;
				BufferedImage shot;
				File fl;
				
				try {
					rb = new Robot();
					shot = rb.createScreenCapture(new Rectangle(drawPanel.getLocationOnScreen(),new Dimension(drawPanel.getWidth(),drawPanel.getHeight())));
					JFileChooser chooser = new JFileChooser();
				    int returnVal = chooser.showSaveDialog(drawPanel);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	fl = new File(chooser.getSelectedFile().getAbsolutePath());
				    	ImageIO.write(shot, "png", fl);
				    }
				} catch (AWTException | IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		// botão de salvar
		mi_load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setApproveButtonText("Load");
			    int returnVal = chooser.showOpenDialog(drawPanel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	if(ctrArquivo.load_file(chooser.getSelectedFile().getAbsolutePath())) {
			    		System.out.println("Sucesso no load!");
			    		ctrFigura.draw_all_again(drawPanel);
			    	}
					else System.out.println("Fail :(");
			    }
			}
		});
		
		// botão de instruções
		mi_instrucoes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Instrucoes();
			}
		});

		// botão de sair
		mi_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// botões do popup menu
		mi_export_selecao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Robot rb;
				BufferedImage shot;
				File fl;
				Point p;
				int height = 0, width = 0;
				
				try {
					rb = new Robot();
					int x = (int) (ctrFigura.selecao_multipla.getLado0().getPtoInicial().getX()+drawPanel.getLocationOnScreen().getX());
					int y = (int) (ctrFigura.selecao_multipla.getLado0().getPtoInicial().getY()+drawPanel.getLocationOnScreen().getY());
					p = new Point(x,y);
					height = (int)Math.round(ctrPonto.dist(ctrFigura.selecao_multipla.getLado0().getPtoInicial(), ctrFigura.selecao_multipla.getLado0().getPtoFinal()));
					width = (int)Math.round(ctrPonto.dist(ctrFigura.selecao_multipla.getLado3().getPtoInicial(), ctrFigura.selecao_multipla.getLado3().getPtoFinal()));
					
					ctrFigura.apagar_ret_selecao(drawPanel, cor_area_de_trabalho);
					
					shot = rb.createScreenCapture(new Rectangle(p, new Dimension(width,height)));
					JFileChooser chooser = new JFileChooser();
				    int returnVal = chooser.showSaveDialog(drawPanel);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	fl = new File(chooser.getSelectedFile().getAbsolutePath());
				    	ImageIO.write(shot, "png", fl);
				    }
				} catch (AWTException | IOException e) {
					e.printStackTrace();
				}			
			}
		});
		
		mi_escala.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				ctrFigura.apagar_ret_selecao(drawPanel, cor_area_de_trabalho);
				drawPanel.addMouseListener(ml_scale_selecao);
			}
		});
		
		mi_rotacionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				ctrFigura.apagar_ret_selecao(drawPanel, cor_area_de_trabalho);
				anguloInicial = -1;
				while(anguloInicial < 0 || anguloInicial > 360) anguloInicial = Integer.parseInt(JOptionPane.showInputDialog("Entre com o valor do angulo (0<a<360):"));
				ctrFigura.rotaciona_figura_dentro_selecao(p1, drawPanel, cor_area_de_trabalho, anguloInicial);
				if(ctrGrade.isVisible()){
					ctrGrade.draw_grid(drawPanel, cor_area_de_trabalho);
					ctrFigura.draw_all_again(drawPanel);
				}
			}
		});
		
		mi_mover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove_all_listeners(drawPanel);
				ctrFigura.apagar_ret_selecao(drawPanel, cor_area_de_trabalho);
				drawPanel.addMouseListener(ml_move_selecao);
			}
		});
		
		// configurando o menu de seleção multipla
		popup.add(mi_escala);
		popup.add(mi_rotacionar);
		popup.add(mi_mover);
		popup.add(mi_export_selecao);
		
		// gerando painel de ferramentas de desenho
		// primeiro parte das ferramentas
		drawToolsPanel1.setLayout(new BoxLayout(drawToolsPanel1,BoxLayout.Y_AXIS));
		TitledBorder draw1_title =  BorderFactory.createTitledBorder("Desenho");
		drawToolsPanel1.setBorder(draw1_title);
		drawToolsPanel1.add(bt_draw_reta_tool);
		drawToolsPanel1.add(bt_draw_circulo_tool);
		drawToolsPanel1.add(bt_draw_arc_circulo_tool);
		drawToolsPanel1.add(bt_draw_ret_tool);
		drawToolsPanel1.add(bt_draw_poligono_tool);
		drawToolsPanel1.add(bt_draw_elipse_tool);
		drawToolsPanel1.add(bt_draw_arc_elipse_tool);
		drawToolsPanel1.add(bt_apagar_figura);

		// segunda parte das ferramentas
		drawToolsPanel2.setLayout(new BoxLayout(drawToolsPanel2,BoxLayout.Y_AXIS));
		TitledBorder draw2_title =  BorderFactory.createTitledBorder("Texto");
		drawToolsPanel2.setBorder(draw2_title);
		drawToolsPanel2.add(bt_txt);
		drawToolsPanel2.add(bt_escolher_tamanho_letra);
		
		// terceira parte das ferramentas
		drawToolsPanel3.setLayout(new BoxLayout(drawToolsPanel3,BoxLayout.Y_AXIS));
		TitledBorder draw3_title =  BorderFactory.createTitledBorder("Opções");
		drawToolsPanel3.setBorder(draw3_title);
		drawToolsPanel3.add(bt_escolher_cor_linha_tool);
		drawToolsPanel3.add(bt_escolher_tipo_linha_tool);
		drawToolsPanel3.add(bt_mover_figura_unica);
		drawToolsPanel3.add(bt_move_all);
		drawToolsPanel3.add(bt_rotacionar_figura_unica);
		drawToolsPanel3.add(bt_rotate_all);
		drawToolsPanel3.add(bt_escala_figura);
		drawToolsPanel3.add(bt_scale_all);
		
		// panel principal das ferramentas
		drawToolsPanel.setLayout(new BoxLayout(drawToolsPanel,BoxLayout.Y_AXIS));
		drawToolsPanel.add(drawToolsPanel1);
		drawToolsPanel.add(drawToolsPanel2);
		drawToolsPanel.add(drawToolsPanel3);
		
		// gerando painel de ferramentas de área de trabalho
		workspaceToolsPanel.setLayout(new BoxLayout(workspaceToolsPanel,BoxLayout.Y_AXIS));
		
		TitledBorder workspace_title =  BorderFactory.createTitledBorder("Área de Trabalho");
		workspaceToolsPanel.setBorder(workspace_title);
		workspaceToolsPanel.add(bt_escolher_cor_area_de_trabalho_tool);
		workspaceToolsPanel.add(bt_show_pontos_ref);
		workspaceToolsPanel.add(bt_fill);
		workspaceToolsPanel.add(bt_selecionar_area);
		workspaceToolsPanel.add(bt_show_grid);
		
		//workspaceToolsPanel.setBackground(new Color(0,0,0));
		
		// configurando panel de ferramentas
		JPanel comboBoxPane = new JPanel(new BorderLayout());
		String comboBoxItems[] = { "Desenho", "Área de Trabalho"};
		cb = new JComboBox<String>(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				CardLayout cl = (CardLayout)(tools_panel.getLayout());
		        cl.show(tools_panel, (String)arg0.getItem());
			}
		});
		comboBoxPane.add(cb);
		
		tools_panel.add(drawToolsPanel,"Desenho");
		tools_panel.add(workspaceToolsPanel,"Área de Trabalho");
		
		pane = new JPanel(new BorderLayout());
		pane.add(comboBoxPane, BorderLayout.NORTH);
		pane.add(tools_panel, BorderLayout.CENTER);
		pane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
		//	gerando painel de desenho
		drawPanel.setOpaque(true);
		drawPanel.setBackground(cor_area_de_trabalho);
		
		SpringLayout sLayout = new SpringLayout();
		mainPanel.setLayout(sLayout);
		
		// set posicao painel ferramentas de desenho
		sLayout.putConstraint(SpringLayout.SOUTH, pane, 0, SpringLayout.SOUTH, mainPanel);
		sLayout.putConstraint(SpringLayout.NORTH, pane, 0, SpringLayout.NORTH, mainPanel);
		sLayout.putConstraint(SpringLayout.WEST, pane, 0, SpringLayout.WEST, mainPanel);	
		
		// set posição painel de desenho principal
		sLayout.putConstraint(SpringLayout.SOUTH, drawPanel, 0, SpringLayout.SOUTH, mainPanel);
		sLayout.putConstraint(SpringLayout.NORTH, drawPanel, 0, SpringLayout.NORTH, mainPanel);
		sLayout.putConstraint(SpringLayout.WEST, drawPanel, 0, SpringLayout.EAST, pane);	
		sLayout.putConstraint(SpringLayout.EAST, drawPanel, 0, SpringLayout.EAST, mainPanel);
		
		mainPanel.add(pane);
		mainPanel.add(drawPanel);
		
		mainFrame.setJMenuBar(menu_bar);
		mainFrame.setUndecorated(true);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(500,500));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setVisible(true);
	}
	
	/**
	 * Método para remover os listeners
	 * de um painel.
	 * @param panel
	 */
	public void remove_all_listeners(Draw panel){
		panel.removeMouseListener(ml_reta);
		panel.removeMouseListener(ml_circulo);
		panel.removeMouseListener(ml_poligono);
		panel.removeMouseListener(ml_elipse);
		panel.removeMouseListener(ml_ret);
		panel.removeMouseListener(ml_arc_elipse);
		panel.removeMouseListener(ml_arc_circulo);
		panel.removeMouseListener(ml_text);
		panel.removeKeyListener(kl_entrada_txt);
		panel.removeMouseListener(ml_mover_figura);
		panel.removeMouseListener(ml_escala_figura);
		panel.removeMouseListener(ml_rotacionar_figura);
		panel.removeMouseListener(ml_selecionar_area);
		panel.removeMouseListener(ml_scale_all);
		panel.removeMouseListener(ml_move_all);
		panel.removeMouseListener(ml_rotate_all);
		panel.removeMouseListener(ml_apagar_figura);
		panel.removeMouseListener(ml_move_selecao);
		panel.removeMouseListener(ml_scale_selecao);
		panel.removeMouseListener(ml_rotaciona_selecao);
	}	
}