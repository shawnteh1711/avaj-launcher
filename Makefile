SRC_DIR = src
OUT_DIR = out
MAIN_CLASS = com.flight.simulation.Main
TEST_CLASS = test.flight.simulation.Main
SCENARIO_FILE = scenario.txt
OUTPUT_FILE = simulation.txt
JAR_FILE = avaj-launcher.jar
TEMP_FILE = sources.txt

# Find all Java source files
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Default target
all: compile run

# Rebuild: clean and build everything
re: clean all

# Compile Java files to the out directory
compile:
	mkdir -p $(OUT_DIR)
	find $(SRC_DIR) -name "*.java" > $(TEMP_FILE)
	javac -d $(OUT_DIR) @$(TEMP_FILE)
	rm -f $(TEMP_FILE)

# Create excutable JAR file
jar: compile
	jar cfe $(JAR_FILE) $(MAIN_CLASS) -C $(OUT_DIR) .

# Run the main program
run:
	@java -cp $(OUT_DIR) $(MAIN_CLASS) $(SCENARIO_FILE); EXIT_CODE=$$?; \
	if [ $$EXIT_CODE -eq 1 ]; then \
		echo "Program end with invalid scenario file"; \
	else \
		if [ -f $(OUTPUT_FILE) ]; then \
			echo "=== Simulation Output ==="; \
			cat -e $(OUTPUT_FILE); \
		fi; \
	fi

# Run using jar
runJar:
	@java -jar $(JAR_FILE) $(SCENARIO_FILE); EXIT_CODE=$$?; \
	if [ $$EXIT_CODE -eq 1 ]; then \
		echo "Program end with invalid scenario file"; \
	else \
		if [ -f $(OUTPUT_FILE) ]; then \
			echo "=== Simulation Output ==="; \
			cat -e $(OUTPUT_FILE); \
		fi; \
	fi

# Run tests
test: compile
	java -cp $(OUT_DIR) $(TEST_CLASS)

# Clean up compiled files and output
clean:
	rm -rf $(OUT_DIR)
	rm -f $(OUTPUT_FILE)
	rm -f $(TEMP_FILE)
	rm -f $(JAR_FILE)


# Phony targets
.PHONY: all compile run test clean jar runJar