import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

	Connection con;
	
	ResultSet rs;
	PreparedStatement ps;

	public Connect() {
		try { 
			String url = "jdbc:mysql://localhost:3306/pt_pudding";
			String username = "root";
			String password = "";

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet getMenu() {
		try {
			ps = con.prepareStatement("select * from tabel_menu");
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return rs;
	}

	public ResultSet insertMenu(String Kode, String Nama, int Harga, int Stok) {
		try {
			ps = con.prepareStatement("insert into tabel_menu (`Kode`, `Nama`, `Harga`, `Stok`) values (?,?,?,?)");
			ps.setString(1, Kode);
			ps.setString(2, Nama);
			ps.setInt(3, Harga);
			ps.setInt(4, Stok);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rs;
	}

	public ResultSet updateMenu(String Kode, int Harga, int Stok) {
		try {
			ps = con.prepareStatement("update tabel_menu set `Harga` = (?), `Stok` = (?) where `Kode` = (?)");
			ps.setInt(1, Harga);
			ps.setInt(2, Stok);
			ps.setString(3, Kode);
			ps.execute();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return rs;
	}
	
	public ResultSet deleteMenu(String Kode) {
		try {
			ps = con.prepareStatement("delete from tabel_menu where `Kode` = (?)");
			ps.setString(1, Kode);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rs;
	}
}