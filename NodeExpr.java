/* 
    Interpreter Assignment 2
    cs354 Patrick Doudy September 2017
*/

// Node Representing and Expression
// Expr -> term addop expr


public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {

		this.term=term;
		this.addop=addop;
		this.expr=expr;
    }

    // recursively add an expr to the end of a chain
    // of exprs
    public void append(NodeExpr expr) {

		if (this.expr==null) {
		    this.addop=expr.addop;
		    this.expr=expr;
		    expr.addop=null;
		} else
		    this.expr.append(expr);

    }

    // recursively evaluate nested expressions
    public double eval(Environment env) throws EvalException {

		return expr==null
	    	? term.eval(env)
	    	: addop.op(expr.eval(env), term.eval(env));
    	
    }

}
