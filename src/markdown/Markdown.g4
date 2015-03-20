grammar Markdown;

// This puts a package statement at the top of the output Java files.
@header {
package markdown;
}

// This adds code to the generated parser. Do not change these lines.
@members {
    // This method makes the parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        //removeErrorListeners();
        
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the parser rules. They define the structures used by the parser.
 * Nonterminal names *must* be lowercase, like html, normal, and italic.
 * Terminals are normally quoted literal strings, like '<i>' and '</i>', but 
 * some terminals are named, like EOF and TEXT below.  
 * Named terminals *must* be capitalized.
 */
root : markdown EOF ;
markdown : ( italic | normal ) * ;
italic : '_' normal '_' ;
normal : TEXT ;
TEXT : ~[_]+ ;  /* represents a string of one or more characters that are not _ 
