/* 
	Interpreter Assignment 1
	cs354 Patrick Doudy September 2017
*/

// Node representing an Addop
// Addop is a terminal

public class NodeAddop extends Node {

    private String addop;

    // Addop Node Constructor
    public NodeAddop(int pos, String addop) {

		this.pos=pos;
		this.addop=addop;
    }

    // Evaluate Addition Operation
    public double op(double o1, double o2) throws EvalException {

		if (addop.equals("+"))
		    return o1+o2;
		if (addop.equals("-"))
		    return o1-o2;

		throw new EvalException(pos,"bogus addop: "+addop);
    }

}
