package dao;

import java.sql.*;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Product;
import model.Product;

public class ProductDAO {
	private static final String insertStatementString = "INSERT INTO Product (idProduct,name,price,stock,category)"
			+ " VALUES (?,?,?,?,?)";
	private static final String deleteStatementString = "DELETE FROM Product WHERE idProduct=?";
	private final static String findStatementString = "SELECT * FROM Product";

	public static ArrayList<Product> find() {
		ArrayList<Product> toReturn = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();
			while(rs.next())
			{
			int idProduct=rs.getInt("idProduct");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			String category = rs.getString("category");
			toReturn.add(new Product(idProduct, name, price, stock,category));
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
	
	public static void insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString,Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, product.getIdProduct());
			insertStatement.setString(2, product.getName());
			insertStatement.setInt(3, product.getPrice());
			insertStatement.setInt(4, product.getStock());
			insertStatement.setString(5, product.getCategory());
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
	
	private final static String findStatementStringID = "SELECT * FROM product where idProduct = ?";
	
	public static Product findById(int productId) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringID);
			findStatement.setLong(1, productId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			String category = rs.getString("category");
			
			toReturn = new Product(productId, name, price,stock,category);
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
