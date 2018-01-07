# miniJava-Compiler
The repository for Compiler project of a MiniJava Parser Front-end upon ANTLR 4.

## Prerequisites

**Java Runtime**(1.8 or higher)

[**ANTLR4**](https://github.com/antlr/antlr4) : (ANother Tool for Language Recognition) is a powerful parser generator for reading, processing, executing, or translating structured text or binary files. It's widely used to build languages, tools, and frameworks. From a grammar, ANTLR generates a parser that can build parse trees and also generates a listener interface (or visitor) that makes it easy to respond to the recognition of phrases of interest.

for OS X,
```
$ cd /usr/local/lib
$ sudo curl -O http://www.antlr.org/download/antlr-4.7.1-complete.jar
$ export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
$ alias antlr4='java -jar /usr/local/lib/antlr-4.7.1-complete.jar'
$ alias grun='java org.antlr.v4.gui.TestRig'
```

## Getting Started with MiniJava

To simply build from MiniJava Grammar File(and by the way generate visitor), you can

```
$ antlr4 MiniJava.g4 -visitor
$ javac MiniJava*.java
$ grun MiniJava goal -gui < ../samples/factorial.java 

```
Then the parse tree shows.

In order to see my method invoke check feature, I wrote a wrong sample source file in `test\semantic\test.java` and you can simply run `make mine` to see the results. To manually check other file, you can try the following instruction:
```
$ cd src
$ javac MiniJava*.java  
$ javac detectiveVisitor.java MiniJava*.java 
$ javac Main.java detectiveVisitor.java MiniJava*.java
$ java Main < ../errorCases/test.java

```
Then you can see the defined error message.