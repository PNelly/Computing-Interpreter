/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy September 2017
*/

// Node representing an output write operation

public class NodeWrite extends NodeStmt {

	private NodeExpr expr;

	public NodeWrite(NodeExpr expr){

		this.expr=expr;
	}

	public double eval(Environment env) throws EvalException {

		double result = expr.eval(env);
		System.out.println(result);

		return result;
	}

}