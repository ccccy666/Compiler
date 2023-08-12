

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import gram.*;
import ast.*;
import utils.*;
import utils.Error;
import Semantic.*;
// import java.io.BufferedReader;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.text.MessageFormat;
// import java.io.FileOutputStream;
// import java.io.FileReader;
// import java.io.PrintStream;

public class Compiler {
      public static void main(String[] args) throws Exception {
        // String filePa = "/home/cyf/compiler/Compiler-Design-Implementation-master/testcases/sema/judgelist.txt";
        // BufferedReader reader = new BufferedReader(new FileReader(filePa));
        // String line;
        // String file = "my.txt";
        // PrintStream fileOut = new PrintStream(new FileOutputStream(file));
        
        // System.setOut(fileOut);
        // while ((line = reader.readLine()) != null) {
          // String filePath = MessageFormat.format("/home/cyf/compiler/Compiler-Design-Implementation-master/testcases/sema{0}",line);
          // // String filePath = "path/to/your/file.txt";
          // Path path = Paths.get(filePath);
          
          CharStream input = CharStreams.fromStream(System.in);
          try{
           
          MxLexer lexer = new MxLexer(input);
          lexer.removeErrorListeners();
          lexer.addErrorListener(new Mxerrorlistener());

          
          CommonTokenStream tokens = new CommonTokenStream(lexer);

          
          MxParser parser = new MxParser(tokens);
          // HelloParser parser = new HelloParser(tokens);
          parser.removeErrorListeners();
          parser.addErrorListener(new Mxerrorlistener());

          
          ParseTree tree = parser.program();
      
          ASTbuilder astBuilder = new ASTbuilder();
          ProgramNode ast = (ProgramNode) astBuilder.visit(tree);
          
          Globalscope globalScope = new Globalscope();
          SymbolCollector collector=new SymbolCollector(globalScope);
          collector.visit(ast);
          SemanticChecker checker=new SemanticChecker(globalScope);
          checker.visit(ast);
          System.out.print("0\n");
          }catch(Error e){
            System.out.print(e.toString());
            System.out.print('\n');

          }
        // }
        

            
        // for(int i=1;i<=72;i++){
          
          
        // }
        //  fileOut.close();
        
    
  }
}