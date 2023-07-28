package br.com.one.conversor.moeda.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConversorMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem opcaoConversorMoeda;
	private JMenuItem opcaoConversorTemperatura;
	private JMenuItem opcaoSair;
	private String conversorDeMoeda;
	private String conversorDeTemperatura;

	public ConversorMenu() {
		setSize(400, 300);
		setTitle("Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel painel = new JPanel();

		exibeMenu();
		painel.add(this.menuBar);
		add(painel);
	}

	private void exibeMenu() {
		inicializaAtributosAdicionaOpcoesNoMenu();

		this.opcaoConversorMoeda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.opcaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tem certeza que deseja sair?");
				System.exit(0);
			}
		});
	}

	private void inicializaAtributosAdicionaOpcoesNoMenu() {
		// inicializar atributos
		this.conversorDeMoeda = "Conversor de Moeda";
		this.conversorDeTemperatura = "Conversor de Temperatura";
		this.menuBar = new JMenuBar();
		this.menu = new JMenu("Escolha uma operação:");
		this.opcaoConversorMoeda = new JMenuItem(this.conversorDeMoeda);
		this.opcaoConversorTemperatura = new JMenuItem(this.conversorDeTemperatura);
		this.opcaoSair = new JMenuItem("Sair");

		// adiciona as itens do menu/opções no menu
		this.menu.add(opcaoConversorMoeda);
		this.menu.add(opcaoConversorTemperatura);
		this.menu.add(opcaoSair);

		// adiociona o menu em menubar
		this.menuBar.add(menu);
	}

}
