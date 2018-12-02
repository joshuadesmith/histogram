SRC_OUT = ./out/production/histogram

SRC_DIR = ./src/com/josh/histogram

TEST_DIR = ./src/com/josh/test

JUNIT = ~/java-jars/junit-4.12.jar

HAMCREST = ~/java-jars/hamcrest-core-1.3.jar

CLASS_PATH = $CLASSPATH:$(JUNIT):$(HAMCREST):$(SRC_OUT)

FILES = *.java

MAIN_APP = com/josh/histogram/App
MAIN_TEST = com.josh.test.WordHistogramTest

TEST_FILE_1 = ./testfiles/test-non-alphanumeric-small.txt

createoutdir:
	@echo Creating Output Directory...; \
	mkdir -p $(SRC_OUT)

clean:
	rm -rf ./out/; \
	>output.txt;

compilesource:
	@echo Compiling App Source Files...; \
	javac -g -d $(SRC_OUT) $(SRC_DIR)/$(FILES);

compiletests:
	@echo Compiling Test Source Files...; \
	javac -g -d $(SRC_OUT) -cp $(JUNIT):$(HAMCREST):$(SRC_OUT) $(TEST_DIR)/$(FILES);

runcreatehistogram: | createoutdir compilesource
	@echo Running Histogram...; \
	>output.txt; \
	java -cp $(SRC_OUT) $(MAIN_APP) $(TEST_FILE_1);

runcreatehistogram_theraven: | createoutdir compilesource
	@echo Creating a word histogram for The Raven; \
	>output.txt; \
	java -cp $(SRC_OUT) $(MAIN_APP) ./testfiles/the-raven.txt;

runhistogramtests: | createoutdir compilesource
	@echo Running Unit Tests...; \
	export CLASSPATH=$(CLASS_PATH); \
	javac -g -d $(SRC_OUT) $(TEST_DIR)/$(FILES); \
	java org.junit.runner.JUnitCore $(MAIN_TEST);