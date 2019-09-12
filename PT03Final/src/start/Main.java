package start;

import java.sql.Connection;

import connection.ConnectionFactory;
import presentation.Gui;

public class Main {

	public static void main(String[] args) {
		Connection con=ConnectionFactory.getConnection();
		ConnectionFactory.close(con);
		Gui g=new Gui();
	}
	
}
