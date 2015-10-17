package structualSpec.syntactic.parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.testng.annotations.Test;

public class TestLexicalDB {
	@Test
	public void testDBConnnection() {
		Connection connnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connnection = DriverManager
					.getConnection("jdbc:sqlite:/Users/UCLAPLSE/Documents/project/nonLocalSpec/workFolder/db/nonLocalSpec.sqlite");
			Statement statement = connnection.createStatement();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	
	
}
