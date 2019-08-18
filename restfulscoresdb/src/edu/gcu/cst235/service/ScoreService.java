/**
 * Web service that keeps track of wins, losses, and ties for a rock-paper-scissors game
 */
package edu.gcu.cst235.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.gcu.cst235.service.model.DataSource;

@ApplicationPath("/")
public class ScoreService extends Application {
	
	/**
	 * Required method to deploy this class as a web service
	 */
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(ScoreService.class)); 
	}
	
	/**
	 * Gets a ScoreBean from the database, parses it as a JSON string
	 * and returns the JSON string to the caller
	 * @return a JSON string with wins, losses, and ties for the Rock-Paper-Scissors game underway
	 */
	@GET
	@Path("/scorebean")
	@Produces("application/json")
    public String getScoreBeanFromDatabase() {
		try {
			//Make a DataSource object to access the database connection methods
			DataSource ds = new DataSource();
			//Make a Jackson ObjectMapper
			ObjectMapper mapper = new ObjectMapper();
			String jsonString;
			//Convert the POJO (java bean) to a JSON string
			//Uses Jackson 2.0 to parse out a serializable POJO
			jsonString = mapper.writeValueAsString(ds.dbGetScoreBean());
			ds.close();
			return jsonString;
		}
		catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
    
	//Methods to increment scores and UPDATE the database
	
	/**
	 * increases the wins in the RPS game
	 * @return the wins increased by 1
	 */
	@POST @Path("/scorebean/wins")@Produces("application/json")
	public String increaseWins() {
		return "{\"wins\" : \"" + increaseScore('w') + "\"}";
	}
	     
	/**
	 * increases the losses in the RPS game
	 * @return the losses increased by 1
	 */
	@POST @Path("/scorebean/losses")@Produces("application/json")         
	public String increaseLosses() {
		return "{\"losses\" : \"" + increaseScore('l') + "\"}";
	}
	
	/**
	 * increases the ties in the RPS game
	 * @return the ties increased by 1
	 */
	@POST @Path("/scorebean/ties")@Produces("application/json")      
	public String increaseTies() {
		return "{\"ties\" : \"" + increaseScore('t') + "\"}";
	}
	
	/**
	 * gets a ScoreBean from the database and increases the specific score as requested
	 * @param scoreToIncrease a character 'w', 'l', or 't' corresponding to wins, losses, ties
	 * @return the score incremented by one
	 */
	private int increaseScore(char scoreToIncrease) {
		//Get a ScoreBean from the database
		ScoreBean currentScore = getScoreBeanFromDb();
		int incrementedScore;
		//Increment the score
		switch(Character.toUpperCase(scoreToIncrease)) {
			case 'W':
				incrementedScore = currentScore.getWins() + 1;
				currentScore.setWins(incrementedScore);
				break;
			case 'L':
				incrementedScore = currentScore.getLosses() + 1;
				currentScore.setLosses(incrementedScore);
				break;
			case 'T':
				incrementedScore = currentScore.getTies() + 1;
				currentScore.setTies(incrementedScore);
				break;
			default:
				return -1;
		}
		//Write the ScoreBean back to the database to UPDATE the scores
		writeScoreBeanToDb(currentScore);
		
		//Return the incremented wins to the caller
		return incrementedScore;
	}
	
	/**
	 * gets a score bean from the database
	 * @return ScoreBean object
	 */
	private ScoreBean getScoreBeanFromDb() {
		DataSource ds = new DataSource();
		ScoreBean currentScore = ds.dbGetScoreBean();
		ds.close();
		return currentScore;
	}
	
	/**
	 * writes a ScoreBean object to the database
	 * @param bean
	 */
	private void writeScoreBeanToDb(ScoreBean bean) {
		DataSource ds = new DataSource();
		ds.dbUpdateScoreBean(bean);
		ds.close();
	}
}