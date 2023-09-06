public class Tests {
    public static void main(String args[]) {
        Instruction potato = Instruction.instructionType("00010100110101000010100001101010");
        System.out.println(potato);//Should be b
        Instruction potato2 = Instruction.instructionType("00011110001101000010100001101010");
        System.out.println(potato2);//Should be r
        Instruction potato3 = Instruction.instructionType("00111000000101000010100001101010");
        System.out.println(potato3);//Should be D
    }
}
