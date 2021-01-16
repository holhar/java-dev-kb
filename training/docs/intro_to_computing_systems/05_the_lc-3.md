# 5 The LC-3

### 5.1

#### Operate Instructions

* ADD - addressing mode: Source register or immediate mode
* NOT - addressing mode: Source register or immediate mode

#### Data Movement Instructions

* LEA - addressing mode: immediate mode

#### Control Instructions

* JMP - register addressing mode (program jumps unconditionally to the location specified by the contents of the base
  register)

### 5.2

* The addressability of the memory indicates the number of bits in each location
* The MDR is used to store the data processed in the execution of the instruction
* The size of the MDR is the same size of the memory addressability bits
* The size of the MAR depends on the number of the memory locations

### 5.3

* *Sentinel*: a sentinel describes a certain condition that must be met to break the loop

### 5.4

* a) 8 bits
* b)
    - the offsets of the PC use two's complement representation, which requires a minimum of 6 bits
    - the two's complement representation of the address allows +22 or -21 locations of the Load (LD) or Store (ST)
      instruction because the PC increments its value before adding the offset
    - so, the number of branch instructions needed to require the PC-relative offset is 6 bits
* c) 6 => 110

### 5.5

* a) An addressing mode is a mechanism for specifying where the operand is located.
* b) An operand can generally be found in one of three places: in memory, in a register, or as a part of the
  instruction. If the operand is a part of the instruction, we refer to it as a *literal* or as an *immediate* operand.
* c)
    - immediate (or literal) => located in the operand
    - register => located in register
    - PC-relative, indirect, and Base+offset => located in memory
* d) Add R2, R0, R1 => register addressing mode

### 5.6

* a) 0101 011 010 1 00100
* b) 0101 011 010 1 01100
* c) 0101 011 010 1 11111
* d) Not possible; using the immediate mode we can only use 5 bits as operand. To check if machine 6 is busy we need an
  operand with 7 bits.

### 5.7

* 4 bits => 1111 => 15

### 5.8

* Yes, the instruction format is not big enough to increase the bit value for addressing registers with such a high
  value. We would need 6 bits, for the current format we have only available 3 bits.

### 5.9

* Option c (0000 000 000000000) is correct - it's a BRANCH control instruction
    - i.e. no condition codes are evaluated and no additional value is added to the PC for the next execution cycle =>
      so it's a Branch that is never taken!

### 5.10

* A: 0000 111 1 0101 0101 => BRnzp xFF55 (PC+1-171)
    - unconditionally branch instruction -> the program branches to the location specified by adding the sign-extended
      PCoffset9 field to the incremented PC
* B: 0100 1 111 0101 0101 => JSRimm11 xFF55 (PC+1-171)
    - Subroutine instruction (i.e. a function call) saving the address of the instruction following JSR into R7 - then
      jump to QUEUE

### 5.11

* No, it's not possible. We could do it with 2 instructions by using the immediate addressing mode (where we can
  subtract directly 16 max)

### 5.12

* We can only trust the result if R0/2 < 16384 or else we got an overflow

### 5.13

