# Programming

### 6.1

* Yes, it's possible by constructing an infinite loop, thus avoiding finiteness.
* In particular we're setting a condition (i.e. a sentinel) to break the loop, which is never met.
    - e.g. increment variable1 by 1 as long as variable2 is greater than 0 - if variable2 does not get decremented in
      the loop, the program runs forever and disqualifies as valid algorithm

* AND R0 R0 #0
* AND R1 R1 #0
* ADD R1 R1 #10
* ADD R0 R0 #1
* ADD R1 R1 #-1
* BRp #-3
* TRAP HALT

### 6.2

* AND R0 R0 #0
* AND R1 R1 #0
* ADD R0 R0 #4
* ADD R1 R1 #5
* NOT R1 R1
* ADD R1 R1 #1
* ADD R2 R1 R0

### 6.3

; R1 holds the new vector ; R0 holds current vector

; Init R1

* x3000: AND R1 R1 x0
* x3001: ADD R1 R1 x1
* x3002: LDI R0 x00C5 ; Load the value of the now busy machine
* x3003: ADD R1 R1 R1 ; Repeatedly add R1 to itself
* x3004: ADD R0 R0 xFFFF ; decrement R0
* x3005: BRp x3803 (#-3) ; check if R0 is zero (base case)
* x3006: LDI R0 x00C2 ; Load the Busyness vector ; Do OR-Procedure
* x3007: NOT R0 R0
* x3008: NOT R1 R1
* x3009: AND R1 R1 R0
* x300A: NOT R1 R1
* x300B: STI R1 x00B7 ; Store new Busy vector
* x300C: TRAP HALT

### 6.4

* x3000: AND R0 R0 #0
* x3001: NOT R2 R2
* x3002: ADD R2 R2 #1
* x3003: ADD R1 R1 R2
* x3004: BRz #4
* x3005: BRn #2
* x3006: ADD R0 R0 #1
* x3007: BRnzp #1
* x3008: ADD R0 R0 #-1
* x3009: TRAP HALT

### 6.5

* the first one
* it's preferable to take the smaller number as iteration counter for the loop to save processing time

### 6.6

* x3000: LDI R1 xXXXX
* x3001: LDI R2 xXXXX ; Determine the bigger number START
* x3002: AND R0 R0 #0
* x3003: NOT R2 R2
* x3004: ADD R2 R2 #1
* x3005: ADD R1 R1 R2
* x3006: BRz #4
* x3007: BRn #2
* x3008: ADD R0 R0 #1
* x3009: BRnzp #1
* x300A: ADD R0 R0 #-1 ; Determine the bigger number END
* x300B: BRn #5 ; if flag negative -> R2 is bigger -> jump to else condition
* x300C: ADD R1 R1 R1
* x300D: ADD R2 R2 #-1
* x300E: BRp #-3 ; if R2 positive jump back to x300C
* x300F: ADD R3 R1 #0 ; write the result into R3
* x3010: BRnzp xXXX ; Jump to HALT
* x3011: ADD R2 R2 R2
* x3012: ADD R1 R1 #-1
* x3013: BRp #-3 ; if R1 positive jump back to x3010
* x3014: ADD R3 R2 #0 ; write the result into R3
* x3015: TRAP HALT

### 6.7

* x3001: LEA R0 x000C ; loads address x300E
* x3002: LEA R1 x0010 ; loads address x3013
* x3003: AND R2 R2 #0 ; Reset R2
* x3004: LD R2 x0013 ; loads content of x3018 (length of the list)
* x3005: LDR R3 R0 #0 ; loads content of R0
* x3006: LDR R4 R1 #0 ; loads content of R1
* x3007: ADD R3 R3 R4 ; add R3 and R4
* x3008: STR R3 R0 #0 ; store the result in R0
* x3009: ADD R0 R0 #1 ; add 1 to R0
* x300A: ADD R1 R1 #1 ; add 1 to R1
* x300B: ADD R2 R2 #-1 ; subtract 1 of R2
* x300C: BRp xFFF8 ; if positive jump back (PC - xFFF8)
* x300D: TRAP HALT
* x300E: BR
* x300F: BR
* x3010: BR
* x3011: BR
* x3012: BR
* x3013: BR
* x3014: BR
* x3015: BR
* x3016: BR
* x3017: BR
* x3018: BR

This program adds together the corresponding elements of two lists of numbers (vector addition). One list starts at
address x300e and the other starts at address x3013. The program finds the length of the two lists at memory location
x3018. The first element of the first list is added to the first element of the second list and the result is stored
back to the first element of the first list; the second element of the first list is added to the second element of the
second list and the result is stored back to the second element of the first list; and so on.

### 6.8

Otherwise the register might already hold some number/value that leads to a wrong end result.

### 6.9

* x3000: AND R1 R1 #0
* x3001: ADD R1 R1 #100
* x3002: LD R0 Z
* x3003: TRAP OUT
* x3004: ADD R1 R1 #-1
* x3005: BRp #-3
* x3006: TRAP HALT

### 6.10

* x3000: AND R2 R2 #1
* x3001: ADD R2 R2 #-1
* x3002: BRz xXXXX (jump to odd branch)
* ...

### 6.11


