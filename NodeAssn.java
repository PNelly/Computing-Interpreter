/* 
	Interpreter Assignment 1
	cs354 Patrick Doudy September 2017
*/

// Node representing an asignment statement
// Assn is a terminal

public class NodeAssn extends NodeStmt {

    private String id;
    private NodeExpr expr;

    public NodeAssn(String id, NodeExpr expr) {

		this.id=id;
		this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {

		return env.put(id, expr.eval(env));
    }

}
