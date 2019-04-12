# FunkBaz

### What the hell is this?

A compiler for the language "FunkBaz" created with the help of SableCC an open source compiler generator in Java.
It is the result of a school project made for the Introduction to Compilers class at UQAM winter 2017.

FunkBaz is a typed language that supports the use of anonymous functions and closures.

### Steps for building and executing a funkbaz program:

  * First download the sablecc jar executable from the [sablecc website](http://www.sablecc.org/downloads).
  
  * Generate syntax directory from sablecc productions file:
   ``` bash
      $ java -jar /path/to/sablecc-3.7/lib/sablecc.jar -d src/ productions.sablecc
   ```
   
  * Run the java binary with your new language source code file as parameter

[Link](http://slides.com/joseki101/deck-2#/) to slide show presentation of the project
