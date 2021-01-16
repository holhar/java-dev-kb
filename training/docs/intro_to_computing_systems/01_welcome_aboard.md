# 1 Welcome Aboard

### 1.1 Explain the first of the two important ideas stated in Section 1.5.

Every computer can do the same thing as every other computer. A smaller or slower computer will just take longer.

### 1.2 Can a higher-level programming language instruct a computer to compute more than a lower-level programming language?

no, it just serves abstraction - is easier to read, faster to program. But on the other hand the higher-level
programming language is for instance less accurate.

### 1.3 What difficulty with analog computers encourages computer designers to use digital designs?

It is hard to increase the accuracy of analog machines.

### 1.4 Name one characteristic of natural languages that prevents them from being used as programming languages.

Ambiguity.

### 1.5 Say we had a "black box", which takes two numbers as input and outputs their sum. See Figure 1.7a. Say we hat another box capable of multiplying two numbers together. See Figure 1.7b. We can connect these boxes together to calculate p x (m + n). See Figure 1.7c. Assume we have an unlimited number of these boxes. Show how to connect them together to calculate:

![Figure 1.7](img/fig1-7.png "Figure 1.7: 'Black boxes' capable of (a) addition, (b) multiplication, and (c) a combination of addition and multiplication")

a) *ax + b*

* inputs to first (x) box are *a* and *x*
* output of first (x) box is *ax*
* inputs to second (+) box are *ax* and *b*
* output of second (+) box is *ax* + *b*

b) The average of the four input numbers *w*, *x*, *y*, and *z*

* inputs to first (+) box are *w* and *x*
* output of first (+) box is *w + x*
* inputs to second (+) box are *y* and *z*
* output of second (+) box is *y + z*
* inputs to third (+) box are *(w + x)* and *(y + z)*
* output of third (+) box is *w + x + y + z*
* inputs to fourth (x) box are *(w + x + y + z)* and .25
* output of fourth (x) box is 0.25 *(w + x + y + z)*, which is the average

c) *a^2 + 2ab + b^2* (Can you do it with one add box and one multiply box?)

* The key is to factor *a^2 + 2ab + b^2 = (a + b)^2*
* inputs to first (+) box are *a* and *b*
* output of first (+) box is *a + b*
* inputs to second (x) box are *(a + b)* and *(a + b)*
* output of second (x) box is *(a + b)^2 = a^2 + 2ab + b^2*

### 1.6 Write a statement in a natural language and offer two different interpretations of that statement.

Any ambiguous statement is fine. For example: I ate my sandwich on a bed of lettuce. The sandwich might have been
sitting on a bed of lettuce on the plate, or I might have been sitting on a bed of lettuce eating a sandwich.

### 1.7 The discusstion of abstration in Section 1.3.1 noted that one does not need to understand the makeup of the components as long as "everything about the detail is just fine". The case was made that when everything is not fine, *

one must be able to deconstruct the components, or be at the mercy of the abstractions*. In the taxi example, suppose
you did not understand the component, that is, you had no clue how to get to the airport. Using the notion of
abstraction, you simply tell the driver, "Take me to the airport". Explain when this is a productivity enhancer, and
when it could result in very negative consequences.

If the taxi driver is honorable, he/she asks you whether time or money is more important to you, and then gets you to
the airport as quickly or as cheaply as possible. You are freed from knowing anything about the various ways one can get
to the airport. If the taxi driver is dishonorable, you get to the airport late enough to miss your flight and/or at a
taxi fare far in excess of what it should have been, as the taxi driver takes a very circuitous route.

### 1.8 John said, "I saw the man in the park with a telescope". What did he mean? How many reasonable interpretations can you provide for this statement? List them. What property does this sentence demonstrate that makes it unacceptable as a statement in a program.

He could mean a lot of things. This statement is ambiguous as it could mean different things. Some reasonable
interpretations are: a) John saw the man "in the park with a telescope" b) John saw the "man in the park" with a
telescope. As this statement is ambiguous, it is unacceptable as a statement in a program.

### 1.9 Are natural languages capable of expressing algorithms?

Yes, if phrased in a way that is definite and lacks ambiguity.

### 1.10 Name three characteristics of algorithms. Briefly explain each of these three characteristics.

* **Definiteness**: each step is precisely stated.
* **Effective Computability**: each step can be carried out by a computer.
* **Finiteness**: the procedure terminates.

