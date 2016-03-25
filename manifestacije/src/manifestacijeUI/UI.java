package manifestacijeUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manifestacijeDAO.GradDAO;
import manifestacijeDAO.ManifestacijaDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;

public class UI {
	
public static Connection conn;
	
	static {
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");

			// konekcija
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/manifestacija", "root", "dudimir66");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws IOException {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				GradDAO.getAll(conn);
				break;
			case 2:
				ManifestacijaDAO.getAll(conn);
				break;
			case 3:
				ManifestacijaDAO.getManByID(conn);
				break;
			case 4:
				ManifestacijaDAO.update(conn);
				break;
			case 5:
				ManifestacijaDAO.getMAX(conn);
				break;
			case 6:
				ManifestacijaDAO.pisiUFajlMAN(conn);
				break;
			case 7:
				ManifestacijaDAO.citajIzFajla(conn);
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

public static void ispisiMenu() {
	System.out.println("Studentska Sluzba - Osnovne opcije:");
	System.out.println("\tOpcija broj 1 - ispisi gradove");
	System.out.println("\tOpcija broj 2 - ispisi manifeastacije");
	System.out.println("\tOpcija broj 3 - nadji manifestaciju po ID");
	System.out.println("\tOpcija broj 4 - promeni manifestaciju");
	System.out.println("\tOpcija broj 5 - manifestacija sa MAX posetilaca");
	System.out.println("\tOpcija broj 6 - pisi text");
	System.out.println("\tOpcija broj 7 - citaj iz teksta");

	System.out.println("\t\t ...");
	System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
}
}
