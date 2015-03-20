package markdown;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


public class Main {
 
    public static void main(String[] args) {
        parse("Markdown _italicizes_ using underscores.");
    }

    /**
     */
     public static void parse(String string) {
        // Create a stream of characters from the string
        CharStream stream = new ANTLRInputStream(string);

        // Make a parser
        MarkdownParser parser = makeParser(stream);
        
        // Generate the parse tree using the starter rule.
        // root is the starter rule for this grammar.
        // Other grammars may have different names for the starter rule.
        ParseTree tree = parser.markdown();
        
        // *** debugging option #1: print the tree to the console
//        System.err.println(tree.toStringTree(parser));

        // *** debugging option #2: show the tree in a window
        RuleContext rootContext = (RuleContext) tree;
        rootContext.inspect(parser);
        
        // *** debugging option #3: walk the tree with a listener
//        new ParseTreeWalker().walk(new PrintEverything(), tree);
        

//        // Construct the board by walking over the parse tree with a listener.
//        ParseTreeWalker walker = new ParseTreeWalker();
//        MakeBoard listener = new MakeBoard();
//        walker.walk(listener, tree);
//        
//        // return the value that the listener created
//        return listener.bombs;
    }

     
     /**
      * Make a parser that is ready to parse a stream of characters.
      * @param stream stream of characters
      * @return a parser that is ready to parse the stream.
      * To start parsing, the client should call a method on the returned parser corresponding to
      * the start rule of the grammar, e.g. parser.html() or parser.root() or
      * whatever it happens to be.
      * During parsing, if the parser encounters a syntax error,
      * it will throw a RuntimeException.
      * 
      */
     private static MarkdownParser makeParser(CharStream stream) {
         // Make a lexer.  This converts the stream of characters into a 
         // stream of tokens.  A token is a character group, like "<i>"
         // or "</i>".  Note that this doesn't start reading the character stream yet,
         // it just sets up the lexer to read it.
         MarkdownLexer lexer = new MarkdownLexer(stream);
         lexer.reportErrorsAsExceptions();
         TokenStream tokens = new CommonTokenStream(lexer);
         
         // Make a parser whose input comes from the token stream produced by the lexer.
         MarkdownParser parser = new MarkdownParser(tokens);
         parser.reportErrorsAsExceptions();
         
         return parser;
     }
     
}
