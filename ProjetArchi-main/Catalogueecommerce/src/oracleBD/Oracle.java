package oracleBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Oracle 
{
	private static Connection cn;
	public Oracle()
	{
		
	}
	
	public static Connection getCn()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut","poissonr","2410005954P");			
		} catch (ClassNotFoundException e) 
		{
		
			e.printStackTrace();
		} catch (SQLException e) 
		{
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}
}
