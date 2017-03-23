
package baz;

import java.io.*;

import baz.interpreter.*;
import baz.semantics.*;
import baz.syntax.lexer.*;
import baz.syntax.node.*;
import baz.syntax.parser.*;

public class Baz {

    public static void main(
            String[] args) {

        PushbackReader in = null;

        if (args.length == 0) {

            in = new PushbackReader(new InputStreamReader(System.in), 1024);
        }
        else if (args.length == 1) {

            try {
                in = new PushbackReader(new FileReader(args[0]), 1024);
            }
            catch (FileNotFoundException e) {
                System.err.println(
                        "INPUT ERROR: file not found '" + args[0] + "'.");
                System.exit(1);
            }
        }
        else {
            System.err.println("COMMAND-LINE ERROR: too many arguments.");
            System.exit(1);
        }

        Node tree = null;

        try {
            // analyse lexicale et syntaxique ainsi que construction de l'arbre
            tree = new Parser(new Lexer(in)).parse();
        }
        catch (IOException e) {
            String inputName;
            if (args.length == 0) {
                inputName = "standard input";
            }
            else {
                inputName = "file '" + args[0] + "'";
            }
            System.err.println("INPUT ERROR: " + e.getMessage()
                    + " while reading " + inputName + ".");
            System.exit(1);
        }
        catch (ParserException e) {
            System.err.println("SYNTAX ERROR: " + e.getMessage() + ".");
            System.exit(1);
        }
        catch (LexerException e) {
            System.err.println("LEXICAL ERROR: " + e.getMessage() + ".");
            System.exit(1);
        }

        // Pour visualiser l'arbre syntaxique (code source DOT)
        // tree.apply(new TreeVisualizer());

        FunctionTable functionTable = new FunctionTable();

        try {
            // vérifications sémantiques
            SemanticVerifier.verify(tree, functionTable);
        }
        catch (SemanticException e) {
            System.err.println("SEMANTIC ERROR: " + e.getMessage() + ".");
            System.exit(1);
        }

        try {
            // interprétation
            Interpreter.interpret(tree, functionTable);
        }
        catch (InterpreterException e) {
            System.out.println();
            System.out.flush();
            System.err.println("RUNTIME ERROR: " + e.getMessage() + ".");
            System.exit(1);
        }
    }

}
