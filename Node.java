/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy October 2017
*/

// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated.

public abstract class Node {

    protected int pos=0;

    public double eval(Environment env) throws EvalException {
    	
		throw new EvalException(pos,"cannot eval() node!");
    }

}
