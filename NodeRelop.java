/* 
	Interpreter Assignment 1
	cs354 Patrick Doudy September 2017
*/

// Node representing a Relational Operator
// Relop is a terminal

public class NodeRelop extends Node {

    private String relop;

    // Addop Node Constructor
    public NodeRelop(int pos, String relop) {

		this.pos=pos;
		this.relop=relop;
    }

    // Evaluate Addition Operation
    public double op(double o1, double o2) throws EvalException {

		if (relop.equals(">"))
			return ( (o1>o2) ? 1.0 : 0.0 );

		if (relop.equals("<"))
			return ( (o1<o2) ? 1.0 : 0.0 );

		if (relop.equals(">="))
			return ( (o1>=o2) ? 1.0 : 0.0 );

		if (relop.equals("<="))
			return ( (o1<=o2) ? 1.0 : 0.0 );

		if (relop.equals("<>"))
			return ( (o1!=o2) ? 1.0 : 0.0 );

		if (relop.equals("=="))
			return ( (o1==o2) ? 1.0 : 0.0 );

		throw new EvalException(pos,"bogus relop: "+relop);
    }

}
