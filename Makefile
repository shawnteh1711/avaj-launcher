SRC_DIR = src
OUT_DIR = out
MAIN_CLASS = com.flight.simulation.Main
TEST_CLASS = com.flight.simulation.Main
SCENARIO_FILE = scenario.txt
OUTPUT_FILE = simulation.txt

# Find all Java source files
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Default target
all: compile run

# Compile Java files to the out directory
compile:
    mkdir -p $(OUT_DIR)
    find $(SRC_DIR) -name "*.java" >> sources.txt
    javac -d $(OUT_DIR) @sources.txt

# Run the main program
run:
    java -cp $(OUT_DIR) $(MAIN_CLASS) $(SCENARIO_FILE)
    cat -e $(OUTPUT_FILE)

# Run tests
test:
    java -cp $(OUT_DIR) $(TEST_CLASS)

# Clean up compiled files and output
clean:
    rm -rf $(OUT_DIR)
    rm -rf $(OUTPUT_FILE)

# Phony targets
.PHONY: all compile run test clean
