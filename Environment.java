/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy October 2017
*/

// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

import java.util.HashMap;

public class Environment {

	private HashMap<String,Double> map;

	public Environment(){

		map = new HashMap<String,Double>();
	}

    public Double put(String var, Double val) { 
    	map.put(var, val);
    	return val; 
    }

    public Double get(int pos, String var) throws EvalException {
    	return map.get(var); 
    }

}
