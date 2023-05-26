@echo off

javacc parse.jj && javac parser.java && java Parser && pause
