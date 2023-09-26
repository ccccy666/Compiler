

import java.io.FileOutputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import IR.*;
import IRopt.*;
import IRopt.DomTreeBuilder;
// import IRopt.IROptimizer;
import IRopt.Mem2Reg;
import gram.MxParser.*;
import gram.*;
import ast.*;
import backend_.*;
import utils.*;

import Semantic.*;
import assembly.ASMModule;

// import java.io.BufferedReader;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.text.MessageFormat;
// import java.io.FileOutputStream;
// import java.io.FileReader;
  import java.io.OutputStream;

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
          ProgramNode ast = (ProgramNode) astBuilder.visitProgram((ProgramContext)tree);
          
          // if(ast==null) {
          //   System.out.println("null");
          //   return ;
          // }

          Globalscope globalScope = new Globalscope();
          SymbolCollector collector=new SymbolCollector(globalScope);
          collector.visit(ast);
          SemanticChecker checker=new SemanticChecker(globalScope);
          checker.visit(ast);
          Program irProgram = new Program();
          new IrBuilder(irProgram, globalScope).visit(ast);
          new CFGBuilder(irProgram).work();
    new DomTreeBuilder(irProgram).work();
    new Mem2Reg(irProgram).work();
          // OutputStream irout = System.out;
          // irout.write(irProgram.toString().getBytes());
          // irout.close();
          // FileOutputStream irout=new FileOutputStream("output.ll");
          // irout.write(irProgram.toString().getBytes());
          // irout.close();
          
          ASMModule asmModule = new ASMModule();
    new InstSelector(asmModule).visit(irProgram);
    new Newallocator(asmModule).work();
    
    
    // FileOutputStream out = new FileOutputStream("test.s");
    // out.write(asmModule.toString().getBytes());
    // out.close();
          OutputStream Out = System.out;//new FileOutputStream("output.ll");
          Out.write(asmModule.toString().getBytes());
          Out.close();
          // System.out.print("0\n");
          // }catch(Error e){
          //   System.out.print(e.toString());
          //   System.out.print('\n');

          //p,s,m

          // }
        // }
        

            
        // for(int i=1;i<=72;i++){
          
          
        // }
        //  fileOut.close();
        
    
  }
}