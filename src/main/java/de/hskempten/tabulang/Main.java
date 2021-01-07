package de.hskempten.tabulang;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.standardLibrary.StandardLibrary;
import de.hskempten.tabulang.tokenizer.Lexer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    private static final String REPL_PREFIX = "[%2d] < ";
    private static final String REPL_POSTFIX = "[%2d] > %s";

    public static void main(String[] args) {
        Lexer l = new Lexer();
        Interpretation interpreter = new Interpretation();

        // Setup Lexer
        for(var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }
        l.addOneLineCommentMarker("//");

        // Setup interpreter library functions
        StandardLibrary.addStandardLibrary(interpreter);

        // Parse command line arguments
        CommandLineArguments cli;
        try {
            cli = CommandLineArguments.parse(args);
        } catch(RuntimeException e) {
            System.err.println(e.getLocalizedMessage());
            return;
        }

        boolean hasDoneSomething = false;

        // parse file?
        if(cli.inputFile != null) {
            hasDoneSomething = true;
            try {
                Object result = executeFile(l, interpreter, cli.inputFile);
                //listInterpreterEnvironment(interpreter);

                if(result != null)
                    System.out.println("\nProgram exited with the following non-null result: " + result.toString());

            } catch(Exception e) {
                System.err.println(e.getLocalizedMessage());
                return;
            }
        }

        // repl?
        if(cli.repl) {
            hasDoneSomething = true;
            startRepl(l, interpreter);
        }

        // nothing?
        if(!hasDoneSomething) {
            System.out.println("Usage:\n\t-r\tStart repl\n\t<string>\tInterpret filename\n");
        }
    }

    private static Object executeFile(Lexer l, Interpretation interpreter, String filename) throws Exception {
        TabulangParser parser;
        ProgramItem prg;
        ProgramAST ast;

        l.setText(Files.readString(Path.of(filename)));
        parser = new TabulangParser(l, interpreter);

        prg = parser.parseN();

        // build the abstract syntax tree
        ast = ASTProgramParser.instance.parse(prg);
        ast.executeProgram(interpreter);
        // MAYBE?
        //return ast.executeProgram(interpreter);
        return null;
    }

    private static void startRepl(Lexer l, Interpretation interpreter) {
        int count = 0;

        TabulangParser parser;
        ProgramItem prg;
        ProgramAST ast;

        Scanner scanner = new Scanner(System.in);

        System.out.println("REPL started.\n"
                + "Type \"showEnvironment();\" to list variables in the current environment.\n"
                + "Type \"exit();\" to quit repl.");

        while(true) {
            System.out.printf(REPL_PREFIX, ++count);
            String line = "";
            try {
                line = scanner.nextLine().trim();
            } catch(NoSuchElementException a) {
                System.out.println("\nk bye");
                System.exit(0);
            }

            if("exit();".equals(line.replaceAll("\\(\\s+\\)", "()"))) {
                break;
            } else if("showEnvironment();".equals(line.replaceAll("\\(\\s+\\)", "()"))) {
                listInterpreterEnvironment(interpreter);
            } else {
                l.setText(line);
                parser = new TabulangParser(l, interpreter);

                try {
                    prg = parser.parseN();

                    // build the abstract syntax tree
                    ast = ASTProgramParser.instance.parse(prg);

                    var resultObject = ast.executeProgram(interpreter);
                    if(resultObject != null)
                        System.out.println(String.format(REPL_POSTFIX, count, resultObject.toString()));
                } catch(Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }

    private static void listInterpreterEnvironment(Interpretation interpreter) {
        System.out.println("--- Internal functions ---");
        for(var entry : interpreter.getEnvironment().entrySet()) {
            if(entry.getValue() instanceof InternalLibraryFunction f)
                System.out.println(f.formattedString(entry.getKey()));
        }

        System.out.println("\n--- Functions ---");
        for(var entry : interpreter.getEnvironment().entrySet()) {
            if(entry.getValue() instanceof InternalFunction f)
                System.out.println(f.formattedString(entry.getKey()));
        }

        System.out.println("\n--- Variables ---");
        for(var entry : interpreter.getEnvironment().entrySet()) {
            if(!(entry.getValue() instanceof InternalLibraryFunction || entry.getValue() instanceof InternalFunction))
                System.out.println(entry.getKey() + " := " + entry.getValue().toString());
        }

        System.out.println();
    }

    private static class CommandLineArguments {
        String inputFile = null;
        boolean repl = false;

        private static CommandLineArguments parse(String[] args) {
            var cli = new CommandLineArguments();

            for(int i = 0; i < args.length; i++) {
                switch(args[i].toLowerCase(Locale.ROOT)) {
                    case "-r":
                        cli.repl = true;
                        break;

                    default:
                        cli.inputFile = args[i];
                }
            }

            return cli;
        }
    }
}
