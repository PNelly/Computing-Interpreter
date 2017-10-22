/* 
	Interpreter Assignment 1
	cs354 Patrick Doudy October 2017
*/

// Parse tree node for multiplication

public class NodeMulop extends Node {

    private String mulop;

    public NodeMulop(int pos, String mulop) {

		this.pos=pos;
		this.mulop=mulop;
    }

    // evaluate multiplication operation
    public Double op(Double o1, Double o2) throws EvalException {

		if (mulop.equals("*"))
		    return o1*o2;
		if (mulop.equals("/"))
		    return o1/o2;

		throw new EvalException(pos,"bogus mulop: "+mulop);
    }

}
