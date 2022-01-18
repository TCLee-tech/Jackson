package com.sgpools.eventresult;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventresultApplication {

//create object mapper instance
static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
	
 
	try{

	//read JSON file
	File newState = new File("eventresult.json");	
	JsonNode root = mapper.readTree(newState);

	//(a)	
	// print JSON nodes
	Integer totalGoalsHandicapValue = root.get("eventResult").get(0).get("markets").get(2).get("handicapValue").asInt();
	//System.out.println(totalGoalsHandicapValue); //checking

	Integer home = root.get("eventResult").get(0).get("scores").get(0).get("home").asInt();	
	Integer away = root.get("eventResult").get(0).get("scores").get(0).get("home").asInt();

	String totalGoalsSelectionCodeH = root.get("eventResult").get(0).get("markets").get(2).get("selections").get(1).get("selectionCode").asText();
	String totalGoalsSelectionCodeL = root.get("eventResult").get(0).get("markets").get(2).get("selections").get(0).get("selectionCode").asText();

	if ((home + away) > totalGoalsHandicapValue) {
		System.out.println("Total Goals Over/Under market, selection code " + totalGoalsSelectionCodeH + "win");
		System.out.println("Total Goals Over/Under market, selection code " + totalGoalsSelectionCodeL + "loss");
	}

	if ((home + away) < totalGoalsHandicapValue) {
		System.out.println("Total Goals Over/Under market, selection code " + totalGoalsSelectionCodeH + " loss");
		System.out.println("Total Goals Over/Under market, selection code " + totalGoalsSelectionCodeL + " win");
	}

	//(b)
	//market: Half Goal. double to handle decimal
	double halfGoalHandicapValue = root.get("eventResult").get(0).get("markets").get(1).get("handicapValue").asDouble();
	
	String halfGoalSelectionCodeH = root.get("eventResult").get(0).get("markets").get(1).get("selections").get(1).get("selectionCode").asText();
	String halfGoalSelectionCodeA = root.get("eventResult").get(0).get("markets").get(1).get("selections").get(0).get("selectionCode").asText();
	

	if ((home - away + halfGoalHandicapValue) > 0) {
		System.out.println("Half Goal market, selection code " + halfGoalSelectionCodeH + " win");
		System.out.println("Half Goal market, selection code " + halfGoalSelectionCodeA + " loss");
	}
	if ((home - away + halfGoalHandicapValue) < 0) {
		System.out.println("Half Goal market, selection code " + halfGoalSelectionCodeH + " loss");
		System.out.println("Half Goal market, selection code " + halfGoalSelectionCodeA + " win");
	}
	if ((home - away + halfGoalHandicapValue) == 0) {
		System.out.println("Half Goal market, selection code " + halfGoalSelectionCodeH + " loss");
		System.out.println("Half Goal market, selection code " + halfGoalSelectionCodeA + " loss");
		System.out.println("Total Goals Over/Under market, selection code " + totalGoalsSelectionCodeL + " win");
		// ??? Half Goal market has no selectioncode=L 
	}

	//(c)
	//market: Asian Handicap
	String asianHandicapSelectionCodeH = root.get("eventResult").get(0).get("markets").get(0).get("selections").get(1).get("selectionCode").asText();
	String asianHandicapSelectionCodeA = root.get("eventResult").get(0).get("markets").get(0).get("selections").get(0).get("selectionCode").asText();

	double asianHandicapValue = root.get("eventResult").get(0).get("markets").get(0).get("handicapValue").asDouble();
	double value = asianHandicapValue / 4;
	String asianHandicap = Double.toString(value);
	
	//check for x.x5 digit pattern
	String regex = "^-?\\d*\\.\\d5$";

	//pattern matching
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(asianHandicap); 
	boolean matches = matcher.matches();
	
	//split Asian Handicap value if end in ".25" or ".75"
	if (matches) {
	double firstPartAsianHandicap = value - 0.25;
	double secondPartAsianHandicap = value + 0.25;

	if ((home - away + firstPartAsianHandicap) > 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeH + " win");
	}

	if ((home - away + firstPartAsianHandicap) < 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeA + " win");
	}

	if ((home - away + firstPartAsianHandicap) == 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeA + " draw");
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeH + " draw");
	}

	if ((home - away + secondPartAsianHandicap) > 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeH + " win");
	}

	if ((home - away + secondPartAsianHandicap) < 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeA + " win");
	}

	if ((home - away + secondPartAsianHandicap) == 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeA + " draw");
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeH + " draw");
	}
	}

	//Asian Handicap ends with "0" or ".5"
	if (!matches) {

	if ((home - away + value) > 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeH + " win");
	}

	if ((home - away + value) < 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeA + " win");
	}

	if ((home - away + value) == 0) {
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeA + " draw");
		System.out.println("Asian Handicap market, selection code " + asianHandicapSelectionCodeH + " draw");
	}
}

	} catch (Exception exception) {
		exception.printStackTrace();
	}	
}
}

