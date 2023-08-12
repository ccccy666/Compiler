# Change the src to the path of your java source files
JAVA_SRC = $(shell find . -name '*.java')
# Change this to the path of your antlr jar
ANTLR_JAR = /home/cyf/compiler/antlr-4.9.3-complete.jar

.PHONY: all
all: Compiler

.PHONY: Compiler
Compiler: $(JAVA_SRC)
	javac11 -d bin $(JAVA_SRC) -cp $(ANTLR_JAR)

.PHONY: clean
clean:
	find bin -name '*.class' -or -name '*.jar' | xargs rm -f