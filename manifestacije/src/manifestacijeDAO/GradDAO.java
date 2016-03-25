package manifestacijeDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import manifestacijeMODEL.Grad;



public class GradDAO {
	public static void getAll(Connection conn) {
		List<Grad> retVal = new ArrayList<Grad>();
		try {
			String query = "SELECT * FROM grad ";
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query.toString());
			while (rset.next()) {
				int id = rset.getInt(1);
				String naziv = rset.getString(2);
				
				Grad g = new Grad(id, naziv);
				retVal.add(g);
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Grad gg : retVal){
		System.out.println(gg);
	}
	}	
}
