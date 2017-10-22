/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy October 2017
*/

// Parse tree node for unary negation

public class NodeUnNeg extends Node {

    public NodeUnNeg(int pos) {

		this.pos=pos;
    }

    // evaluate unary negation
    public Double op(Double operand) {

		return -operand;
    }

}