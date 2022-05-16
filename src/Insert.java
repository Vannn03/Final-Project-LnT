import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Insert extends JFrame implements ActionListener {

	JPanel body, footer;
	JLabel namaLabel, hargaLabel, stokLabel;
	JTextField namaTF, hargaTF, stokTF;
	JButton addButton;
	
	DefaultTableModel dtm;
	
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	
	Connect db = new Connect();
	
	public Insert() {
		setFrame();
		getData();
		
		// BODY ------------------------------------------------------------
		body = new JPanel();
		body.setBackground(Color.decode("#FEDBD0"));
		this.add(body, BorderLayout.CENTER);
		
		// NAMA ==================================================
		namaLabel = new JLabel("Nama Menu: ");
		namaLabel.setForeground(Color.decode("#442C2E"));
		
		namaTF = new JTextField();
		namaTF.setPreferredSize(new Dimension(200, 30));
		namaTF.setForeground(Color.decode("#442C2E"));
		
		body.add(namaLabel);
		body.add(namaTF);

		// HARGA =================================================
		hargaLabel = new JLabel("Harga Menu: ");
		hargaLabel.setForeground(Color.decode("#442C2E"));
		
		hargaTF = new JTextField();
		hargaTF.setPreferredSize(new Dimension(200, 30));
		hargaTF.setForeground(Color.decode("#442C2E"));
		
		body.add(hargaLabel);
		body.add(hargaTF);
		
		// STOK ==================================================
		stokLabel = new JLabel("Stok Menu: ");
		stokLabel.setForeground(Color.decode("#442C2E"));
		
		stokTF = new JTextField();
		stokTF.setPreferredSize(new Dimension(200, 30));
		stokTF.setForeground(Color.decode("#442C2E"));
		
		body.add(stokLabel);
		body.add(stokTF);
		
		
		// FOOTER ----------------------------------------------------------
		footer = new JPanel();
		footer.setBackground(Color.decode("#442C2E"));
		this.add(footer, BorderLayout.SOUTH);
		
		// SUBMIT ================================================
		addButton = new JButton("A D D");
		addButton.setPreferredSize(new Dimension(175, 40));
		addButton.addActionListener(this);
		addButton.setBackground(Color.decode("#FEEAE6"));
		addButton.setForeground(Color.decode("#442C2E"));
		
		footer.add(addButton);
	}
	
	public void setFrame() {
		this.setVisible(true);
		this.setSize(250, 285);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Insert Menu");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addButton) {
			dtm = new DefaultTableModel();
			
			int kodeAngka = 0;
			while (kodeAngka < 100 || kodeAngka > 1000) {
				Random rand = new Random();
				kodeAngka = rand.nextInt();
			}
			
			String Kode = Integer.toString(kodeAngka);
			Kode = "PD-" + kodeAngka;
			
			String Nama = namaTF.getText();
			
			String textHarga = hargaTF.getText();
			int Harga = Integer.parseInt(textHarga);
			
			String textStok = stokTF.getText();
			int Stok = Integer.parseInt(textStok);
			
			db.insertMenu(Kode, Nama, Harga, Stok); 
			
			Object row[] = {Kode, Nama, Harga, Stok};
			dtm.addRow(row);
			
			JOptionPane.showMessageDialog(this, "New menu added!");
			
			new Features();
		}
	}	
	
	public void getData() {
		db.rs = db.getMenu();
		
		try {
			while (db.rs.next()) {
				Vector<Object> newRow = new Vector<>();
				newRow.add(db.rs.getString("Kode"));
				newRow.add(db.rs.getString("Nama"));
				newRow.add(db.rs.getString("Harga"));
				newRow.add(db.rs.getString("Stok"));
				
				data.add(newRow);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
