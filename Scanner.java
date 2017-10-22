/* 
    Interpreter Assignment 1
    cs354 Patrick Doudy September 2017
*/

// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

public class Scanner {

    private String program;	// source program being interpreted
    private int pos;		// index of next char in program
    private Token token;	// last/current scanned token

    // sets of various characters and lexemes
    private Set<String> whitespace=new HashSet<String>();
    private Set<String> digits=new HashSet<String>();
    private Set<String> letters=new HashSet<String>();
    private Set<String> legits=new HashSet<String>();
    private Set<String> keywords=new HashSet<String>();
    private Set<String> operators=new HashSet<String>();
    private Set<String> comments=new HashSet<String>();

    // initializers for previous sets

    private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
		    s.add(c+"");
    }    

    // Initialize WhiteSpace Characters
    private void initWhitespace(Set<String> s) {

		s.add(" ");
		s.add("\n");
		s.add("\t");
    }

    // Initialize Digit Characters
    private void initDigits(Set<String> s) {

		fill(s,'0','9');
    }

    // Initialize Letter Characters
    private void initLetters(Set<String> s) {

		fill(s,'A','Z');
		fill(s,'a','z');
    }

    // Initialize Digit/Letter Characters
    private void initLegits(Set<String> s) {

		s.addAll(letters);
		s.addAll(digits);
    }

    // Initialize Operators
    private void initOperators(Set<String> s) {

		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
        s.add("<");
        s.add(">");
        s.add("<=");
        s.add(">=");
        s.add("<>");
        s.add("==");
    }

    // Initialize Keywords
    private void initKeywords(Set<String> s) {

    }

    // Initialize Comments
    private void initComments(Set<String> s) {

    	s.add("$");
    }

    // constructor:
    //   - squirrel-away source program
    //   - initialize sets
    public Scanner(String program) {

		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
    }

    // handy string-processing methods

    // "done" if the scanner position has
    // advanced past the program length
    public boolean done() {

		return pos>=program.length();
    }

    // increment scanner position for each
    // character falling within the set s
    private void many(Set<String> s) {

		while (!done() && s.contains(program.charAt(pos)+""))
		    pos++;
    }
    
    // increment the scanner position just
    // past the character c
    private void past(char c) {

		while (!done() && c!=program.charAt(pos))
		    pos++;
		if (!done() && c==program.charAt(pos))
		    pos++;
    }

    // scan various kinds of lexeme

    // Get Next Number Token
    // get a string of digits and create a token
    // from it of type "num" 
    private void nextNumber() {

		int old=pos;
		many(digits);
		token=new Token("num",program.substring(old,pos));
    }

    // Get Next Keyword Token
    // Get a string composed of a letter follwed by letters
    // or digits, and create a token of type "keyword" if
    // that string is contained in the keyword set. Otherwise
    // create a new token of type "id"
    private void nextKwId() {

		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
    }

    // Get Next Operator Token
    // increment the scanner two positions and check for a
    // two character operator. If none, go back and check for
    // a one character operator. 
    private void nextOp() {

		int old=pos;
		pos=old+2;

		if (!done()) {

		    String lexeme=program.substring(old,pos);

		    if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
		    }
		}

		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
    }

    // Consume Next Comment
    // increment the scanner past the comment without
    // setting the token variable
    private void nextComment() {

    	many(whitespace);
    	many(legits);
    }

    // Get Next Token
    // This method determines the kind of the next token (e.g., "id"),
    // and calls a method to scan that token's lexeme (e.g., "foo").
    public boolean next() {

		if (done())
		    return false;

		many(whitespace); // advance to beginning of next token
		String c=program.charAt(pos)+"";

		if (digits.contains(c))
		    nextNumber();
		else if (letters.contains(c))
		    nextKwId();
		else if (operators.contains(c))
		    nextOp();
		else if (comments.contains(c))
			nextComment();
		else {
			// character not contained in any set
		    System.err.println("illegal character at position "+pos);
		    pos++;
		    return next();
		}

		return true;
    }

    // This method scans the next lexeme,
    // if the current token is the expected token.
    public void match(Token t) throws SyntaxException {

		if (!t.equals(curr()))
		    throw new SyntaxException(pos,t,curr());

		next();
    }

    // return most recently scanned token
    public Token curr() throws SyntaxException {

		if (token==null)
		    throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));

		return token;
    }

    // return scanner position
    public int pos() { 

    	return pos; 
    }

    // for unit testing
    public static void main(String[] args) {

		try {
		    Scanner scanner=new Scanner(args[0]);
		    while (scanner.next())
			System.out.println(scanner.curr());
		} catch (SyntaxException e) {
		    System.err.println(e);
		}
    }

}
