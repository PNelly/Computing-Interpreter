/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy September 2017
*/

// Node representing an input read operation

import java.util.Scanner;

public class NodeRead extends NodeStmt {

	// Class Variables
	static Scanner scanner = new Scanner(System.in);

	// Instance Variables
	private String id;

	public NodeRead(String id){
		this.id=id;
	}

	public double eval(Environment env) throws EvalException {

		return env.put(id, scanner.nextDouble());
	}

}