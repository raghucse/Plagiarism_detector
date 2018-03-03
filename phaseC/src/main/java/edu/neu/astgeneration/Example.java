package edu.neu.astgeneration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Example {

    private static Logger lg = Logger.getLogger(AstPrinter.class.getName());

    public static void main(String[] args) {
            new Example().printAST();
    }



    public void printAST() {

        ParserFacade parserFacade = new ParserFacade();
        AstPrinter astPrinter = new AstPrinter();

        try {
            astPrinter.print(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode2.py").getFile())));
        } catch (IOException e) {
            lg.info("Failed!");
        }
    }

}
