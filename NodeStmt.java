/* 
	Interpreter Assignment 1
	cs354 Patrick Doudy September 2017
*/

public class NodeStmt extends Node {

    private NodeAssn assn;

    public NodeStmt(NodeAssn assn) {

		this.assn=assn;
    }

    public double eval(Environment env) throws EvalException {
    	
		return assn.eval(env);
    }

}