* a) 0001 011 010 1 00000 (ADD R3, R2, #0)
* b)
    - 1001 011 011 111111 (NOT R3, R3)
    - 0001 011 011 0 00001 (ADD R3, R3, #1)
    - 0001 001 010 0 00011 (ADD R1, R2, R3)
* c) 0001 001 001 1 00000 (ADD R1, R1, #0)
* d) not possible - the result can not be negative AND zero
* e) 0101 010 010 1 00000 (AND R2, R2, #0)

### 5.14

Perform the OR of the contents of R1 and R2 and put the result in R3

* 1001 100 001 111111 (NOT R4, R1) - Inverse R1 and store the result in R4
* 1001 101 010 111111 (NOT R5, R2) - Inverse R2 and store the result in R5
* 0101 110 100 000 101 (AND R6, R4, R5) - Add R4 and R5 and store the result in R5
* 1001 011 110 111111 (NOT R3, R6) - Inverse R6 and store the result in R3

### 5.15

| Address                     | Data                                | What happens?                                                                                      |
|-----------------------------|-------------------------------------|----------------------------------------------------------------------------------------------------|
| 0011 0001 0000 0000 (x3100) | 1110 001 000100000 (LEA R1 0x20)    | R1 <- 0x3121 (load into R1 the computed address: PC+1+20=x3121)                                    |
| 0011 0001 0000 0001 (x3101) | 0010 010 000100000 (LD R2 0x20)     | R2 <- mem\[0x3122\] (load into R2 the memory content that is located in the computed address: PC+1+20) |
| 0011 0001 0000 0010 (x3102) | 1010 011 000100000 (LDI R3 0x20)    | R3 <- mem\[mem\[0x3123\]\] (an address is loaded into R3 that is located at the computed address: PC+1+20) |
| 0011 0001 0000 0011 (x3103) | 0110 100 010 000001 (LDR R4 R2 0x1) | R4 <- mem\[R2 + 0x1\] (the contents of memory at the computed address (R2+1) are loaded into R4        |
| 0011 0001 0000 0100 (x3104) | 1111 0000 0010 0101 (TRAP HALT)     | Halt execution and print a message on the console                                                  |

### 5.16

* a) Addressing mode to load value from an address less thant +/- 2^8 locations away is **PC-relative addressing mode**
    - to load the value from an address less than +/- 2^8 locations away PC-relative addressing mode is used
    - because, in PC-relative addressing mode the maximum offset range is 9 bits, i.e. 0 to 8. The 9 bits allow the
      offset range between -256 to 255 forming any address within the range
    - thus PC-relative addressing mode can address +/- 2^8 memory locations
* b) Addressing mode to load a value from an address more than 2^8 locations away is **Indirect Addressing Mode**
    - to load the value from an address greater than 2^8 locations away, indirect addressing mode is used
    - because, in indirect addressing mode. the data register will load the value present in the memory location pointed
      by PC + Offset
    - since PC address is limited to 2^8. and to extend PC address more than 2^8 memory locatoins add offset with PC to
      get required memory location to get address greater than 2^8
    - Sinde PC address is limited to 2^8, and to extend PC address more than 2^8 memory locations add offset with PC to
      get required memory location to get address greater than 2^8
* c) Addressing mode to load array of sequential address is **PC-relative addressing mode**
    - to load an array of sequential addresses PC-relative addressing mode is used. But there is one limitation: the
      maximum offset range is 9 bit, i.e. 0 to 8
    - the 9 bits allow the offset range between -256 to +255 forming any address within the range

### 5.17 times of read or write requests to memory during:

* LD instruction: 2 times, once to fetch the instruction, once to fetch the data
* LDI instruction: 3 times, once to fetch the instruction, once to fetch the data address, and once to fetch the data
* LEA instruction: 1 times, once to fetch the instruction.

### 5.18

* LDI: 2 times
* STI: 2 times
* TRAP: 1 time

### 5.19

* range = -(2^6) till (2^6)-1 = -64 till 63

### 5.20

* 7 bits

### 5.21

* 2^8 = 256 trap routines

### 5.22

* x3010: 1110 011 0 0011 1111 (LEA R3 0x3F) - R3 <- x3050
* x3011: 0110 100 011 000000 (LDR R4 R3 0x0) R4 <- mem\[R3 + 0x0\]
* x3012: 0110 110 100 000000 (LDR R6 R4 0x0) R6 <- mem\[R4 + 0x0\]

=> R6 gets assigned the address x70A4, which contains x123B

=> Shortcut instruction 1110 110 0011 1111 (LEA R6 0x3F)

### 5.23

