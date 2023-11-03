# legv8_emulator (ISU CS321 - Computer Architecture and Machine-Level Programming, 2021)

binary emulator for the LEGv8 ISA, written in Java. Emulator takes an assembled LEGv8 program (binary file) and executes it. 

# Compile
```
 javac -sourcepath /mnt/c/Users/Nika/legv8_emulator/src /mnt/c/Users/Nika/legv8_emulator/src/Main.java

```

# Run 
```
 java -classpath /mnt/c/Users/Nika/legv8_emulator/src Main test.legv8asm.machine

```
result excerpt (full output can be found in `output.legv8asm`):

```
main:
BL label5
ADDI X22, X22, #0
ADDI X23, X23, #48
LSR X27, X22, #3
B label56
label5:
ADDI X10, X10, #999
STUR X10, [X19, #0]
SUBI X10, X10, #13
STUR X10, [X19, #8]
SUBI X10, X10, #190
STUR X10, [X19, #16]
SUBI X10, X10, #11
```
# How It Works

The program processes the input binary file, interprets each 32-bit instruction, and determines its type.
It then generates a modified version of each instruction based on the analysis of branch instructions and their target labels.
* These modified instructions, including any added labels, are written to the "output.legv8asm" file.
* The program replaces branch instructions (instructions that represent jumps or branches in the code) with labels based on the dependencies it has identified.
Labels are added in the form "labelX," where X represents the position in the instruction list divided by 2.
   * For example, if the program identifies a branch instruction at position 10 that depends on a label at position 5, it replaces the branch instruction with "label5."
# Supported Instructions 
The `Instruction` class supports several LegV8 instructions based on their binary representations (listed below). The program then creates instances of specific instruction classes for further processing.

1. **B-Type Instructions** (Branch Instructions):
   - `B` (Branch): Binary pattern "000101"
   - `BL` (Branch with Link): Binary pattern "100101"

2. **D-Type Instructions** (Data Transfer Instructions):
   - `STURB` (Store Register Byte): Binary pattern "00111000000"
   - `LDURB` (Load Register Byte): Binary pattern "00111000010"
   - `STURH` (Store Register Halfword): Binary pattern "01111000000"
   - `LDURH` (Load Register Halfword): Binary pattern "01111000010"
   - `STURW` (Store Register Word): Binary pattern "10111000000"
   - `LDURSW` (Load Register Signed Word): Binary pattern "10111000100"
   - `STXR` (Store Exclusive Register): Binary pattern "11001000000"
   - `LDXR` (Load Exclusive Register): Binary pattern "11001000010"
   - `STUR` (Store Register): Binary pattern "11111000000"
   - `LDUR` (Load Register): Binary pattern "11111000010"

3. **CB-Type Instructions** (Conditional Branch Instructions):
   - `B.cond` (Conditional Branch): Binary pattern "01010100"
   - `CBZ` (Conditional Branch if Zero): Binary pattern "10110100"
   - `CBNZ` (Conditional Branch if Not Zero): Binary pattern "10110101"

4. **I-Type Instructions** (Immediate Instructions):
   - `ADDI` (Add Immediate): Binary pattern "1001000100"
   - `ANDI` (Bitwise AND Immediate): Binary pattern "1001001000"
   - `ANDIS` (Bitwise AND Immediate Shifted): Binary pattern "1011000100"
   - `ORRI` (Bitwise OR Immediate): Binary pattern "1011001000"
   - `SUBI` (Subtract Immediate): Binary pattern "1101000100"
   - `EORI` (Bitwise Exclusive OR Immediate): Binary pattern "1101001000"
   - `SUBIS` (Subtract Immediate Shifted): Binary pattern "1111000100"
   - `ANDIS` (Bitwise AND Immediate Shifted): Binary pattern "1111001000"

5. **IM-Type Instructions** (Immediate Move Instructions):
   - `MOVZ` (Move with Zero): Binary pattern "110100101"
   - `MOVK` (Move with Keep): Binary pattern "111100101"

6. **R-Type Instructions** (Register Instructions):
   - `FMULS` (Floating Point Multiply Single): Binary pattern "00011110001" (with specific shamt binary)
   - `FDIVS` (Floating Point Divide Single): Binary pattern "00011110001" (with specific shamt binary)
   - `FCMPS` (Floating Point Compare Single): Binary pattern "00011110001" (with specific shamt binary)
   - `FADDS` (Floating Point Add Single): Binary pattern "00011110001" (with specific shamt binary)
   - `FSUBS` (Floating Point Subtract Single): Binary pattern "00011110001" (with specific shamt binary)
   - `FMULD` (Floating Point Multiply Double): Binary pattern "00011110011" (with specific shamt binary)
   - `FDIVD` (Floating Point Divide Double): Binary pattern "00011110011" (with specific shamt binary)
   - `FCMPD` (Floating Point Compare Double): Binary pattern "00011110011" (with specific shamt binary)
   - `FADDD` (Floating Point Add Double): Binary pattern "00011110011" (with specific shamt binary)
   - `FSUBD` (Floating Point Subtract Double): Binary pattern "00011110011" (with specific shamt binary)
   - `AND` (Bitwise AND): Binary pattern "10001010000"
   - `ADD` (Add): Binary pattern "10001011000"
   - `SDIV` (Signed Integer Divide): Binary pattern "10011010110" (with specific shamt binary)
   - `UDIV` (Unsigned Integer Divide): Binary pattern "10011010110" (with specific shamt binary)
   - `MUL` (Multiply): Binary pattern "10011011000"
   - `SMULH` (Signed Multiply High): Binary pattern "10011011010"
   - `UMULH` (Unsigned Multiply High): Binary pattern "10011011110"
   - `ORR` (Bitwise OR): Binary pattern "10101010000"
   - `ADDS` (Add and Set Flags): Binary pattern "10101011000"
   - `STURS` (Store Register and Set Flags): Binary pattern "10111100000"
   - `LDURS` (Load Register and Set Flags): Binary pattern "10111100010"
   - `EOR` (Bitwise Exclusive OR): Binary pattern "11001010000"
   - `SUB` (Subtract): Binary pattern "11001011000"
   - `LSR` (Logical Shift Right): Binary pattern "11010011010"
   - `LSL` (Logical Shift Left): Binary pattern "11010011011"
   - `BR` (Branch): Binary pattern "11010110000"
   - `ANDS` (Bitwise AND and Set Flags): Binary pattern "11101010000"
   - `SUBS` (Subtract and Set Flags): Binary pattern "11101011000"
   - `STURD` (Store Register Doubleword): Binary pattern "11111100000"
   - `LDURD` (Load Register Doubleword): Binary pattern "11111100010"
   - `PRNT` (Print): Binary pattern "11111111101"
   - `PRNL` (Print New Line): Binary pattern "11111111100"
   - `DUMP` (Dump): Binary pattern "11111111110"
   - `HAULT` (Halt): Binary pattern "11111111111"

