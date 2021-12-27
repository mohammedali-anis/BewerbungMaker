package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class DB {

	public static Connection connect() {

		Connection connect = null;

		try {

			String path = "src/main/resources/BewerbungMaker.db";

			File file = new File(path);
			String absolutePath = file.getAbsolutePath();
			connect = DriverManager.getConnection("jdbc:sqlite:" + absolutePath);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;

	}

	public static String sqlLogin(String email, String password) throws SQLException {

		ResultSet r = null;

		String Query = "Select * from User where email = '" + email + "' AND password ='" + password + "';";
		Statement s = null;
		s = DB.connect().createStatement();
		r = s.executeQuery(Query);
		String name = "NF";
		while (r.next()) {
			name = r.getString(1);
		}
		r.close();
		s.close();
		System.out.println(name);
		return name;
	}

	public static void sqlUserTable(DefaultTableModel tableModel) throws SQLException {

		ResultSet r = null;

		/*
		 * SELECT user_id from User natural join User_Role Where username='username' AND
		 * password='password'
		 */
		String Query = "Select * from Firma;";
		Statement s = DB.connect().createStatement();
		r = s.executeQuery(Query);
		while (r.next()) {
			String firma_id = r.getString(1);
			String name = r.getString(2);
			String sex = r.getString(3);
			String firstName = r.getString(4);
			String lastName = r.getString(5);
			String email = r.getString(6);
			String mobile = r.getString(7);
			String tel = r.getString(8);
			String street = r.getString(9);
			String no = r.getString(10);
			String plz = r.getString(11);
			String stadt = r.getString(12);
			String datum = r.getString(13);

			String[] tbData = { firma_id, name, sex, firstName, lastName, mobile, tel, street, no, plz, stadt, datum };

			tableModel.addRow(tbData);
		}

	}

	public static void sqlAddBewerbung(String Name, String sex, String firstName, String lastName, String email,
			String mobile, String tel, String strret, String no, String plz, String stadt, String datum)
			throws SQLException {

		try {

			Connection con1 = connect();
			String query1 = " insert into Firma (Name, sex, firstName_Emp, lastName_Emp, email, mobile, tel, strasse_Emp, no_Emp, plz_Emp, stadt_Emp, datum)"
					+ " values ('" + Name + "', '" + sex + "', '" + firstName + "', '" + lastName + "', '" + email
					+ "', '" + mobile + "', '" + tel + "', '" + strret + "', '" + no + "', '" + plz + "', '" + stadt
					+ "', '" + datum + "');'";
			PreparedStatement posted1 = con1.prepareStatement(query1);
			posted1.execute();

			con1.close();
			posted1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
