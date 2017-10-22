/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy September 2017
*/

// Node representing a while loop

public class NodeWhile extends NodeStmt {

	private static final double TRUE  = Interpreter.TRUE;
	private static final double FALSE = Interpreter.FALSE;

	private NodeBoolExpr bool;
	private NodeStmt stmt;

	public NodeWhile(NodeBoolExpr bool, NodeStmt stmt){

		this.bool=bool;
		this.stmt=stmt;
	}

	public double eval(Environment env) throws EvalException {

		double result = FALSE;

		while(bool.eval(env) == TRUE)
			result = stmt.eval(env);

		return result;
	}

}