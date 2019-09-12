package dao;

import java.sql.*;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO {
	private static final String insertStatementString = "INSERT INTO Client (idClient,name,age,address)"
			+ " VALUES (?,?,?,?)";
	private static final String deleteStatementString = "DELETE FROM Client WHERE idClient=?";
	private final static String findStatementString = "SELECT * FROM Client";

	public static ArrayList<Client> find() {
		ArrayList<Client> toReturn = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();
			while(rs.next())
			{
			int idClient=rs.getInt("idClient");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			toReturn.add(new Client(idClient, name, age, address));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static void insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, client.getIdClient());
			insertStatement.setString(2, client.getName());
			insertStatement.setInt(3, client.getAge());
			insertStatement.setString(4, client.getAddress());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void deleteById(int id)
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	private final static String findStatementStringID = "SELECT * FROM client where idClient =?" ;

	public static Client findById(int clientId) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringID);
			findStatement.setLong(1, clientId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			
			toReturn = new Client(clientId, name, age,address);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

}
