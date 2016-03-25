package manifestacijeDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.WrappedPlainView;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;
import manifestacijeMODEL.Grad;
import manifestacijeMODEL.Manifestacija;


public class ManifestacijaDAO {
	public static void getAll(Connection conn) {
		List<Manifestacija> retVal = new ArrayList<Manifestacija>();
		try {
			String query = "select * from manifestacija m left join grad g on m.grad= g.ptt; ";
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query.toString());
			while (rset.next()) {
				int id = rset.getInt(1);
				String naziv = rset.getString(2);
				int brojP = rset.getInt(3);
				Grad grad = new Grad(rset.getInt(5),rset.getString(6));
				
				Manifestacija m = new Manifestacija(id, naziv,brojP, grad);
				retVal.add(m);
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Manifestacija man : retVal){
			System.out.println(man);
		}
	}
	public static void getManByID(Connection conn) {
		Manifestacija m = null;
		getAll(conn);
		System.out.println("upisi ID manifestacije koju trazis ");
		int idM = ScannerWrapper.ocitajCeoBroj();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("SELECT * FROM manifestacija m left join grad g on m.grad= g.ptt WHERE id = "+idM );

			if (rset.next()) {
				int id = rset.getInt(1);
				String naziv = rset.getString(2);
				int brojP = rset.getInt(3);
				Grad grad = new Grad(rset.getInt(5),rset.getString(6));
				
				 m = new Manifestacija(id, naziv,brojP, grad);
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(m);
	}
	public static void update(Connection conn) {
		getAll(conn);
		System.out.println("Odaberi ID manifestaije koju zelis da promenis ");
		int idM = ScannerWrapper.ocitajCeoBroj();
		System.out.println("Odaberi novo ime za manifestaciju ");
		String noviNaziv = ScannerWrapper.ocitajTekst();
		System.out.println("Unesi br posetilaca");
		int brP = ScannerWrapper.ocitajCeoBroj();
		System.out.println("Unesi ptt grada");
		System.out.println("***napomena***");
		System.out.println("---- Moze se uneti samo ptt BG,NS,APA");
		String noviGrad = ScannerWrapper.ocitajTekst();
		
		try {
			String update = "update manifestacija set naziv= ?, brojPosetilaca=?, grad=? where id=?;";
			PreparedStatement pstmt = conn.prepareStatement(update);
			
			pstmt.setString(1, noviNaziv);
			pstmt.setInt(2, brP);
			pstmt.setString(3, noviGrad);
			pstmt.setInt(4, idM);
			
			if(pstmt.executeUpdate() == 1){
				System.out.println("uspela promena");
			}else{
				System.out.println("nije uspela promena");
			}
				
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Manifestacija getMAX(Connection conn) {
		Manifestacija m = null;
		int max = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt
					.executeQuery("SELECT MAX(brojPosetilaca) FROM manifestacija;" );

			if (rset.next()) {
				 max = rset.getInt(1);
				
			}
			rset.close();
			stmt.close();
		
		
				Statement stmt2 = conn.createStatement();
				ResultSet rset2 = stmt2
						.executeQuery("SELECT * FROM manifestacija m left join grad g on m.grad= g.ptt WHERE brojPosetilaca ="+max);

				if (rset2.next()) {
					int id = rset2.getInt(1);
					String naziv = rset2.getString(2);
					int brojP = rset2.getInt(3);
					Grad grad = new Grad(rset2.getInt(5),rset2.getString(6));
					
					 m = new Manifestacija(id, naziv,brojP, grad);
				}
				rset2.close();
				stmt2.close();	
			
				System.out.println(m);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
		}
	
	public static void pisiUFajlMAN(Connection conn) throws IOException {
		String sP = System.getProperty("file.separator");
		File dokument = new File("."+sP+"zaTekst"+sP+"manifestacija.csv");
		
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		
		
			out2.println("<"+getMAX(conn).getNaziv()+"><"+getMAX(conn).getBrojPosetilaca()+">");
		
		
		out2.flush();
		out2.close();
		
	}
	public static void citajIzFajla(Connection conn) throws IOException {
		String sP = System.getProperty("file.separator");
		File dokument = new File("."+sP+"zaTekst"+sP+"manifestacija.csv");
		if(dokument.exists()){

			BufferedReader in = new BufferedReader(new FileReader(dokument));
			
			in.mark(1);
			if(in.read()!='\ufeff'){
				in.reset();
			}
			
			System.out.println(in.readLine());
			in.close();
	}
	}
}




