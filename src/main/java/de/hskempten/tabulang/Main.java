package de.hskempten.tabulang;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.datatypes.exceptions.DataTypeException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.standardLibrary.StandardLibrary;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final String REPL_PREFIX = "[%2d] < ";
    private static final String REPL_POSTFIX = "[%2d] > %s";

    private static boolean isDebug = false;

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
            Main.isDebug = cli.debug;
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
            System.out.println("""
                    Usage:
                    \t-r\tStart repl
                    \t-d\tDebug mode
                    \t<string>\tInterpret filename
                    """);
        }
    }

    /**
     * Check if debug flag in command line arguments was set.
     * @return true if debug flag was set
     */
    public static boolean isDebug() {
        return isDebug;
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

        System.out.println("""
                REPL started.
                Type "showEnvironment();" to list variables in the current environment.
                Type "exit();" to quit repl.""");

        while(true) {
            try {
                // sleep so syserr can print
                TimeUnit.MILLISECONDS.sleep(50);
            } catch(InterruptedException ignored) {}

            System.out.printf(REPL_PREFIX, ++count);
            String line = "";
            try {
                line = scanner.nextLine().trim();
            } catch(NoSuchElementException a) {
                line = "exit();";
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
                    if(resultObject != null) {
                        String[] lines = resultObject.toString().split("\n");
                        if(lines.length == 1)
                            System.out.printf((REPL_POSTFIX) + "%n", count, resultObject.toString());
                        else
                            System.out.printf((REPL_POSTFIX) + "%n%s%n", count, "", resultObject.toString());
                    }
                } catch(DataTypeException | ParseTimeException e) {
                    System.out.println(e.getLocalizedMessage());
                } catch(Exception e) {
                    if(isDebug)
                        e.printStackTrace();
                    else
                        System.err.println(e.getLocalizedMessage());
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
        boolean debug = false;
        boolean repl = false;

        private static CommandLineArguments parse(String[] args) {
            var cli = new CommandLineArguments();

            for(int i = 0; i < args.length; i++) {
                switch (args[i].toLowerCase(Locale.ROOT)) {
                    case "-r" -> cli.repl = true;
                    case "-d" -> cli.debug = true;
                    default -> cli.inputFile = args[i];
                }
            }

            return cli;
        }
    }
}