### 1.11 For each characteristic of an algorithm, give an example of a procedure that does not have the characteristic, and is therefore not an algorithm.

* (a) Lacks definiteness: Go south on Main St. for a mile or so.
* (b) Lacks effective computability: Find the integer that is the square root of 14.
* (c) Lacks finiteness: Do something. Repeat forever.

### 1.12 Are item *a* through *

e* in the following list algorithms? If not, what qualities required of algorithms do they lack?

a. Add the first row of the following matrix to another row whose first column contains a nonzero entry. (*Reminder:*
Columns run vertically; rows run horizontally.)

|   |   |   |   |
|---|---|---|---|
| 1 | 2 | 0 | 4 |
| 0  | 3 | 2  | 4  |
| 2  | 3 | 10 | 22 |
| 12 | 4 | 3  | 4  |

This is an algorithm.

b. In order to show that there are as many prime numbers as there are natural numbers, match each prime number with a
natural number in the following manner. Create paris of prime and natural numbers by mathing the first prime number with
2, the third with 3, and so forth, If, in the end, it turns out that each prime number can be paired with each natural
number, then it is shown that there are as many prime numbers as natural numbers.

No algorithm, lacks finiteness.

c. Suppose you're given two vectors each with 20 elements and asked to perform the following operation. Take the first
element of the first vector and multiply it by the first element of the second vector. Do the same to the second
element, and so forth. Add all the individual products together to derive the dot product.

This is an algorithm.

d. Lynne and Calvin are trying to decide who will take the dog for a walk. Lynne suggests that they flip a coin and
pulls a quarter out of her pocket. Calvin does not trust Lynne and suspects that the quarter may be weighted (meaning
that it might favor a particula outcome when tossed) and suggests the following procedure to fairly determine who will
walk the dog. 1. Flick the quarter twice. 2. If the outcome is heads on the first flip and tails on the second, then I
will walk the dog. 3. If the outcome is tails on the first flip, and heads on the second, then you will walk the dog. 4.
If both outcomes are tails or both outcomes are heads, then we flip twice again. Is Calvins's technique an algorithm?

No algorithm, lacks finiteness.

e. Given a number, perform the following steps in order:

1. Multiply it by four 2. Add four 3. Divide by two 4. Subtract two 5. Divide by two 6. Aubtract one 7. At this point,
   add one to a counter to keep track of the fact that you performed steps 1 through 6. Then test the result you got
   when you subtracted one. If 0, write down the number of times you performed steps 1 through 6 and stop. If not 0,
   starting with the result of subtracting 1, perform the above 7 steps again.

This is not finite, so it is not an algorithm. Steps 1 to 6 calculate, albeit in a long way, the number - 1. If the
given number is negative or zero, then there will never be a time when you get 0 at the end of step 6.

### 1.13 Two computers, A and B, are identical except for the fact that A has a subtract instruction and B does not. Both have add instructions. Both have instructions that can take a value and produce the negative of that value. Which computer is able to solve more problems, A or B? Prove your result.

Both computers, A and B, are capable of solving the same problems. Computer B can perform subtraction by taking the
negative of the second number and adding it to the first one. As A and B are otherwise identical, they are capable of
solving the same problems.

### 1.14 Suppose we wish to put a set of names in alphabetical order. We call the act of doing so *

sorting*. One algorithm that can accomplish that is called the bubble sort. We could then program our bubble sort
algorithm in C, and compile the C program to execute on an x86 ISA. The x86 ISA can be implemented with an Intel Pentium
IV microarchitecure. Let us call the sequence "Bubble Sort, C program, x86 ISA, Pentium IV microarchitecture" one *
transformation process*. Assume we have available four sorting algorithms and can program in C, C++, Pascal, Fortran,
and COBOL, We have available compilers that can translate from each of these to either x86 or SPARC, and we have
available three different microarchitectures for x86 and three different microarchitectures for SPARC.

* **a.** How many transformation processes are possible?
* Answer: 120 transformation processes.
* **b.** Write three examples of transformation processes.
* Answer: Any 3 of this form are fine: "Sort Algorithm 3, Fortran program, SPARC ISA, SPARC microarchitecture 1".
* **c.** How many tranformation processes are possible if instead of three different microarchitectures for x86 and
  three different microarchitectures for SPARC, there were two for x86 and four for SPARC?
