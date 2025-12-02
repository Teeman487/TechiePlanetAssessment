## Design Decisions & Assumptions

**RemoveDuplicates**
  - Input may contain null arrays/rows → handled safely.
  - O(1) lookup for duplicates using `seen` array.
  - Dynamically expand `seen` array if needed.
  - Duplicates replaced with 0 to preserve row structure.

**DigitSum & DigitalRoot**
  - Recursion for clarity and functional style.
  - Input validation for null/empty strings.
  - Base cases clearly defined for single-digit sums.

**TimeInWords**
  - Pre-defined number-word mapping for quick lookup.
  - Special cases handled explicitly (`o'clock`, `quarter`, `half`).
  - Minutes <30 use "past", minutes >30 use "to".
  - Inputs validated; invalid hour/minutes return error.


## Setup Instruction
Ensure the following tools are installed:
- Java 17+
- Maven 3.9+
- Any Java IDE (e.g., IntelliJ IDEA, VS Code)

- Build the project: mvn clean package
- Run your application or execute any module as needed.


## Testing
-  Run all unit tests using: `mvn test` or `mvn clean package`


## Notes
- All solutions follow SOLID principles


## Project Structure - All Programming & Algorithm Solution are located in
-  ..\TechiePlanetAssessment\programming_algorithm
