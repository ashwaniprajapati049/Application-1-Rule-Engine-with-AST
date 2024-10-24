# Rule Engine with AST

## Description

The **Rule Engine with AST** is a 3-tier application that determines user eligibility based on certain rules (e.g., age, department, income). The rules are parsed into an Abstract Syntax Tree (AST), allowing for dynamic creation, combination, modification, and evaluation of these rules. It is ideal for scenarios such as evaluating user attributes in personalized offers, HR systems, or financial services.

## Features

- Parse complex rules into Abstract tax Trees (ASTs).
- Combine multiple ASTs into a single structure.
- Evaluate user data (attributes like age, department, salary) against the created AST.
- Support for logical conditions like AND, OR, and comparison operators (>, <, ==).

## How It Works

- **AST Representation**: The rules are represented as a tree structure, where each node is either an operator (AND, OR) or an operand (e.g., age > 30).
- **Evaluation**: The engine recursively evaluates the tree based on user input, returning `True` or `False` to determine if the user meets the criteria defined by the rule.

## Setup Instructions

### 1. Prerequisites

Ensure Java 8 or higher is installed.

### 2. Clone the Repository


git clone https://github.com/ashwaniprajapati049/rule-engine-ast.git
cd rule-engine-ast
API Endpoints
POST /create_rule
Description: Parses a rule string into an AST.
Request Body:
{
  "rule": "age > 30 AND department == 'Sales'"
}
Response
{
  "id": 1,
  "ast": { ... } // The AST object representation
}
POST /combine_rules
Description: Combines multiple rule strings into a single AST.
Request Body:
{
  "rules": [
    "age > 30 AND department == 'Sales'",
    "salary > 50000 OR experience > 5"
  ]
}
Response
{
  "id": 2,
  "combined_ast": { ... } // The combined AST object
}
POST /evaluate_rule
Description: Evaluates an AST with user data.
Request Body:
{
  "ast": { ... },
  "data": {
    "age": 35,
    "department": "Sales",
    "salary": 60000,
    "experience": 3
  }
}







