# Algorithm Analysis

* **data structure**: systematic way of organizing and accessing data
* **algorithm**: step-by-step procedure for performing some task in a finite amount of time

The primary analysis tool used in context of this project involves characterizing the **running 
times** of algorithms and data structure operations, with **space usage** also being of interest.

We focus on the relationship between the running time of an algorithm and the size of its input.

We are interested in characterizing an algorithm's running time as a function of the input size.

## Empirical Analysis

Measure the elapsed time, collect data, and compare:

    long startTime = System.currentTimeMillis();
    // (run the algorithm)
    long endTime = System.currentTimeMillis();
    long elapsed = endTime - startTime;

#### Challenges of Experimental Analysis

* Experimental running times of two algorithms are difficult to directly compare unless the 
  experiments are performed in the same hardware and software environments.
* Experiments can be done only on a limited set of test inputs; hence, they leave out the 
  running times of inputs not included in the experiment (and these inputs may be important).
* An algorithm must be fully implemented in order to execute it to study its running time 
  experimentally.

### Moving Beyond Experimental Analysis

Our goal is to develop an approach to analyzing the efficiency of algorithms that:

1. Allows us to evaluate the relative efficiency of any two algorithms in a way that is 
   independent of the hardware and software environment.
2. Is performed by studying a high-level description of the algorithm without need for 
   implementation.
3. Takes into account all possible inputs.

#### Counting Primitive Operations

We define a set of primitive operations such as the following:

* Assigning a value to a variable 
* Following an object reference 
* Performing an arithmetic operation (for example, adding two numbers)
* Comparing two numbers 
* Accessing a single element of an array by index 
* Calling a method 
* Returning from a method

A primitive operation corresponds to a low-level instruction with an execution time that is 
constant. We will simply count how many primitive operations are executed, and use this number **t** 
as a measure of the running time of the algorithm. The number, **t**, of primitive operations an 
algorithm performs will be proportional to the actual running time of that algorithm.

#### Measuring Operations as a Function of Input Size

To capture the order of growth of an algorithm's running time, we will associate, with each 
algorithm, a function **f(n)** that characterizes the number of primitive operations that are 
performed as a function of the input size **n**.

#### Focusing on the Worst-Case Input

An average-case analysis usually requires that we calculate expected running times based on a 
given input distribution, which usually involves sophisticated probability theory. Therefore, 
unless we specify otherwise, we will characterize running times in terms of the **worst case**, 
as a function of the input size, **n**, of the algorithm.

## Common Mathematical Functions

### The Constant Function

    f(n) = c

For any argument **n**, the constant function **f(n)** assigns the value **c**.

The most fundamental constant function is 

    g(n) = 1

### The Logarithm Function

This function is defined as the inverse of a power, as follows:

    x = log_b n <=> b*x = n

The base 2 is so common, so that is

    log n = log_2 n

**Logarithm Rules:**

    log_b (ac) = log_b a + log_b c
    log_b (a/c) = log_b a - log_b c
    log_b (a*c) = c log_b a
    log_b a = log_d a / log_d b
    b*(log_d a) = a*(log_d b)

### The Linear Function

    f(n) = n

This function arises in algorithm analysis any time we have to do a single basic operation for 
each of **n** elements.

### The N-Log-N Function

    f(n) = n log_n

that is, the function that assigns to an input **n** the value of **n** times the logarithm 
base-two of **n**. This function grows a little more rapidly than the linear function and a lot 
less rapidly than the quadratic function.

### The Quadratic Function

    f(n) = n*2

That is, given an input value **n**, the function **f** assigns the product of **n** with itself 
(in other words, "**n squared**"). The main reason why the quadratic function appears in the 
analysis of algorithms is that there are many algorithms that have **nested loops**, where the inner
loop performs a linear number of operations and the outer loop is performed a
linear number of times.

**Nested Loops and the Quadratic Function**

    1 + 2 + 3 + ... + (n-2) + (n-1) + n = (n(n+1))/2

See story of **Gauss** as pupil, solving the task of adding all numbers from 1 to 100 in very 
little time.

### The Cubic Function and Other Polynomials

    f(n) = n*3

which assigns to an input value **n** the product of **n** with itself three times.

**Polynomials**

The linear, quadratic and cubic functions can each be viewed as being part of a
larger class of functions, the polynomials. A polynomial function has the form,

    f(n) = a_0 + a_1 n + a_2 n*2 + a_3 n*3 + ... + a_d n*d

where _a0, a1, ..., ad_ are constants, called the **coefficients** of the polynomial, and 
_a_d != 0_. Integer _d_, which indicates the highest power in the polynomial, is called  the 
**degree** of the polynomial.

**Summations**

A notation that appears again and again in the analysis of data structures and algorithms is the **summation**, which is defined as follows:

    b∑_i=a f(i) = f(a) + f(a+1) + f(a+2) + ... + f(b)

### The Exponential Function

    f(n) = b*n

That is, function **f(n)** assigns to the input argument **n** the value obtained by multiplying 
the base **b** by itself **n** times. As was the case with the logarithm function, the most 
common base for the exponential function in algorithm analysis is **b=2**.

**Exponent Rules**

    (b*a)*c = b*(ac)
    b*a b*c = b*(a+c)
    b*a/b*c = b*(a-c)


**Geometric Sums**

Suppose we have a loop for which each iteration takes a multiplicative factor longer than the 
previous one. This loop can be analyzed using the following proposition.

For any integer **n >= 0** and any real number a such that **a > 0** and **a != 1**, consider the 
summation:

    n∑_i=0 ai = 1 + a + *2 + ... + a*n

### Comparing Growth Rates

![Growth Rates of Fundamental Functions](1_function_growth_rates.png "Growth Rates of Fundamental Functions")