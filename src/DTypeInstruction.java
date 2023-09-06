public class DTypeInstruction extends Instruction
{
    private int Rt;
    private int Rn;
    private int op;
    private int DT_address;
//[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,39,31] general
    //[(op)0,1,2,3,4,5,6,7,8,9,10(op),(DT_address)11,12,13,14,15,16,17,18,19(DT_address),(op)20,21(op),(Rn)22,23,24,25,26(rn),(Rt)27,28,29,39,31(Rt)] for D

    public DTypeInstruction(String bitcode) {
        super(bitcode);
        Rt = findRt(bitcode);
        Rn = findRn(bitcode);
        op = findOp(bitcode);
        DT_address = findDT_address(bitcode);
    }
    private int findRt(String bitcode)
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
    private int findOp(String bitcode)
    {
        String machineCode = bitcode.substring(20,22);
        int toReturn = Integer.parseInt(machineCode,2);
        return toReturn;
    }
    private int findDT_address(String bitcode)
    {
        String machineCode = bitcode.substring(11,20);
        int toReturn = Integer.parseInt(machineCode,2);
        return toReturn;
    }
    @Override
    public String toString() {
        return instructionName + " X" + Rt + ", " + "[" + "X"+Rn+", #"+DT_address + "]";
    }
}
