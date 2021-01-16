# 4 The von Neumann Model

### 4.1 Name the five components of the von Neumann model. For each component, state its purpose.

#### Memory

Memory is made up of a (usually large) number of locations, each uniquely identifiable and each having the ability to
store a value. That is, memory stores data and programs.

#### Processing Unit

Carries out the actual processing of information in the computer. The processing unit in a modern computer can consist
of many sophisticated complex functional units, each performing one particular operation (divide, square root, etc.)

#### Input and Output

In order for a computer to process information, the information must get into the computer. In order to use the results
of that provessing, those results must be displayed in some fashion outside the computer. Many devices exist for the
purposes of input and output (e.g. keyboard and monitor).

#### Control Unit

The control unit the like the conductor of an orchestra; it is in charge of making all the other parts play together. As
we will see when we describe the step-by-step process of executing computer program, it is the control unit that keeps
track of both where we are within the process of executing the program and where we are in the process of executing each
instruction.

### 4.2 Briefly describe the interface between the memory and the processing unit. That is, describe the method by which the memory and the processing unit communicate.

The interface is described by the first step of the instruction cycle: the FETCH. The FETCH itself is made up of three
steps - here the first step is relevant in terms of the interface between memory and processing unit:

Load the memory's address register (*MAR*) with the contents of the PC, and simultaneously increment the program
counter (*PC*).

In order for the contents of the PC to be loaded into the MAR, the finite state machine must assert GatePC and LD.MAR.
GatePC connects the PC to the processor bus. LD.MAR, the wirte enable signal of the MAR register, latches the contents
of the bus into the MAR at the end of the current clock cycle.

### 4.3 What is misleading about the name *program counter*? Why is the name *instruction pointer* more insightful?

The program counter does not maintain a count of any sort. The value stored in the program counter is the address of the
next instruction to be processed. Hence the name 'Instruction Pointer' is more appropriate for it.

### 4.4 What is the *word

length* of a computer? How does the word length of a computer affect what the computer is able to compute? That is, is
it a valid argument, in light of what you learned in Chapter 1, to say that a computer with a larger word size can
process more information and therefore is capable of computing more than a computer with a smaller word size?

The size of the quantities normally processed by the arithmetic and logical unit (*ALU*) is often referred to as the *
word length* of the computer, and each element is referred to as *word*. Each ISA has its own word length, depending on
the intended use of the computer. larger word lengths means we can process more data faster in the processor, which is
greatly needed as we advance computing technology.

### 4.5 The following table represents a small memry. Refer to this table for the following questions.

| Address | Data                |
|---------|---------------------|
| 0000    | 0001 1110 0100 0011 |
| 0001    | 1111 0000 0010 0101 |
| 0010    | 0110 1111 0000 0001 |
| 0011    | 0000 0000 0000 0000 |
| 0100    | 0000 0000 0110 0101 |
| 0101    | 0000 0000 0000 0110 |
| 0110    | 1111 1110 1101 0011 |
| 0111    | 0000 0110 1101 1001 |

#### a. What binary value does location 3 contain? Location 6?

* 3 -> 0000 0000 0000 0000
* 6 -> 1111 1110 1101 0011

#### b. The binary value within each location can be interpreted in many ways. We have seen that binary values can represent unsigned numbers, 2's complement signed numbers, floating point numbers, and so forth.

##### (1) Interpret location 0 and location 1 as 2's complement integers.

* 0001 1110 0100 0011 -> 7747
* 1111 0000 0010 0101 -> -4059

##### (2) Interpret location 4 as an ASCII value.

* 0000 0000 0110 0101 -> 'e'

##### (3) Interpret locations 6 and 7 as an IEEE floating point number. Location 6 contains number \[15:0\]. Location contains number \[31:16\].

* 1 1111 1101 1010 0110 000 0110 1101 1001 -> -140251331087326225280478234625328545792

##### (4) Interpret location 0 and location 1 as unsigned integers.

* 0001 1110 0100 0011 -> 7747
* 1111 0000 0010 0101 -> 61477

#### c. In the von Neumann model, the contents of a memory location can also be an instruction. If the binary pattern in location 0 were interpreted as an instruction, what instruction would it represent?

* ADD

#### d. A binary value can also be interpreted as a memory address. Say the value stored in location 5 is a memory address. To which location does it refer? What binary value does that location contain?

* It would refer to location 6
* Location 6 contains the value 1111 1110 1101 0011

### 4.6

A computer program consists of a set of instructions, each specifiying a well-defined piece of work for the computer to
carry. The *instruction* is the smallest piece of work specified in a computer program. It's made up of two parts, the *
opcode* (what the instruction does) and the *operands* (who it is to do it to).

### 4.7

* 60 opcodes = 6 bits
* 32 registers = 5 bits
* So number of bits required for IMM = 32 - 6 - 5 - 5 = 16
* Since IMM is a 2's complement value, its range is -2^15 ... (2^15 -1) = -32768 ... 32767

### 4.8

