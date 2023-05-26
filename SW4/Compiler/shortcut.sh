#!/bin/bash

# Generate the JavaCC parser
javacc parse.jj

# Compile the Java source code
javac Compiler.java

# Run the Java application
java Compiler