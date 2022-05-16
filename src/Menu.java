import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener {
	
	JPanel header, body, footer;
	JLabel titleLabel, creditLabel;
	JButton viewButton;

	public Menu() {
		setFrame();

		// TITLE ================================================
		header = new JPanel();
		header.setBackground(Color.decode("#FEDBD0"));

		titleLabel = new JLabel("[ PT Pudding ]");
		titleLabel.setFont(new Font("poppins", Font.BOLD, 30));
		titleLabel.setPreferredSize(new Dimension(200, 85));
		titleLabel.setForeground(Color.decode("#442C2E"));

		this.add(header, BorderLayout.NORTH);
		header.add(titleLabel);

		// VIEW =================================================
		body = new JPanel();
		body.setBackground(Color.decode("#FEDBD0"));
		
		viewButton = new JButton("V I E W  M E N U");
		viewButton.setPreferredSize(new Dimension(300, 60));
		viewButton.addActionListener(this);
		viewButton.setBackground(Color.decode("#FEEAE6"));
		viewButton.setForeground(Color.decode("#442C2E"));
		
		this.add(body, BorderLayout.CENTER);
		body.add(viewButton);
		
		// CREDIT ===============================================
		footer = new JPanel();
		footer.setBackground(Color.decode("#442C2E"));

		creditLabel = new JLabel("Made by Musang Software House");
		creditLabel.setPreferredSize(new Dimension(190, 60));
		creditLabel.setFont(new Font("poppins", Font.BOLD, 12));
		creditLabel.setForeground(Color.white);

		this.add(footer, BorderLayout.SOUTH);
		footer.add(creditLabel);
	}
	
	public void setFrame() {
		this.setVisible(true);
		this.setSize(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Menu Application");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Features();
	}

}
