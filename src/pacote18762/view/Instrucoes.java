package pacote18762.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Instrucoes extends JFrame{
	
	// Panels 
	private JPanel main_panel;
	private JPanel display_panel;
	
	// Menus de seleção
	private JMenuBar menu_bar;
	private JMenuItem menu_desenhos, menu_at;
	private JMenuItem mi_fechar;
	
	// Strings
	private static final String STR_TITULO = "Instruções - ";
	private static final String STR_DESENHO = "Desenho";
	private static final String STR_AT = "Á. Trabalho";
	private static final String STR_RETAS = "Retas", STR_CIRCULOS = "Circulos", STR_ELIPSE= "Elipse", STR_POLIGONO= "Poligono";
	private static final String STR_RETANGULO = "Retangulo", STR_TEXT = "Texto", STR_ESCALA= "Escala", STR_LINHAS= "Tipos de Linha";
	private static final String STR_MOVER = "Mover", STR_ROT = "Rotacionar", STR_BORRACHA = "Borracha", STR_TAM_LETRA = "Tamanho da Letra", STR_COR_LINHA = "Cor da Linha";
	private static final String[] op_desenho = {STR_RETAS, STR_CIRCULOS, STR_ELIPSE, STR_POLIGONO, STR_RETANGULO, STR_TEXT, STR_TAM_LETRA,STR_ESCALA, STR_LINHAS, STR_COR_LINHA, STR_MOVER, STR_ROT, STR_BORRACHA};
	
	private static final String STR_PTO_REF = "Pontos de Referência", STR_SELECAO = "Seleção de Área", STR_GRADE = "Grade", STR_FUNDO = "Cor de Fundo";
	private static final String[] op_at= {STR_PTO_REF, STR_SELECAO, STR_GRADE, STR_FUNDO};
	
	// Dim dos botões
	private Dimension dim;
	
	private JTextArea informacoes;
	
	public Instrucoes(){
		this.dim = new Dimension(250,30);
		
		this.display_panel  = new JPanel();
		this.main_panel = new JPanel();
		
		this.menu_bar = new JMenuBar();
		this.menu_desenhos = new JMenuItem(Instrucoes.STR_DESENHO);
		this.menu_at = new JMenuItem(Instrucoes.STR_AT);
		this.mi_fechar = new JMenuItem("Fechar");
		
		this.informacoes = new JTextArea();
		
		this.mount();
	}
	
	
	private void mount(){
		menu_desenhos.setPreferredSize(dim);
//		menu_desenhos.setMaximumSize(dim);
		menu_at.setPreferredSize(dim);
//		menu_at.setMaximumSize(dim);
		mi_fechar.setPreferredSize(dim);
//		mi_fechar.setMaximumSize(dim);
		
		// menus
		menu_desenhos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				display_panel.repaint();
				JComboBox<String> list = new JComboBox<String>(op_desenho);
				JOptionPane.showMessageDialog(null, list, "Escolha um dos tópicos:", JOptionPane.PLAIN_MESSAGE);
				try {
					switch(list.getSelectedItem().toString()){
						case STR_RETAS : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/retas.png")), 0, 0, null); setTitle(STR_TITULO+STR_RETAS); break;
						case STR_CIRCULOS : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/circulos.png")), 0, 0, null); setTitle(STR_TITULO+STR_CIRCULOS); break;
						case STR_ELIPSE: display_panel.getGraphics().drawImage(ImageIO.read(new File("info/elipses.png")), 0, 0, null); setTitle(STR_TITULO+STR_ELIPSE); break;
						case STR_POLIGONO : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/poligonos.png")), 0, 0, null); setTitle(STR_TITULO+STR_POLIGONO); break;
						case STR_RETANGULO : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/retangulo.png")), 0, 0, null); setTitle(STR_TITULO+STR_RETANGULO); break;
						case STR_TEXT : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/textos.png")), 0, 0, null); setTitle(STR_TITULO+STR_TEXT); break;
						case STR_TAM_LETRA : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/tamletra.png")), 0, 0, null); setTitle(STR_TITULO+STR_TAM_LETRA); break;
						case STR_ESCALA : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/escala.png")), 0, 0, null); setTitle(STR_TITULO+STR_ESCALA); break;
						case STR_LINHAS : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/linhas.png")), 0, 0, null); setTitle(STR_TITULO+STR_LINHAS); break;
						case STR_COR_LINHA: display_panel.getGraphics().drawImage(ImageIO.read(new File("info/corlinha.png")), 0, 0, null); setTitle(STR_TITULO+STR_COR_LINHA); break;
						case STR_MOVER : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/mover.png")), 0, 0, null); setTitle(STR_TITULO+STR_MOVER); break;
						case STR_ROT : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/rotacionar.png")), 0, 0, null); setTitle(STR_TITULO+STR_ROT); break;
						case STR_BORRACHA : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/borracha.png")), 0, 0, null); setTitle(STR_TITULO+STR_BORRACHA); break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		menu_at.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<String> list = new JComboBox<String>(op_at);
				JOptionPane.showMessageDialog(null, list, "Escolha um tópico:", JOptionPane.PLAIN_MESSAGE);
				try {
					switch(list.getSelectedItem().toString()){
						case STR_PTO_REF : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/pontoreferencia.png")), 0, 0, null); setTitle(STR_TITULO+STR_PTO_REF); break;
						case STR_SELECAO : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/selecao.png")), 0, 0, null); setTitle(STR_TITULO+STR_SELECAO); break;
						case STR_GRADE: display_panel.getGraphics().drawImage(ImageIO.read(new File("info/grade.png")), 0, 0, null); setTitle(STR_TITULO+STR_GRADE); break;
						case STR_FUNDO : display_panel.getGraphics().drawImage(ImageIO.read(new File("info/corfundo.png")), 0, 0, null); setTitle(STR_TITULO+STR_FUNDO); break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mi_fechar.setPreferredSize(dim);
		mi_fechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		menu_bar.add(menu_desenhos);
		menu_bar.add(menu_at);
		menu_bar.add(mi_fechar);
		
		// painel para mostrar as informações de cada item
		informacoes.setText("Teste\nTeste");
		informacoes.setEditable(false);
		informacoes.setBackground(Color.white);
		informacoes.setLineWrap(true);
		
		// painel principal
		display_panel.setBackground(Color.white);
		main_panel.setLayout(new GridLayout(1,1));
		main_panel.setBackground(Color.white);
		main_panel.add(display_panel);
		
		this.setJMenuBar(menu_bar);
		this.setContentPane(main_panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(300,600));
		this.setResizable(false);
		this.setTitle(STR_TITULO);
		this.setVisible(true);
	}
}
