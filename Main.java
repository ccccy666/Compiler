

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import gram.*;
import ast.*;
import utils.*;
import utils.Error;
import Semantic.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
      public static void main(String[] args) throws Exception {
        String file = "my.txt";
        PrintStream fileOut = new PrintStream(new FileOutputStream(file));

            // 将System.out重定向到文件输出流
        System.setOut(fileOut);
        for(int i=1;i<=16;i++){
          String filePath = MessageFormat.format("/home/cyf/compiler/Compiler-Design-Implementation-master/testcases/sema/class-package/class-{0}.mx",i);
          // String filePath = "path/to/your/file.txt";
          Path path = Paths.get(filePath);
          // 从标准输入流中读取输入
          CharStream input = CharStreams.fromPath(path);
          try{
            // 创建词法分析器
          MxLexer lexer = new MxLexer(input);
          lexer.removeErrorListeners();
          lexer.addErrorListener(new Mxerrorlistener());

          // 创建词法符号流
          CommonTokenStream tokens = new CommonTokenStream(lexer);

          // 创建语法解析器
          MxParser parser = new MxParser(tokens);
          // HelloParser parser = new HelloParser(tokens);
          parser.removeErrorListeners();
          parser.addErrorListener(new Mxerrorlistener());

          // 解析给定的规则名称
          ParseTree tree = parser.program();
      
          ASTbuilder astBuilder = new ASTbuilder();
          ProgramNode ast = (ProgramNode) astBuilder.visit(tree);
          //进行semantic check
          Globalscope globalScope = new Globalscope();
          SymbolCollector collector=new SymbolCollector(globalScope);
          collector.visit(ast);
          SemanticChecker checker=new SemanticChecker(globalScope);
          checker.visit(ast);
          System.out.print("accept\n");
          }catch(Error e){
            System.out.print(e.toString());
            System.out.print('\n');

          }
          
        }
         fileOut.close();
        
    
  }
}