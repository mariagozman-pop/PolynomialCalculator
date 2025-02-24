# Polynomial Calculator

## Description
The Polynomial Calculator is a user-friendly application designed to perform various mathematical operations on polynomials. This application supports operations such as addition, subtraction, multiplication, division, differentiation, and integration of polynomial expressions. It aims to simplify mathematical tasks for users, allowing for accurate and efficient calculations.

## Features
- **Polynomial Operations**: Perform addition, subtraction, multiplication, division, differentiation, and integration of polynomials.
- **User Interface**: Intuitive GUI built using Java Swing, allowing users to easily input polynomials and select operations.
- **Error Handling**: Detects and provides informative messages for invalid inputs or mathematical errors.
- **Scalability**: Designed to accommodate future enhancements with a modular structure.
- **Results Display**: Presents results in standard polynomial notation for readability.

## Usage
- Launch the application to reveal the main interface.
- Input polynomial expressions in the designated fields.
- Select the desired operation (addition, subtraction, multiplication, division, differentiation, or integration) using the corresponding buttons.
- The result will be displayed in a designated area, formatted in standard polynomial notation.

## Functional Requirements
- Ability to insert polynomial expressions.
- Support for addition, subtraction, multiplication, division, differentiation, and integration of polynomials.
- Accurate display of results.
- Error detection and informative messaging for invalid inputs.

## Non-functional Requirements
- Intuitive and easy-to-use GUI.
- Efficient performance, even with large polynomial expressions.
- Graceful error handling to prevent application crashes.
- Modular design to facilitate future updates and feature integration.

## Design Principles
- **Abstraction**: Use of classes such as Polynomial and Monomial to represent mathematical concepts while hiding implementation details.
- **Encapsulation**: Bundling data and operations into cohesive units, ensuring data integrity.
- **Polymorphism**: Implementing interfaces to allow uniform treatment of different object types.
- **Modularity**: Organized into packages for logic, GUI, and models, promoting code reuse and maintainability.

## Implementation
The application is developed using Java, utilizing Java Swing for the GUI. The logic for polynomial operations is encapsulated in separate classes that handle mathematical computations.

## Testing
Comprehensive testing strategies include:
- Unit tests for individual components.
- Integration tests for system functionality.
- User acceptance testing to validate usability.
