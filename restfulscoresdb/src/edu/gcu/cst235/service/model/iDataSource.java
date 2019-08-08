package edu.gcu.cst235.service.model;

import edu.gcu.cst235.service.ScoreBean;

public interface iDataSource {
	/**
	 * Closes the data connection
	 */
	void close();

	/**
	 * UPDATE a ScoreBean
	 * @param score a ScoreBean containing wins, losses, and ties
	 * @return true if bean successfully retrieved
	 */
	boolean updateScoreBean(ScoreBean score);

	/**
	 * READ and return a ScoreBean
	 * @return a ScoreBean object
	 */
	ScoreBean getScoreBean();
}
