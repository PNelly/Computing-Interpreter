/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy September 2017
*/

// Node representing a block of statements

public class NodeBlock extends Node {

	private NodeStmt stmt;
	private NodeBlock block;

	public NodeBlock(NodeStmt stmt, NodeBlock block){

		this.stmt  = stmt;
		this.block = block;
	}

	public double eval(Environment env) throws EvalException {

		if(block==null)
			return stmt.eval(env);
		else{
			stmt.eval(env);
			return block.eval(env);
		}

	}

}