/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy October 2017
*/

// Child Class of Factor Node defining
// an identifier as a factor

public class NodeFactId extends NodeFact {

	private NodeUnNeg unneg;
    private String id;

    public NodeFactId(NodeUnNeg unneg, int pos, String id) {

    	this.unneg = unneg;
		this.pos=pos;
		this.id=id;
    }

    public double eval(Environment env) throws EvalException {

    	double factEval = env.get(pos,id);

    	if(unneg!=null)
    		return unneg.op(factEval);
		else
			return factEval;
    }

}
