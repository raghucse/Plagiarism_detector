package edu.neu.astgeneration;

import java.io.File;
import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {
            new Example().printAST();
    }



    public void printAST() {
        ParserFacade parserFacade = new ParserFacade();
        AstPrinter astPrinter = new AstPrinter();

        try {
            astPrinter.print(parserFacade.parse(new File(getClass().getClassLoader().getResource("simplecode2.py").getFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
