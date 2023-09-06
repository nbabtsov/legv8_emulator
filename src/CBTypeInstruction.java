public class CBTypeInstruction extends Instruction
{
    private int Rt;
    private int label;

    public CBTypeInstruction(String bitcode) {
        super(bitcode);
        label = findLabel(bitcode);
        Rt = findRt(bitcode);
    }

    @Override
    public String toString() {
        if(instructionName.equals("CBNZ") | instructionName.equals("CBZ"))
        {
            return instructionName + " X" + Rt + ", " + label;
        }
        if(instructionName.equals("B.cond"))//Gotta be careful cause B.cond can go backwards and is in 2's complement
        {
            if(Rt == 0)
            {
                return "B.EQ" + " " + label;
            }
            if(Rt == 1)
            {
                return "B.NE" + " " + label;
            }
            if(Rt == 2)
            {
                return "B.HS" + " " + label;
            }
            if(Rt == 3)
            {
                return "B.LO" + " " + label;
            }
            if(Rt == 4)
            {
                return "B.MI" + " " + label;
            }
            if(Rt == 5)
            {
                return "B.PL" + " " + label;
            }
            if(Rt == 6)
            {
                return "B.VS" + " " + label;
            }
            if(Rt == 7)
            {
                return "B.VC" + " " + label;
            }
            if(Rt == 8)
            {
                return "B.HI" + " " + label;
            }
            if(Rt == 9)
            {
                return "B.LS" + " " + label;
            }
            if(Rt == 10)
            {
                return "B.GE" + " " + label;
            }
            if(Rt == 11)
            {
                return "B.LT" + " " + label;
            }
            if(Rt == 12)
            {
                return "B.GT" + " " + label;
            }
            if(Rt == 13)
            {
                return "B.LE" + " " + label;
            }
        }
        return instructionName + " X" + Rt + ", " + label;
    }

    private int findLabel(String bitcode)
    {
        String machineCode = bitcode.substring(8,27); //[0,1,2,3,4,5(End of bitcode),6(Cause it's inclusive),7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
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
    private int findRt(String bitcode)
    {
        String machineCode = bitcode.substring(27); //[0,1,2,3,4,5(End of bitcode),6(Cause it's inclusive),7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,()27,28,29,39,31]
        //Turn the number from binary to whatever form it should be
        int toReturn = Integer.parseInt(machineCode,2);
        return toReturn;
    }
}
