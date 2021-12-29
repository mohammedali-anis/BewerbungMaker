package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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
			String postion = r.getString(3);
			String sex = r.getString(4);
			String firstName = r.getString(5);
			String lastName = r.getString(6);
			String email = r.getString(7);
			String mobile = r.getString(8);
			String tel = r.getString(9);
			String street = r.getString(10);
			String no = r.getString(11);
			String plz = r.getString(12);
			String stadt = r.getString(13);
			String datum = r.getString(14);

			String[] tbData = { firma_id, name, postion, sex, firstName, lastName, email, mobile, tel, street, no, plz,
					stadt, datum };

			tableModel.addRow(tbData);
		}

	}

	public static void sqlAddBewerbung(String Name, String postion, String sex, String firstName, String lastName,
			String email, String mobile, String tel, String strret, String plz, String no, String stadt, String datum,
			String user_id) throws SQLException {

		try {

			Connection con1 = connect();
			String query1 = " insert into Firma (Name, postion, sex, firstName_Emp, lastName_Emp, email, mobile, tel, strasse_Emp, no_Emp, plz_Emp, stadt_Emp, datum)"
					+ " values ('" + Name + "', '" + postion + "', '" + sex + "', '" + firstName + "', '" + lastName
					+ "', '" + email + "', '" + mobile + "', '" + tel + "', '" + strret + "', '" + no + "', '" + plz
					+ "', '" + stadt + "', '" + datum + "');'";
			PreparedStatement posted1 = con1.prepareStatement(query1);
			posted1.execute();

			con1.close();
			posted1.close();

			Connection con2 = connect();
			String query2 = " insert into Bewerbung (user_id, firma_id)" + " values ('" + user_id
					+ "', (Select max(firma_id) from Firma));";
			PreparedStatement posted2 = con2.prepareStatement(query2);
			posted2.execute();

			con2.close();
			posted2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public static ArrayList<String> getInfo(String bewerbungID) throws SQLException {
//
//		ResultSet r = null;
//
//		String Query = "Select * from Firma Natural Join Bewerbung Where firma_id='" + bewerbungID + "';";
//		Statement s = null;
//		s = DB.connect().createStatement();
//		r = s.executeQuery(Query);
//		String user_id = null;
//		ArrayList<String> al = new ArrayList<String>();
//		while (r.next()) {
//			al.add(r.getString(2));
//			al.add(r.getString(4));
//			al.add(r.getString(5));
//			al.add(r.getString(6));
//			al.add(r.getString(10));
//			al.add(r.getString(11));
//			al.add(r.getString(12));
//			al.add(r.getString(13));
//			user_id = r.getString(15);
//
//		}
//		r.close();
//		s.close();
//
//		ResultSet r2 = null;
//
//		String Query2 = "Select * from User Where user_id='" + user_id + "';";
//		Statement s2 = null;
//		s2 = DB.connect().createStatement();
//		r2 = s2.executeQuery(Query2);
//
//		while (r2.next()) {
//			al.add(r2.getString(2));
//			al.add(r2.getString(3));
//			al.add(r2.getString(7));
//			al.add(r2.getString(8));
//			al.add(r2.getString(9));
//			al.add(r2.getString(10));
//
//		}
//		r2.close();
//		s2.close();
//		
//		System.out.println(al);
//		
//		return al;
//		
//
//	}

	public static ArrayList<HashMap> getInfo(String bewerbungID) throws SQLException {

		HashMap<String, String> company = new HashMap<String, String>();
		HashMap<String, String> me = new HashMap<String, String>();

		ResultSet r = null;

		String Query = "Select * from Firma Natural Join Bewerbung Where firma_id='" + bewerbungID + "';";
		Statement s = null;
		s = DB.connect().createStatement();
		r = s.executeQuery(Query);
		String user_id = null;
		ArrayList<HashMap> twoMap = new ArrayList<HashMap>();
		while (r.next()) {
			company.put("CompanyName", r.getString(2));
			company.put("Position", r.getString(3));
			company.put("Sex", r.getString(4));
			company.put("FirstName", r.getString(5));
			company.put("LastName", r.getString(6));
			company.put("Email", r.getString(7));
			company.put("Mobile", r.getString(8));
			company.put("Tel", r.getString(9));
			company.put("Street", r.getString(10));
			company.put("No", r.getString(11));
			company.put("PLZ", r.getString(12));
			company.put("City", r.getString(13));

			user_id = r.getString(15);

		}
		r.close();
		s.close();

		ResultSet r2 = null;

		String Query2 = "Select * from User Where user_id='" + user_id + "';";
		Statement s2 = null;
		s2 = DB.connect().createStatement();
		r2 = s2.executeQuery(Query2);

		while (r2.next()) {
			me.put("FirstName", r2.getString(2));
			me.put("LastName", r2.getString(3));
			me.put("Email", r2.getString(4));
			me.put("Mobile", r2.getString(6));
			me.put("Street", r2.getString(7));
			me.put("No", r2.getString(8));
			me.put("PLZ", r2.getString(9));
			me.put("City", r2.getString(10));

		}
		r2.close();
		s2.close();

		twoMap.add(company);
		twoMap.add(me);

		return twoMap;

	}

}
