package se.MinimalisticPerfectionTechnology.start;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.DropMode;

public class GUI {

	private JFrame frame;
	private JList list;
	private PathPrinter pathPrinter = null;
	private JTextArea txtEnterPathOf;
	private JTextArea txtEnterSearchWordwords;
	private JList resultList;


	private DefaultListModel model;


	private final Action action = new SwingAction();
	String[] hej = new String[]{"one", "two"};

	/**
	 * Launch the application.
	 */

	public GUI() {

		initialize();
		this.frame.setVisible(true);
		pathPrinter = new PathPrinter(model);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	public void addItemToList(String str)
	{
		model.addElement(str);
	}

	private void initialize() {
		
		model = new DefaultListModel();
		model.addElement("resultList");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 0));

		txtEnterSearchWordwords = new JTextArea();
		txtEnterSearchWordwords.setText("Enter search word/words separated by comma...");
		txtEnterSearchWordwords.setRows(3);
		panel.add(txtEnterSearchWordwords);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		txtEnterPathOf = new JTextArea();
		txtEnterPathOf.setBackground(Color.WHITE);
		txtEnterPathOf.setText("Enter path...");
		txtEnterPathOf.setRows(1);
		panel_1.add(txtEnterPathOf);

		JButton btnSearch = new JButton("Search");
		btnSearch.setAction(action);
		panel_1.add(btnSearch);

		JList resultList = new JList(model);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(resultList);
		resultList.setBackground(Color.WHITE);
		panel.add(scrollPane);
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Fetch");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			model.clear();
			String[] str = txtEnterSearchWordwords.getText().split(",");
			pathPrinter.printPaths(txtEnterPathOf.getText(), str);
		}
	}
}