* a) 8 bits
* b) 7 bits (3 times)
* c) 3 bits

### 4.9

The second important operation performed during the FETCH phase is the loading of the address of the next instruction
into the program counter.

### 4.

From the examples 4.1, 4.2 and 4.5 we have the following instructions: ADD (R6, R2, R6), LDR (R2, R3, 6), JMP (R3)

|     | Fetch Instruction | Decode | Evaluate Address | Fetch Data | Execute | Store Result |
|-----|-------------------|--------|------------------|------------|---------|--------------|
| PC  | ALL               |        |                  |            | JMP     |              |
| IR  | ALL               |        |                  |            |         |              |
| MAR | ALL               |        |                  | LDR        |         |              |
| MDR | ALL               |        |                  | LDR        |         |              |

* The PC gets overwritten for every instruction fetched and is written during the execution phase of the JMP instruction
  whenever the JMP instruction is taken PC address will be modified.
* MAR is required for all the above said instruction to hold the address of the register.
* MDR also is required for all the above said instructions to store the data of the register.
* During the fetch operation of LDR instruction, register R2 is loaded with the contents of memory (R3+6). Hence, to
  load address of the register is required which is kept in MAR and to load the data MDR is required.
* Thus Opcode for the registers during corresponding phase of instruction cycle is given.

### 4.11 The phases of the instruction cycle are:

* (a) Fetch: Get instruction from memory. Load address of next instruction in the Program Counter.
* (b) Decode: Find out what the instruction does.
* (c) Evaluate Address: Calculate address of the memory location that is needed to process the instruction.
* (d) Fetch Operands: Get the source operands (either from memory or register file).
* (e) Execute: Perform the execution of the instruction.
* (f) Store Result: Store the result of the execution to the specified destination.

### 4.12

#### ADD

* **Fetch**: Get instruction from memory. Load next address into PC.
* **Decode**: It is here that it is determined that the instruction is an *Add* instruction.
* **Evaluate Address**: No memory operation so NOT REQUIRED.
* **Fetch Operands**: Get operands from register file.
* **Execute**: Perform the add operation.
* **Store Result**: Store result in the register file.

#### LDR

* **Fetch**: Get instruction from memory. Load next address into PC.
* **Decode**: It is here that it is determined that the instruction is an *Load Base+offset* instruction.
* **Evaluate Address**: Calculate the memory address by adding the Base register with the sign extended offset.
* **Fetch Operands**: Get value from the memory.
* **Execute**: No operation needed so NOT REQUIRED.
* **Store Result**: Store value loaded into the register file.

#### JMP

* **Fetch**: Get instruction from memory. Load next address into PC.
* **Decode**: It is here that it is determined that the instruction is an *Jump* instruction.
* **Evaluate Address**: No memory operation so NOT REQUIRED.
* **Fetch Operands**: Get the base register from register file.
* **Execute**: Store the value in PC.
* **Store Result**: NOT REQUIRED.

### 4.13

|                     | F   | D | EA | FO  | E  | SR  |       |
|---------------------|-----|---|----|-----|----|-----|-------|
| x86: ADD [eax] edx  | 100 | 1 | 1  | 100 | 1  | 100 | = 303 |
| LC3: ADD R6, R2, R6 | 100 | 1 | -  | 1   | 1  | 1   | = 104 |

**x86 explained**:

* Fetch step fetches the instruction from the memory. Therefore, a memory access is required and it uses 100 clock
  cycles.
* Decoding the instruction requires only 1 clock cycle.
* The instruction uses *eax* register to calculate the address of the memory location. Therefore, it requires 1 clock
  cycle instead of 100 clock cycles.
* Fetching the operand requires 100 clock cycles.
* Executing the instruction requires 1 clock cycles.
* Finally, the result is stored in the memory. Therefore, it requires 100 clock cycles.
* Total number of clock cycles required to execute the instruction is **303**.

### 4.14

#### JMP: 1100 0000 1100 0000

* **Fetch**: Get instruction from memory. Load next address into PC.
* **Decode**: It is here that it is determined that the instruction is an *Jump* instruction.
* **Evaluate Address**: No memory operation so NOT REQUIRED.
* **Fetch Operands**: Get the base register from register file.
* **Execute**: Load PC with the base register value, x369C.
* **Store Result**: NOT REQUIRED.

### 4.15

Once the RUN latch is cleared, the clock stops, so no instructions can be processed. Thus, no instruction can be used to
set the RUN latch. In order to re-initiate the instruction cycle, an external input must be applied. This can be in the
form of an interrupt signal or a front panel switch, for example.

### 4.16

* a) 1 second = 1.000.000.000 nanosecond => 500 million machine cycles occur each second
* b) 500.000.000 / 8 = 62.500.000 => 62,5 million instructions can be processed each second.
* Once the first instruction reaches the last phase of the instruction, an instruction will be completed every cycle.
  One instruction will be completed in each cycle except for the initial delay. If the delay is ignored, the number of
  instructions that will be executed each second is the same as the number of machine cycles in a second, i.e. 5*10^8.