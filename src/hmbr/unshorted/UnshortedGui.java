package hmbr.unshorted;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class UnshortedGui extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3926169212953361833L;
	
	private String title = "Discover url";

	public UnshortedGui() {
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Main");
		JMenuItem close = new JMenuItem("Close");
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(close);
		menubar.add(menu);
		setJMenuBar(menubar);

		Container content = getContentPane();

		GroupLayout layout = new GroupLayout(content);
		content.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		final JLabel url = new JLabel("Url");
		JLabel result = new JLabel("Result");

		final JTextField urlInput = new JTextField();
		urlInput.setColumns(20);
		final JTextField urlOutput = new JTextField();
		urlOutput.setEditable(false);
		JButton find = new JButton("Find");

		find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String unknow = urlInput.getText().trim();
				String finds = null;
				if (unknow.length() > 0) {
					if (unknow.startsWith("http")) {
						finds = Unshorted.discover(unknow);
					} else {
						finds = Unshorted.discover("http://" + unknow);

					}

				}
				if (finds != null) {
					urlOutput.setText(finds);
				} else {
					urlOutput.setText("error");
				}

			}
		});

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(url).addComponent(result))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(urlInput).addComponent(urlOutput)).addComponent(find)

		);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(url).addComponent(urlInput).addComponent(find))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(result).addComponent(urlOutput)));

		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new UnshortedGui();
	}

}