* x30FF: 1110 001 0 0000 0001 (LEA R1 0x1) => R1 <- x3101
* x3100: 0110 010 001 0 00010 (LDR R2 R1 0x2) => R2 <- mem\[R1 + 0x2] (x1482)
* x3101: 1111 0000 0010 0101 (TRAP 0x25) => HALT
* x3102: 0001 0100 0100 0001 (ADD R2 R1 R1) => R2 <- R1 + R1
* x3103: 0001 0100 1000 0010 (ADD R2 R2 R2) => R2 <- R2 + R2

### 5.24

* LDR (offset with 6 bits: 011111)
    - largest address with 2's complement binary: 31 steps (x1F), i.e. x3042
    - largest/smallest address with unsigned binary: 64 steps (x3F), i.e. x4074/x4011

### 5.25

- x1001: 1001 010 010 111111 (NOT R2 R2)
- x1002: 0001 010 010 1 00001 (ADD R2 R2 #1)
- x1003: 0001 100 011 0 00 010 (ADD R4 R3 R2)
- x1004: 0000 100 0 0000 0010 (BRn 0x2)
- x1005: 0000 010 0 0000 0011 (BRz 0x3)
- x1006: 0000 001 0 0000 0010 (BRp 0x4)
- x1007: 0110 001 010 0 00000 (LDR R1 R2 0x0)
- x1008: 1111 0000 0010 0101 => HALT
- x1009: 0101 001 001 1 00000 (AND R1 R1 0x0)
- x100A: 1111 0000 0010 0101 => HALT
- x100B: 0110 001 011 0 00000 (LDR R1 R2 0x0)
- x100C: 1111 0000 0010 0101 => HALT

### 5.26

- 16 new instructions
- 16 registers
- 1 byte addressability
- 64K bytes total memory
- instruction size 16 bits (with the same five fields for instruction encoding)

* a) 16 bits
* b) (2^4)-1 = 15
* c) 2^7 * 2^5 = 2 ^12 = 4096
* d) 7 bits => (2^6)-1 = 31

### 5.27

* 4 values:
    - init value: xAAAA
    - R2 <- R1+14 = x30F4
    - R2 <- 0 = x0000
    - R2 <- R2+4 = x0005

### 5.28

* x3000: 0010 0000 0000 0010 (LD R0 0x2) => R0 <- mem\[x3003\] = NOP
* x3001: 1011 0000 0000 0010 (STI R0 0x2) => x3004 <- R0 = NOP
* x3002: 1111 0000 0010 0101 (TRAP HALT) => HALT
* x3003: 0000 0000 0100 1000 (BR 0x48) => NOP
* x3004: 1111 0011 1111 1111 (TRAP xF3FF) => contains the next instruction to be fetched

REPLACED BY:

* x3000: 0010 0000 0000 0010 (LD R0 0x2) => R0 <- mem\[x3003\] = NOP
* x3001: 1110 001 000000011 (LEA R1 0x3) => R1 <- 0x3005
* x3002: 0111 000 001 000000 (STR R0 R1 0x0) => 0x3005 <- R0 = NOP
* x3003: 1111 0000 0010 0101 (TRAP HALT) => HALT
* x3004: 0000 0000 0100 1000 (BR 0x48) => NOP
* x3005: 1111 0011 1111 1111 (TRAP xF3FF) => contains the next instruction to be fetched

### 5.29

* (a)
    - LDR R2, R1, #0 ;load R2 with contents of location pointed to by R1
    - STR R2, R0, #0 ;store those contents into location pointed to by R0
* (b) The constituent micro-ops are:
    - MAR <- SR
    - MDR <- Mem\[MAR\]
    - MAR <- DR
    - Mem\[MAR\] <- MDR

### 5.30

R0 = R1 + 1 as we're dealing with 2's complement binary operands (R1 is only inverted but not incremented by one)

### 5.31

0x1000: 0001 101 000 1 11000

### 5.32