* Answer: 120 transformation processes again.

### 1.15 Identify one advantage of programming in a higher-level language compared to a lower-level language. Identify one disadvantage.

**Advantages of a higher level language**: Fewer instructions are required to do the same amount of work. This usually
means it takes less time for a programmer to write a program to solve a problem. High level language programs are
generally easier to read and therefore know what is going on.

**Disadvantages of a higher level language**: Each instruction has less control over the underlying hardware that
actually performs the computation that the program frequently executes less efficiently.
**NOTE**: this problem is beyond the scope of Chapter 1 or most students.

### 1.16 Name at least three things specified by an *instruction set architecture (ISA)*.

An ISA describes the interface to the computer from the perspective of the 0s and 1s of the program. For example, it
describes the operations, data types, and addressing modes a programmer can use on that particular computer. It doesn' t
specify the actual physical implementation. The microarchitecture does that. Using the car analogy, the ISA is what the
driver sees, and the microarchitecture is what goes on under the hood.

### 1.17 Briefly describe the difference between an ISA and a microarchitecture.

An ISA describes the interface to the computer from the perspective of the 0s and 1s of the program. For example, it
describes the operations, data types, and addressing modes a programmer can use on that particular computer. It doesn't
specify the actual physical implementation. The microarchitecture does that. Using the car analogy, the ISA is what the
driver sees, and the microarchitecture is what goes on under the hood.

### 1.18 How many ISAs are normally implemented by a single microarchitecture? Conversely, how many microarchitectures could exist for a single ISA?

A single microarchitecture typically implements only one ISA. However, many microarchitectures usually exist for the
same ISA.

### 1.19 List the levels of transformation and name an example for each level.

* (a) **Problem**: For example, what is the sum of the ten smallest positive integers.
* (b) **Algorithm**: Any procedure is fine as long as it has definiteness, effective computability, and finiteness.
* (c) **Language**: For example, C, C++, Fortran, IA-32 Assembly Language.
* (d) **ISA**: For example, IA-32, PowerPC, Alpha, SPARC.
* (e) **Microarchitecture**: For example, Pentium III, Compaq 21064.
* (f) **Circuits**: For example, a circuit to add two numbers together.
* (g) **Devices**: For example, CMOS, NMOS, gallium arsenide.

### 1.20 The levels of transformation in Figure 1.6 are often referred to as levels of abstraction. Is that a reasonable characterization? If yes, give an example. If not, why not?

![Figure 1.6](img/fig1-6.png "Figure 1.6: Levels of transformation")

Refering to the levels of transformation as the levels of abstraction is a reasonable characterization. Each level in
Figure 1.6 is essentially a level of abstraction, abstracting the other levels. For example, if the problem statement
said "Find the average of two numbers", you have abstracted the rest of the system away. Now, lets take the Language
level. If you have a C language program, the lower levels are abstraced away. You don't have to worry about the exact
ISA or microarchitecture you will run the program on. Similarily, you should be able to draw examples for all the other
levels.

### 1.21 Say you go to the store and buy some word processing software. What form is the software actually in? Is it in a high-level programming langurage? Is it in assembly language? Is it in the ISA of the computer on which you'll run it? Justify your answer.

It is in the ISA of the computer that will run it. We know this because if the word processing software were in a high-
or low-level programming language, then the user would need to compile it or assemble it before using it. This never
happens. The user just needs to copy the files to run the program, so it must already be in the correct machine
language, or ISA.

### 1.22 Suppose you were given a task at one of the transformation levels shown in Figure 1.6, and required to transform it to the level just below. At which level would it be most difficult to perform the transformation to the next lwer level? Why?

The transformation from Problems to Algorithms is the most difficult step. There is ambiguity in a Problem statement
which needs to be resolved in order to generate an algorithm. This requires the intelligence to actually understand the
problem and make sense out of it. All the other transformations can be performed by a program written to perform that
transformation.

### 1.23 Why is an ISA unlikely to change between successive generations of microarchitectures that implement it? For example, why would Intel want to make certain that the ISA implemented by the Pentium III is the same as the one implemented by the Pentium II? *

Hint*: When you upgrade your computer (or buy one with a newer CPU), do you need to throw out all your old software?

ISA's don't change much between successive generations, because of the need for **backward compatibility**. You'd like
your new computer to still run all your old software.