# Recursion

## Designing Recursive Algorithms

An algorithm that uses recursion typically has the following form:

* **Test for base cases**: There must be at least one base case present. Base cases should be 
  defined so that every possible chain of recursive calls will eventually reach a base case, and 
  the handling of each base case should not use recursion.
* **Recur**: If not a base case, we perform one or more recursive calls. This recursive step may 
  involve a test that decides which of several possible recursive calls to make. We should 
  define each possible recursive call so that it makes progress towards a base case.
