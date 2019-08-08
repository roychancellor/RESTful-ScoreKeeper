package edu.gcu.cst235.service.tester;

import edu.gcu.cst235.service.ScoreService;

public class Tester {

	public static void main(String[] args) {
		ScoreService ss = new ScoreService();
		System.out.println(ss.getScoreBeanFromDatabase());
	}

}
