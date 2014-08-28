package se.MinimalisticPerfectionTechnology.start;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class GUI {

	private JFrame frame;
	private JTextField txtEnterPathOf;
	private JList list;
	private PathPrinter pathPrinter = null;
	private final Action action = new SwingAction();
	private JTextField txtEnterSearchWordwords;
	String[] hej = new String[]{"one", "two"};

	/**
	 * Launch the application.
	 */

	public GUI() {

		initialize();
		this.frame.setVisible(true);




		System.out.println("Processing...");
		pathPrinter = new PathPrinter(list);
		//pathPrinter.printPaths("C:\\Users\\Johan\\Documents\\dator\\dev\\MPT\\FindFast\\PoliceSearch\\sub", hej);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 0));

		txtEnterSearchWordwords = new JTextField();
		txtEnterSearchWordwords.setText("Enter search word/words...");
		panel.add(txtEnterSearchWordwords);
		txtEnterSearchWordwords.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		txtEnterPathOf = new JTextField();
		txtEnterPathOf.setText("Enter path of super folder to search in...");
		txtEnterPathOf.setToolTipText("");
		panel_1.add(txtEnterPathOf);
		txtEnterPathOf.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setAction(action);
		panel_1.add(btnSearch);

		JList list = new JList();
		panel.add(list);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String[] str = txtEnterSearchWordwords.getText().split(" ");
			//System.out.println(str[0]);
			pathPrinter.printPaths(txtEnterPathOf.getText(), str);

		}
	}
}
