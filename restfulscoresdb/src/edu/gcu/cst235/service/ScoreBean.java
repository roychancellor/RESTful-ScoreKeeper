package edu.gcu.cst235.service;

/**
 * A JavaBean is a POJO that is serializable,
 * has a no-argument constructor, and allows access
 * to properties using getter and setter methods that
 * follow a simple naming convention
 *
 */
public class ScoreBean {
	private int wins;
	private int losses;
	private int ties;
	
	/**
	 * Constructor for a ScoreBean that initializes scores to zero
	 */
	public ScoreBean() {
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
	}

	/**
	 * getter for wins
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * setter for wins
	 * @param wins the number of wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * getter for losses
	 * @return losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * setter for losses
	 * @param losses the number of losses
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * getter for ties
	 * @return ties
	 */
	public int getTies() {
		return ties;
	}

	/**
	 * setter for ties
	 * @param ties the number of ties
	 */
	public void setTies(int ties) {
		this.ties = ties;
	}
}
