// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

    private String token;
    private String lexeme;

    // constructor for tokens that have both
    // a token id and a lexeme string.
    // e.g., "foo" is the lexeme for an "id" token
    public Token(String token, String lexeme) {

    	this.token=token;
    	this.lexeme=lexeme;
    }

    // constructor for tokens that do not
    // need an associated lexeme, such as an
    // operator. e.g., "+"
    public Token(String token) {

	   this(token,token);
    }

    // token string get method
    public String tok() { 

        return token; 
    } 

    // lexeme string get method
    public String lex() {

        return lexeme; 
    }

    // use string comparison to build
    // .equals() method
    public boolean equals(Token t) {

	   return token.equals(t.token);
    }

    public String toString() {

	   return "<"+tok()+","+lex()+">";
    }

}
