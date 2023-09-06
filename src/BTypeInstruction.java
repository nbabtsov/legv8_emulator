public class BTypeInstruction extends Instruction
{

    private int label;
    public BTypeInstruction(String bitcode) {
        super(bitcode);
        label = findLabel(bitcode);
    }

    @Override
    public String toString() {
        return instructionName + " " + label;
    }

    private int findLabel(String bitcode)
    {
        String machineCode = bitcode.substring(6); //[0,1,2,3,4,5(End of bitcode),6(Cause it's inclusive),7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
        //Turn the number from binary to whatever form it should be
        int toReturn;
        if(machineCode.substring(0,1).equals("1"))
        {
            StringBuffer machineCodeBuffer = new StringBuffer(machineCode);
            toReturn = Integer.parseInt(findTwoscomplement(machineCodeBuffer));
            toReturn = (int) Long.parseLong(String.valueOf(toReturn), 2);
            toReturn*=-1;
        }else
        {
            toReturn = (int) Long.parseLong(machineCode, 2);
        }
        return toReturn;
    }
}
