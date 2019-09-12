package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import model.Client;
import model.Orders;

public class OrderDAO {
	private static final String insertStatementString = "INSERT INTO Orders (idClient,idProduct,Quantity)"
			+ " VALUES (?,?,?)";
	
	
	public static void insert(Orders order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getIdClient());
			insertStatement.setInt(2, order.getIdProduct());
			insertStatement.setInt(3, order.getQuantity());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
