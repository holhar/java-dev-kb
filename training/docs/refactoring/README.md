# Refactoring

## Table of Contents

1. [Refactoring, a first example](#01_first_example)
2. [Principles in Refactoring](#02_principles_in_refactoring)
3. [Bad Smells in Code](#03_bad_smells_in_code)
4. [Building Tests](#04_building_tests)
15. [Putting It All Together](#15_putting_it_all_together)

## 1. Refactoring, a first example<a name="01_first_example">

* When you find you have to add a feature to a program, and the program's code is not structured in a convenient way to
  add the feature, **first refactor the program to make it easy to add the feature**, then add the feature.
* Before you start refactoring, check that you have **a solid suite of tests**. Theses tests must be self-checking.
* Refactoring changes the program in small steps. If you make a mistake, it is easy to find the bug.
* Any fool can write code that a computer can understand. **Good programmers write code that humans can understand**.

## 2. Principles in Refactoring<a name="02_principles_in_refactoring">

* **Refactoring** (noun): a change made to the internal structure of software to make it easiert to understand and
  cheaper to modify without changing its observable behavior.
* **Refactor** (verb): to restructure software by applying a series of refactorings without changing its observable
  behavior.
* The rule of Three - Three strikes and you refactor: The first time you do something you just do it. The second time
  you do something similar, you wince at the duplication, but you do the duplicate thing anyway. The third time you do
  simething similar, you refactor.
* Don't publish interfaces prematurely. Modify your code ownership policies to smooth refactoring.

## 3. Bad Smells in Code<a name="03_bad_smells_in_code">

| **Smell**                                            | **Common Refactorings**
|
|------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alternative Classes with Different Interfaces, p. 85 | Rename Method (273), Move Method (142)
| | Comments, p. 87 | Extract Method (110), Introduce Assertion (267)
| | Data Class, p. 86 | Move Method (142), Encapsulate Field (206), Encapsulate Collection (208)
| | Data Clumps, p. 81 | Extract Class (149), Introduce Parameter Object (295), Preserve Whole Object (288)
| | Divergent Change, p. 79 | Extract Class (149)
| | Duplicated Code, p. 76 | Extract Method (110), Extract Class (149), Pull Up Method (322), Form Template Method (345)
| | Feature Envy, p. 80 | Move Method (142), Move Field (146), Extract Method (110)
| | Inappropriate Intimacy, p. 85 | Move Method (142), Move Field (146), Change Bidirectional Association to
Unidirectional (200), Replace Inheritance with Delegation (352), Hide Delegate (157)
| | Incomplete Library Class, p. 86 | Introduce Foreign Method (162), Introduce Local Extension (164)
| | Large Class, p. 78 | Extract Class (149), Extract Subclass (330), Extract Interface (341), Replace Data Value with
Object (175)
| | Lazy Class, p. 83 | Inline Class (154), Collapse Hierarchy (344)
| | Long Method, p. 76 | Extract Method (110), Replace Temp with Query (120), Replace Method with Method Object (135),
Decompose Conditional (238)
| | Long Parameter List, p. 78 | Replace Parameter with Method (292), Introduce Parameter Object (295), Preserve Whole
Object (288)
| | Message Chains, p. 84 | Hide Delegate (157)
| | Middle Man, p. 85 | Remove Middle Man (160), Inline Method (117), Replace Delegation with Inheritance (355)
| | Parallel Inheritance Hierarchie, p. 83 | Move Method (142), Move Field (146)
| | Primitive Obsession, p. 81 | Replace Data Value with Object (175), Extract Class (149), Introduce Parameter Object (
295), Replace Array with Object (186), Replace Type Code with Class (218), Replace Type Code with Subclasses (223),
Replace Type Code with State/Strategy (227) | | Refused Bequest, p. 87 | Replace Inheritance with Delegation (352)
| | Shotgun Surgery, p. 80 | Move Method (142), Move Field (146), Inline Class (154)
| | Speculative Generality, p. 83 | Collapse Hierarchy (344), Inline Class (154), Remove Parameter (277), Rename
Method (273)
| | Switch Statements, p. 82 | Replace Conditional with Polymorphism (255), Replace Type Code with Subclasses (223),
Replace Type Code with State/Strategy (227), Replace Parameter with Explicit Methods (285), Introduce Null Object (260)
| | Temporary Field, p. 84 | Extract Class (149), Introduce Null Object (260)
|

* When you feel the need to write a comment, first try to refactor the code so that any comment becomes superfluous

## 4. Building Tests<a name="04_building_tests">

* Make sure all tests are fully automatic and that they check their own results.
* A suite of tests is a powerful bug detector that decapitates the time it takes to find bugs.
* Run your tests frequently. Localize tests whenever you compile - every test at least every day.
* When you get a bug report, start by writing a unit test that exposes the bug.
* It is better to write and run incomplete tests than not to run complete tests.
* Think of the boundary conditions under which things might go wrong and concentrate your tests there.
* Don't forget to test that exceptions are raised when things are expected to go wrong.
* Don't let the fear that testing can't catch all bugs stop you from writing the tests that will catch most bugs.

## 15. Putting It All Together<a name="15_putting_it_all_together">

* **Get used to picking a goal**
* **Stop when you are unsure**
* **Backtrack**
* **Duets**