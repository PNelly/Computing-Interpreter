/* 
    Interpreter Assignment 2
    cs354 Patrick Doudy October 2017
*/

// Child of factor node defining the factor
// as a number to be used in muliplication

public class NodeFactNum extends NodeFact {

    private NodeUnNeg unneg;
    private String num;

    public NodeFactNum(NodeUnNeg unneg, String num) {

        this.unneg=unneg;
		this.num=num;
    }

    // evaluate by returning the number to be used in
    // mulitiplication operation
    public double eval(Environment env) throws EvalException {
    	
        double factEval = Double.parseDouble(num);

        if(unneg!=null)
            return unneg.op(factEval);
		else
            return factEval;
    }

}
