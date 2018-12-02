# Histogram
By Josh Smith

A simple program that creates a histogram from the number of occurrences of words in a text file.
### Requirements
* Unix
* Java 1.8
* JUnit 4 (if you'd like to run the unit tests)
### Instructions
1. Clone the repo `git clone https://github.com/joshuadesmith/histogram.git`
2. Navigate to the local copy that was created `cd path/to/folder/histogram`
3. Compile and run the program:
    * With make: `make runcreatehistogram`
    * Manually:
        1. Create a build output directory
        2. Compile the source code `javac -g -d your/build/dir ./src/com/josh/histogram/*.java`
        3. Clear the output file: `>output.txt`
        4. Run the program: `java -cp your/build/dir com/josh/histogram/App path/to/input/file`
4. View the results of the program in output.txt  
        
* Note: if you don't provide a filename argument when running the program, it will search for  
A file called `input.txt` in the base directory of the project.

### Running Unit Tests
1. Download JUnit 4 (I used 4.12) and Hamcrest Core (I used 1.3)
2. If you'd like to use Make:
    1. Create a directory `~/java-jars/` and place the JUnit/Hamcrest JARs in the directory
    2. Run `make runhistogramtests`
3. If you'd like to run them manually:
    1. Create a build directory if you haven't
    2. Compile the app source files if you haven't
    3. Add JUnit and the app class files to the classpath: `export CLASSPATH=$CLASSPATH:path/to/junit.jar:path/to/hamcrest.jar:your/build/dir`
    4. Compile the test files: `javac -g -d your/build/dir .src/com/josh/test/*.java`
    5. Run the tests: `java org.junit.runner.JUnitCore com.josh.test.WordHistogramTest`


### Improvements Moving Forward
Moving forward, I would have like to test the program for performance with extremely large files. I also would like to 
dive deeper into make the project's file parsing/cleaning logic more robust. Finally, I would like to give the user
the flexibility to define their desired output file, and make the setup more user-friendly.

This was a create chance to de-rust my Java skills a bit, and also learn the basics of Makefiles.
