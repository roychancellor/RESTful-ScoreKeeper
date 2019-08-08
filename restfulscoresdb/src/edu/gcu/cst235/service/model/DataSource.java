package edu.gcu.cst235.service.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.gcu.cst235.service.ScoreBean;

public class DataSource implements iDataSource {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private boolean connectedToDb;
	private String dbURL = "jdbc:mysql://localhost:3306";
	private String dbUser = "root";
	private String dbPassword = "root";

	public DataSource() {
		try {
			//Something required to run in TomEE server
			//THE JDBC JAR FILE MUST BE ADDED TO DEPLOYMENT PATH IN PROJECT PROPERTIES
			//OR IT WILL THROW A CLASSNOTFOUND EXCEPTION
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connect to the database
			this.conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			
			//Create a Statement object
			try {
				this.stmt = conn.createStatement();
				this.connectedToDb = true;
			}
			catch(SQLException e) {
				e.printStackTrace();
				this.connectedToDb = false;
			}				
		}
		catch(SQLException e) {
			e.printStackTrace();
			this.connectedToDb = false;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		try {
			this.conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ScoreBean getScoreBean() {
		//Prepare the SQL statement
		String sql = "SELECT * FROM rpsscores.scores WHERE rpsscores.scores.gameId=1";
		if(this.connectedToDb) {
			try {
				//Execute SQL statement and get a result set
				this.rs = stmt.executeQuery(sql);
				
				//Process the result set
				ScoreBean scores = new ScoreBean();
				while(this.rs.next()) {
					//Read the fields in the current record and store in Contact object
					scores.setWins(rs.getInt("wins"));
					scores.setLosses(rs.getInt("losses"));
					scores.setTies(rs.getInt("ties"));
				}
				
				return scores;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean updateScoreBean(ScoreBean score) {
		String sql = "UPDATE rpsscores.scores SET "
			+ "wins = " + score.getWins()
			+ ", losses = " + score.getLosses()
			+ ", ties = " + score.getTies()
			+ " WHERE gameId = 1";
		if(connectedToDb) {
			try {
				stmt.execute(sql);			
				return true;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
