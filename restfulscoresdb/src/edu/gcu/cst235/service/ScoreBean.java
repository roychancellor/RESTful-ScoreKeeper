package edu.gcu.cst235.service;

import java.util.ArrayList;
import java.util.List;

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
	private List<String> restActions;
	
	public ScoreBean() {
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
		restActions = new ArrayList<String>();
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getTies() {
		return ties;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}

	public List<String> getRestActions() {
		return restActions;
	}

	public void setRestActions(List<String> restActions) {
		this.restActions = restActions;
	}
}
