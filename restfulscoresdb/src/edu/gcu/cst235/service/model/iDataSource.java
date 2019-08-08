package edu.gcu.cst235.service.model;

import edu.gcu.cst235.service.ScoreBean;

public interface iDataSource {
	/**
	 * Closes the data connection
	 */
	void close();

	/**
	 * UPDATE a ScoreBean
	 */
	boolean updateScoreBean(ScoreBean score);

	/**
	 * READ and return a ScoreBean
	 */
	ScoreBean getScoreBean();
}
