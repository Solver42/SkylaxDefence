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

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.DropMode;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

public class GUI {

	private JFrame frame;
	private JList list;
	private PathPrinter pathPrinter = null;
	private JTextArea txtEnterSearchWordwords;
	private JList resultList;


	private DefaultListModel model;


	private final Action action = new SwingAction();
	String[] hej = new String[]{"one", "two"};
	private JTextField txtEnterPathOf;

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
		frame.setTitle("SearchFurther");
		frame.setMinimumSize(new Dimension(560, 180));
		frame.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
				
				JPanel panel_1 = new JPanel();
				frame.getContentPane().add(panel_1);
						panel_1.setLayout(new GridLayout(2, 1, 0, 0));
				
						txtEnterSearchWordwords = new JTextArea();
						txtEnterSearchWordwords.setForeground(Color.DARK_GRAY);
						panel_1.add(txtEnterSearchWordwords);
						txtEnterSearchWordwords.setBackground(SystemColor.activeCaption);
						txtEnterSearchWordwords.setText("Enter search word/words separated by comma...");
						txtEnterSearchWordwords.setRows(3);
						
						JPanel panel_4 = new JPanel();
						panel_1.add(panel_4);
						panel_4.setBackground(SystemColor.activeCaption);
						panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
						txtEnterPathOf = new JTextField();
						txtEnterPathOf.setText("Enter the path that you'd like to search trough...");
						txtEnterPathOf.setForeground(Color.GREEN);
						txtEnterPathOf.setBackground(Color.BLACK);
						panel_4.add(txtEnterPathOf);
						txtEnterPathOf.setColumns(40);
						
								JButton btnSearch = new JButton("Search");
								panel_4.add(btnSearch);
								btnSearch.setAction(action);
												
												JPanel panel_2 = new JPanel();
												frame.getContentPane().add(panel_2);
														panel_2.setLayout(new GridLayout(0, 1, 0, 0));
												
														JList resultList_1 = new JList(model);
														resultList_1.setForeground(Color.DARK_GRAY);
														JScrollPane scrollPane = new JScrollPane();
														panel_2.add(scrollPane);
														scrollPane.setViewportView(resultList_1);
														resultList_1.setBackground(Color.WHITE);
		
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
