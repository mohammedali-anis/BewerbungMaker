package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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
		String name="NF";
		while (r.next()) {
			name = r.getString(1);
		}
		r.close();
		s.close();
		System.out.println(name);
		return name;
	}

}
