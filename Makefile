# Change the src to the path of your java source files
JAVA_SRC = $(shell find src -name '*.java')
# Change this to the path of your antlr jar
ANTLR_JAR = /home/cyf/compiler/antlr-4.9.3-complete.jar

ANTLR_G4 = $(shell find -name '*.g4')
G4_DIR = $(shell find -name '*.g4' | xargs dirname | uniq)

.PHONY: all
all: Compiler

.PHONY: Compiler
Compiler: $(JAVA_SRC) antlr-parser
	javac11 -d bin $(JAVA_SRC) -cp $(ANTLR_JAR) -encoding UTF-8

# replace the antlr4 command with your own
.PHONY: antlr-parser
antlr-parser: $(ANTLR_G4)
	antlr4 $^ -visitor -listener -package gram

.PHONY: clean
clean:
	rm -f $(G4_DIR)/*.java $(G4_DIR)/*.tokens $(G4_DIR)/*.interp
	rm -f bin/*.class bin/*.jar