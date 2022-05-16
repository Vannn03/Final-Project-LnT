import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Features extends JFrame implements ActionListener {
	
	JPanel header, body, footer;
	JLabel hargaLabel, stokLabel;
	JTextField updateHargaTF, updateStokTF;
	JButton insertButton, updateButton, deleteButton;
	
	JTable menuTable = new JTable();
	DefaultTableModel dtm;
	JScrollPane jsp;
	
	Vector<String> columnNama = new Vector<>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	
	Connect db = new Connect();

	public Features() {
		setFrame();
		getData();
		
		//UPDATE ====================================================================
		header = new JPanel();
		header.setBackground(Color.decode("#FEDBD0"));
		
		hargaLabel = new JLabel("Harga Menu: ");
		hargaLabel.setForeground(Color.decode("#442C2E"));

		updateHargaTF = new JTextField();
		updateHargaTF.setPreferredSize(new Dimension(150, 30));
		updateHargaTF.setBackground(Color.white);
		updateHargaTF.setForeground(Color.decode("#442C2E"));

		stokLabel = new JLabel("Stok Menu: ");
		stokLabel.setForeground(Color.decode("#442C2E"));

		updateStokTF = new JTextField();
		updateStokTF.setPreferredSize(new Dimension(150, 30));
		updateStokTF.setBackground(Color.white);
		updateStokTF.setForeground(Color.decode("#442C2E"));

		header.add(hargaLabel);
		header.add(updateHargaTF);
		header.add(stokLabel);
		header.add(updateStokTF);
		
		updateButton = new JButton("U P D A T E");
		updateButton.setPreferredSize(new Dimension(175, 40));
		updateButton.setBackground(Color.decode("#FEEAE6"));
		updateButton.setForeground(Color.decode("#442C2E"));
		updateButton.addActionListener(this);
		
		this.add(header, BorderLayout.NORTH);
		header.add(updateButton);
		
		
		// TABLE ====================================================================
		body = new JPanel();
		body.setBackground(Color.white);
		
		columnNama.add("Kode");
		columnNama.add("Nama");
		columnNama.add("Harga");
		columnNama.add("Stok");

		dtm = new DefaultTableModel(data, columnNama);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		this.add(body, BorderLayout.CENTER);
		body.add(jsp);
		
		// INSERT & DELETE ==========================================================
		footer = new JPanel();
		footer.setPreferredSize(new Dimension(0, 60));
		footer.setBackground(Color.decode("#FEDBD0"));
		
		insertButton = new JButton("I N S E R T");
		insertButton.setPreferredSize(new Dimension(175, 40));
		insertButton.setBackground(Color.decode("#FEEAE6"));
		insertButton.setForeground(Color.decode("#442C2E"));
		insertButton.addActionListener(this);
		
		deleteButton = new JButton("D E L E T E");
		deleteButton.setPreferredSize(new Dimension(175, 40));
		deleteButton.setBackground(Color.decode("#FEEAE6"));
		deleteButton.setForeground(Color.decode("#442C2E"));
		deleteButton.addActionListener(this);
		
		this.add(footer, BorderLayout.SOUTH);
		footer.add(insertButton);
		footer.add(deleteButton);
	}
	
	
	public void setFrame() {
		this.setVisible(true);
		this.setSize(675, 585);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Menu Application");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == insertButton) {
			new Insert();
		}
		else if (e.getSource() == updateButton) {

			int index = menuTable.getSelectedRow();

			String Kode = String.valueOf(dtm.getValueAt(index, 0));
			
			String textHarga = updateHargaTF.getText();
			int Harga = Integer.parseInt(textHarga);
			
			String textStok = updateStokTF.getText();
			int Stok = Integer.parseInt(textStok);
			
			db.updateMenu(Kode, Harga, Stok);
			
			JOptionPane.showMessageDialog(this, "Menu Updated!");
			dtm.setValueAt(Harga, index, 2);
			dtm.setValueAt(Stok, index, 3);
		}
		else {
			int index = menuTable.getSelectedRow();

			String Kode = String.valueOf(dtm.getValueAt(index, 0));

			db.deleteMenu(Kode);

			JOptionPane.showMessageDialog(this, "Menu deleted!");
			dtm.removeRow(index);
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
