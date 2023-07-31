package br.com.one.conversor.moeda.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.one.conversor.moeda.services.ConversorMoedaService;

public class ConversorMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem opcaoConversorMoeda;
	private JMenuItem opcaoConversorTemperatura;
	private JMenuItem opcaoSair;
	private String conversorMoeda = "Conversor de Moeda";
	private String conversorDeTemperatura = "Conversor de Temperatura";
	private ConversorMoedaService conversorMoedaService;

	public ConversorMenu() {
		setSize(400, 300);
		setTitle("Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// inicializar atributos
		this.menuBar = new JMenuBar();
		this.menu = new JMenu("Escolha uma operação:");
		this.opcaoConversorMoeda = new JMenuItem(this.conversorMoeda);
		this.opcaoConversorTemperatura = new JMenuItem(this.conversorDeTemperatura);
		this.opcaoSair = new JMenuItem("Sair");
		this.conversorMoedaService = new ConversorMoedaService();

		// adiciona as itens do menu/opções no menu
		this.menu.add(opcaoConversorMoeda);
		this.menu.add(opcaoConversorTemperatura);
		this.menu.add(opcaoSair);

		// adiociona o menu em menubar
		this.menuBar.add(menu);

		JPanel painel = new JPanel();

		this.exibeMenu();
		painel.add(this.menuBar);
		add(painel);
	}

	private void exibeMenu() {
		this.opcaoConversorMoeda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String entrada = JOptionPane.showInputDialog("Insira um valor:");
				double parseEntrada = Double.parseDouble(entrada);
				chamarMenuDeEscolha(parseEntrada);
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

	private void chamarMenuDeEscolha(double valor) {
		Object[] opcoes = { "De Reais a Dólares", "De Reais a Euros", "De Reais a Libras", "De Dólares a Reais",
				"De Euro a Reais", "De Libras a Reais" };

		String selecionarOpcao = JOptionPane
				.showInputDialog(null, "Escolha a moeda para a qual você deseja girar seu dinheiro:", "Conversão",
						JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0])
				.toString();

		switch (selecionarOpcao) {
		case "De Reais a Dólares":
			this.conversorMoedaService.conversorRealDolar(valor);
			break;
		case "De Reais a Euros":
			this.conversorMoedaService.conversorRealEuro(valor);
			break;
		case "De Reais a Libras":
			this.conversorMoedaService.conversorRealLibra(valor);
			break;
		case "De Dólares a Reais":
			this.conversorMoedaService.conversorDolarReal(valor);
		default:
			JOptionPane.showMessageDialog(null, "Moeda inválida, tente novamente!", null, JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}
