/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy October 2017
*/

// Node representing a term
// term -> fact mulop term

public class NodeTerm extends Node {

    private NodeFact fact;
    private NodeMulop mulop;
    private NodeTerm term;

    public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {

		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
    }

    // recursively append a term to the end of
    // a chain of terms
    public void append(NodeTerm term) {

		if (this.term==null) {
		    this.mulop=term.mulop;
		    this.term=term;
		    term.mulop=null;
		} else
		    this.term.append(term);
    }

    // recursively evaluate the term
    public double eval(Environment env) throws EvalException {

		return term==null
		    ? fact.eval(env)
		    : mulop.op(term.eval(env),fact.eval(env));
    }

}
