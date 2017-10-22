/* 
	Interpreter Assignment 2
	cs354 Patrick Doudy September 2017
*/

// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

public class Parser {

    private Scanner scanner;

    // Wrapper methods that interact with scanner
    private void match(String s) throws SyntaxException {

		scanner.match(new Token(s));
    }

    private Token curr() throws SyntaxException {

		return scanner.curr();
    }

    private int pos() {

		return scanner.pos();
    }

    // Parse Tree Node Methods

    // Prase a relational operator
    private NodeRelop parseRelop() throws SyntaxException {

    	if(curr().equals(new Token(">"))) {
    		match(">");
    		return new NodeRelop(pos(),">");
    	}

    	if(curr().equals(new Token("<"))) {
    		match("<");
    		return new NodeRelop(pos(),"<");
    	}

    	if(curr().equals(new Token(">="))) {
    		match(">=");
    		return new NodeRelop(pos(),">=");
    	}

    	if(curr().equals(new Token("<="))) {
    		match("<=");
    		return new NodeRelop(pos(),"<=");
    	}

    	if(curr().equals(new Token("<>"))) {
    		match("<>");
    		return new NodeRelop(pos(),"<>");
    	}

    	if(curr().equals(new Token("=="))) {
    		match("==");
    		return new NodeRelop(pos(),"==");
    	}

    	return null;
    }

    // Parse a unary negation
    private NodeUnNeg parseUnNeg() throws SyntaxException {

    	if(curr().equals(new Token("-"))){
    		match("-");
    		return new NodeUnNeg(pos());
    	}

    	return null;
    }

    // Parse a mulop
    // adds a multiplication operation to the parse tree
    private NodeMulop parseMulop() throws SyntaxException {

		if (curr().equals(new Token("*"))) {
		    match("*");
		    return new NodeMulop(pos(),"*");
		}

		if (curr().equals(new Token("/"))) {
		    match("/");
		    return new NodeMulop(pos(),"/");
		}

		return null;
    }

    // Parse An Addop
    // Adds an addition operation to the parse tree
    private NodeAddop parseAddop() throws SyntaxException {

		if (curr().equals(new Token("+"))) {
		    match("+");
		    return new NodeAddop(pos(),"+");
		}

		if (curr().equals(new Token("-"))) {
		    match("-");
		    return new NodeAddop(pos(),"-");
		}

		return null;
    }

    // Parse a boolean expression
    private NodeBoolExpr parseBoolExpr() throws SyntaxException {

    	NodeExpr 	exprA=parseExpr();
    	NodeRelop	relop=parseRelop();
    	NodeExpr 	exprB=parseExpr();

    	return new NodeBoolExpr(exprA, relop, exprB);
    }

    // Parse a factor
    // parses a bracketed expression, or an identifier,
    // or a number
    private NodeFact parseFact() throws SyntaxException {

    	NodeUnNeg unneg=parseUnNeg();

		if (curr().equals(new Token("("))) {
		    match("(");
		    NodeExpr expr=parseExpr();
		    match(")");
		    return new NodeFactExpr(unneg, expr);
		}

		if (curr().equals(new Token("id"))) {
		    Token id=curr();
		    match("id");
		    return new NodeFactId(unneg, pos(),id.lex());
		}

		Token num=curr();
		match("num");

		return new NodeFactNum(unneg, num.lex());
    }

    // Parse a term
    // parses a factor, then parses a mulop, then
    // recursively parses another term
    private NodeTerm parseTerm() throws SyntaxException {

		NodeFact fact=parseFact();
		NodeMulop mulop=parseMulop();

		if (mulop==null)
		    return new NodeTerm(fact,null,null);

		NodeTerm term=parseTerm();
		term.append(new NodeTerm(fact,mulop,null));

		return term;
    }

    // Parse an expression
    // parse a term, then parse an addop,
    // then recursively parse another expression
    private NodeExpr parseExpr() throws SyntaxException {

		NodeTerm term=parseTerm();
		NodeAddop addop=parseAddop();

		if (addop==null)
		    return new NodeExpr(term,null,null);

		NodeExpr expr=parseExpr();
		expr.append(new NodeExpr(term,addop,null));

		return expr;
    }

    // Parse Assignment Statement
    // seek out an identifier followed by the assignment
    // operator, then parse the RHS expression
    private NodeAssn parseAssn() throws SyntaxException {

		Token id=curr();
		match("id");
		match("=");
		NodeExpr expr=parseExpr();
		NodeAssn assn=new NodeAssn(id.lex(),expr);

		return assn;
    }

    // Parse a Statement (only supports assign stmt)
    // parse an assigment statement and seek
    // out the terminating semicolon
    private NodeStmt parseStmt() throws SyntaxException {

    	if (curr().equals(new Token("rd"))) {
		    match("rd");
		    Token id=curr();
		    match("id");
		    NodeRead read = new NodeRead(id.lex());
		    return read;
		}

		if (curr().equals(new Token("wr"))) {
			match("wr");
			NodeExpr expr = parseExpr();
			NodeWrite write = new NodeWrite(expr);
			return write;
		}

		if (curr().equals(new Token("if"))) {

			match("if");
			NodeBoolExpr bool = parseBoolExpr();
			match("then");
			NodeStmt stmtA = parseStmt();
			NodeStmt stmtB = null;

			if(curr().equals(new Token("else"))){
				match("else");
				stmtB = parseStmt();
			}

			return new NodeIf(bool, stmtA, stmtB);
		}

		return parseAssn();
    }

    // Top Level Method that begins descending program parsing
    // Accepts the program as a string
    // and parses it as a statement
    public Node parse(String program) throws SyntaxException {

		scanner=new Scanner(program);
		scanner.next();

		return parseStmt();
    }

}
