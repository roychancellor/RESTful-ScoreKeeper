/**
 * Rock-paper-scissors (Roshambo)
 * Uses a web API to keep track of scores
 */

/**
 * Player clicks a button in index.html which calls this method
 * Method generates a random response (1-3), then determines if
 * the player has won, lost, or tied and posts the result to the API.
 * The method also calls the API to get the cumulative scores from
 * the database
 */
playRoshambo = function(clientGesture) {
	//Server picks randomly
	serverVal = parseInt((Math.random() * 3) + 1);
	
	//Get the current scores
	getScores();
	
	//Determine the winner
	switch(serverVal) {
	case 1:
		serverGesture = 'rock';
		switch(clientGesture) {
			case 'rock':
				//result = "tie";
				postTie();
				break;
			case 'paper':
				//result = 'win';
				postWin();
				break;
			default:
				//result = 'lose';
				postLoss();
		}
		break;
	case 2:
		serverGesture = 'paper';
		switch(clientGesture) {
			case 'rock':
				//result = 'lose';
				postLoss();
				break;
			case 'paper':
				//result = 'tie';
				postTie();
				break;
			default:
				//result = 'win';
				postWin();
		}
		break;
	case 3:
		serverGesture = 'scissors';
		switch(clientGesture) {
			case 'rock':
				//result = 'win';
				postWin();
				break;
			case 'paper':
				//result = 'lose';
				postLoss();
				break;
			default:
				//result = 'tie';
				postTie();
		}
		break;
	}
	
	//Update the scores
	getScores();

} //end playRoshambo

//POST a win through the scores API - don't use the returned data
function postWin() {
	// Get the element where we will place our scores
	const para = document.getElementById('winblock');
    fetch('http://localhost:8080/restfulscores/scorebean/wins',
    	{method: 'POST'});
}
//POST a loss through the scores API - don't use the returned data
function postLoss() {
    fetch('http://localhost:8080/restfulscores/scorebean/losses',
        {method: 'POST'});
}
//POST a tie through the scores API - don't use the returned data
function postTie() {
    fetch('http://localhost:8080/restfulscores/scorebean/ties',
        {method: 'POST'});
}

//GET the scores from the scores API
function getScores() {
	// Get the element where we will place our scores
	const para = document.getElementById('scores');
	fetch('http://localhost:8080/restfulscores/scorebean')
	 .then(function (response) {
	     return response.json();
	 })
	 .then(function (data) {
	      para.innerHTML = '<h3>SCORES</h3><p><strong>Wins:</strong> ' + data.wins
	      + '<p><strong>Losses:</strong> ' + data.losses
	      + '<p><strong>Ties:</strong> ' + data.ties;
	 })
	 .catch(function (err) {
	     console.log('error: ' + err);
	 });
}