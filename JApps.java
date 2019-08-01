import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JApps extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JApps frame = new JApps();
					frame.setVisible(true);
					frame.setSize(600,300);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JApps() {
		setTitle("UTILITY TOOL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(300, 300, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 250, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Notepad2.main(new String[]{});
			}
		});
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("notepad.jpg")));
		

		JLabel lblJavaApplicationWorld = new JLabel("UTILITY TOOL");
		lblJavaApplicationWorld.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblJavaApplicationWorld.setForeground(new Color(204, 51, 51));
		

		

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TTT1.main(new String[]{});
			}
		});
		button_2.setIcon(new ImageIcon(getClass().getResource("tictactoe.jpg")));
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup())
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
		
							.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
							
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup())
					.addGap(2)
					.addComponent(lblJavaApplicationWorld)
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING))

						.addComponent(btnNewButton)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING))
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						
		);
		
	}
}