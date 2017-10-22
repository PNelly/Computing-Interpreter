/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy October 2017
*/

// Parse Tree Node Representing an If Statement

public class NodeIf extends NodeStmt {

	private static final double TRUE  = Interpreter.TRUE;
	private static final double FALSE = Interpreter.FALSE;

	private NodeBoolExpr bool;
	private NodeStmt stmtA;
	private NodeStmt stmtB; // for 'else' block

	public NodeIf(NodeBoolExpr bool, NodeStmt stmtA, NodeStmt stmtB){

		this.bool 	= bool;
		this.stmtA	= stmtA;
		this.stmtB	= stmtB;
	}

	public double eval(Environment env) throws EvalException {

		if(bool.eval(env) == TRUE) {
			return stmtA.eval(env);
		} else {
			if(stmtB!=null)
				return stmtB.eval(env);
		}

		return FALSE; 
	}

}