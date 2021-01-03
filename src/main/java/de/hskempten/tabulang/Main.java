package de.hskempten.tabulang;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.standardBibliothek.MainClass;
import de.hskempten.tabulang.tokenizer.Lexer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    private static final String REPL_PREFIX = "[%2d] > ";

    public static void main(String[] args) {
        Lexer l = new Lexer();
        Interpretation interpreter = new Interpretation();

        //interpreter.getEnvironment().put("print", new InternalFunction(
        //        new ArrayList<>(new IdentifierNode[]{new IdentifierNode("x")})
        //));

        // Setup Lexer
        for(var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }
        l.addOneLineCommentMarker("//");

        // Setup interpreter library functions
        MainClass.addStandardLibrary(interpreter);

        // Parse command line arguments
        CommandLineArguments cli;
        try {
            cli = CommandLineArguments.parse(args);
        } catch(RuntimeException e) {
            System.err.println(e.getLocalizedMessage());
            return;
        }

        // parse file?
        if(cli.inputFile != null) {
            try {
                executeFile(l, interpreter, cli.inputFile);
                listInterpreterEnvironment(interpreter);

            } catch(Exception e) {
                System.err.println(e.getLocalizedMessage());
                return;
            }
        }

        // repl?
        if(cli.repl) {
            startRepl(l, interpreter);
        }
    }

    private static void executeFile(Lexer l, Interpretation interpreter, String filename) throws Exception {
        TabulangParser parser;
        ProgramItem prg;
        ProgramAST ast;

        l.setText(Files.readString(Path.of(filename)));
        parser = new TabulangParser(l, interpreter);

        prg = parser.parseN();

        // build the abstract syntax tree
        ast = ASTProgramParser.instance.parse(prg);

        ast.executeProgram(interpreter);
    }

    private static void startRepl(Lexer l, Interpretation interpreter) {
        int count = 0;
        String line = "";

        TabulangParser parser;
        ProgramItem prg;
        ProgramAST ast;

        Scanner scanner = new Scanner(System.in);

        System.out.println("REPL started.\n"
                + "Type \"showEnvironment();\" to list variables in the current environment.\n"
                + "Type \"exit();\" to quit repl.");

        while(true) {
            if(line.isBlank())
                System.out.printf(REPL_PREFIX, ++count);
            else
                // program code not done yet, append to the end of line
                System.out.print(":" + " ".repeat(String.format(REPL_PREFIX, count).length() + 1));

            line += scanner.nextLine().trim();
            if(!line.endsWith(";"))
                continue;

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

                    ast.executeProgram(interpreter);
                } catch(Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }

            line = "";
        }
    }

    private static void listInterpreterEnvironment(Interpretation interpreter) {
        for(var entry : interpreter.getEnvironment().entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
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
