/* 
	Interpreter Assignment 1
	cs354 Patrick Doudy September 2017
*/

// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

public class Interpreter {

    public static void main(String[] args) {

		Parser parser=new Parser();
		Environment env=new Environment();

		for(String program : args)
		    try {

		    	// This call will drive the recursive-descent parsing
		    	// process, which creates the parse tree nodes and their
		    	// relationships to other nodes, then evaluate method of 
		    	// the root node is called, triggering
		    	// recursive evaluation of all the nodes
				System.out.println(parser.parse(program).eval(env));

		    } catch (SyntaxException e) {
				System.err.println(e);
		    } catch (EvalException e) {
				System.err.println(e);
		    }

    }

}
