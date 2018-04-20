package edu.neu.astgeneration;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;



public class ParserFacade {

    /**
     * This function reads the input file
     * @param file is the input file given by the user
     * @param encoding is the encoding format
     * @return the string version of the encoded file
     * @throws IOException
     */
    private static String readFile(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }

    /**
     * Generates a parse tree
     * @param file is the input file who's AST is to be developed
     * @return a parse tree
     * @throws IOException
     */
    public Python3Parser.File_inputContext parse(File file) throws IOException {
        String code = readFile(file, Charset.forName("UTF-8"));
        Python3Lexer lexer = new Python3Lexer(new ANTLRInputStream(code));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python3Parser parser = new Python3Parser(tokens);

        return parser.file_input();
    }
}