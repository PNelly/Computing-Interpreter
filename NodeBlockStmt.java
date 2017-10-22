/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy September 2017
*/

// Node representing a block of statements as 
// a SINGLE statement

public class NodeBlockStmt extends NodeStmt {

	private NodeBlock block;

	public NodeBlockStmt(NodeBlock block){

		this.block = block;
	}

	public double eval(Environment env) throws EvalException {

		return block.eval(env);
	}
}