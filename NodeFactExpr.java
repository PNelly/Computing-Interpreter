/* 
    Interpreter Assignment 2
    cs354 Patrick Doudy September 2017
*/

// Child class of the factor node that defines
// a multiplication factor as an expression

public class NodeFactExpr extends NodeFact {

    private NodeUnNeg unneg;
    private NodeExpr expr;

    public NodeFactExpr(NodeUnNeg unneg, NodeExpr expr) {
        this.unneg = unneg;
		this.expr=expr;
    }

    // evaluate the expression contained by the factor so the 
    // result can be used in multiplication
    public double eval(Environment env) throws EvalException {
    	
        double factEval = expr.eval(env);

        if(unneg!=null)
            return unneg.op(factEval);
		else
            return factEval;
    }

}
