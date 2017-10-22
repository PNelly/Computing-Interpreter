/* 
    Interpreter Assignment 2
    cs354 Patrick Doudy September 2017
*/

// Node Representing a Boolean Expression
// BoolExpr -> Expr Relop Expr


public class NodeBoolExpr extends Node {

    private NodeExpr exprA;
    private NodeRelop relop;
    private NodeExpr exprB;

    public NodeBoolExpr(NodeExpr exprA, NodeRelop relop, NodeExpr exprB) {

        this.exprA=exprA;
        this.relop=relop;
        this.exprB=exprB;
    }

    
    public double eval(Environment env) throws EvalException {

		return relop.op(exprA.eval(env),exprB.eval(env));
    	
    }

}