at position x3052 an ADD R0 R0 0x1 is processed - as we do not know what is stored in R0 we can not determine what will
be set in the condition codes

### 5.33

- x3000: 0101 111 111 1 00000 (AND R7 R7 #0) => R7 <- 0x0
- x3001: 0001 110 111 1 00001 (ADD R6 R7 0x1) => R6 <- 0x1
- x3002: 0101 100 101 0 00 110 (AND R4 R5 R6) => ???
- x3003: 0000 010 0 0000 0001 (BRz 0x1) => BRz x3005
- x3004: 0001 000 000 1 00001 (ADD R0 R0 0x1) => R0 <- R0 + 1
- x3005: 0001 110 110 0 00 110 (ADD R6 R6 R6) => R6 <- R6 + R6
- x3006: 0001 111 111 1 00001 (ADD R7 R7 0x1) => R7 <- R7 + 1
- x3007: 0001 001 111 1 11000 (ADD R1 R7 ) => R1 <- R7 - 8
- x3008: 0000 100 1 11111001 (BRn 1x7) => BRn x3002
- x3009: 0101 111 111 1 00000 (AND R7 R7 0x0) => R7 <- 0x0

=> It can be inferred that R5 has exactly 5 of the lower 8 bits = 1. // TODO: I don't get this...

### 5.34

The Reg File, IR (all instructions need it because an instruction itself starts from IR), and the ALU implement the NOT
function, along with NZP and the logic which goes with it

### 5.35

The IR, SEXT unit, SR2MUX, Reg File and ALU implement the ADD instruction, along with NZP and the logic which goes with
it.

### 5.36

Memory, MDR, MAR, IR, PC, Reg File, the SEXT unit connected to IR\[8:0\], ADDR2MUX, ADDR1MUX set to PC, alongwith the
ADDER they connect to, and MAXMUX and GateMARMUX implement the LD instruction, alongwith NZP and the logic which goes
with it.

### 5.37

Memory, MDR, MAR, IR, PC, Reg File, the SEXT unit connected to IR\[8:0\], ADDR2MUX, ADDR1MUX set to PC, alongwith the
ADDER they connect to, and MAXMUX and GateMARMUX implement the LDI instruction, alongwith NZP and the logic which goes
with it.

### 5.38

Memory, MDR, MAR, IR, Reg File, the SEXT unit connected to IR\[5:0\], ADDR2MUX is set to 0, ADDR1MUX is set to SR1 out,
along with the ADDER they connect to, and MAXMUX and GateMARMUX implement the LDR instruction, along with NZP and the
logic which goes with it.

### 5.39

IR, PC, Reg File, the SEXT unit connected to IR\[8:0\], ADDR2MUX, ADDR1MUX set to PC, alongwith the ADDER they connect
to, and MAXMUX and GateMARMUX implement the LEA instruction, alongwith NZP and the logic which goes with it.

### 5.40

The signal A indicates if the instruction in IR is a taken branch instruction.

### 5.41

* a) **Y is the P condition code**: the global bus is tested for (\[15\] == 0) AND (at least 1 bit in \[14:0\] == 1),
  which indicates if the value on the bus is positive/non-zero. The P condition code register (a single gated D-Latch)
  will store this value only if it is write-enabled by X, which tests the opcode (IR\[15:12\]) for any of the 7
  instructions that write to the GPRs.
* b) Yes. The error is that the logic should not have the logic gate A. X should be one whever the opcode field of the
  IR matches the opcodes which change the condition code registers. The problem is that X is 1 for the BR opcode (0000)
  in the given logic.

### 5.42

* MUL is most useful as the other functions can be more easily emulated by other instructions (especially the
  multiplication of higher numbers would need a high amount of ADD instructions)
* MOVE can be emulated by using ADD and AND instruction
* NAND can be emulated by using AND followed by NOT instruction
* SHFL can be emulated by using the MUL instruction