public class ITypeInstruction extends Instruction
{
    private int Rd;
    private int Rn;
    private int ALU_immediate;
    public ITypeInstruction(String bitcode) {
        super(bitcode);
        Rd = findRd(bitcode);
        Rn = findRn(bitcode);
        ALU_immediate = findALU_immediate(bitcode);
    }

    private int findRd(String bitcode)
    {
        String machineCode = bitcode.substring(27,32);
        int toReturn = Integer.parseInt(machineCode,2);
        return toReturn;
    }
    private int findRn(String bitcode)
    {
        String machineCode = bitcode.substring(22,27);
        int toReturn = Integer.parseInt(machineCode,2);
        return toReturn;
    }
    private int findALU_immediate(String bitcode)
    {
        String machineCode = bitcode.substring(10,22);
        int toReturn = Integer.parseInt(machineCode,2);
        return toReturn;
    }
    @Override
    public String toString() {
        return instructionName + " X" + Rd + ", " + "X"+Rn+", #"+ALU_immediate;
    }
}
