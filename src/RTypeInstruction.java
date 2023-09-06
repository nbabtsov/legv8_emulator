public class RTypeInstruction extends Instruction
{
	private int Rd;
	private int Rn;
	private int Rm;
	private int shamt;
	public RTypeInstruction(String bitcode) {
		super(bitcode);
		Rd = findRd(bitcode);
		Rn = findRn(bitcode);
		Rm = findRm(bitcode);
		shamt = findShamt(bitcode);
	}
	//[(opcode)0,1,2,3,4,5,6,7,8,9,10(opcode),(Rm)11,12,13,14,15(Rm),(Shamt)16,17,18,19,20,21(Shamt),(Rn)22,23,24,25,26(Rn),(Rd)27,28,29,39,31(Rd)]
	@Override
	public String toString() {
		if(instructionName.equals("LSR") | instructionName.equals("LSL"))
		{
			return instructionName + " X" + Rd + ", "  + "X"+Rn+", #"+shamt;
		}
		if(instructionName.equals("BR"))
		{
			return instructionName + " X" + Rn;
		}
		if(instructionName.equals("DUMP"))
		{
			return instructionName;
		}
		if(instructionName.equals("PRNL") | instructionName.equals("HAULT"))
		{
			return instructionName;
		}
		if(instructionName.equals("PRNT"))
		{
			return instructionName + " X" + Rd;
		}
		return instructionName + " X" + Rd + ", "  + "X"+Rn+", X"+Rm;
	}

	private int findRd(String bitcode)
	{
		String machineCode = bitcode.substring(27,32);
		int toReturn = Integer.parseInt(machineCode,2);
		return toReturn;
	}
	private int findRm(String bitcode)
	{
		String machineCode = bitcode.substring(11,16);
		int toReturn = Integer.parseInt(machineCode,2);
		return toReturn;
	}
	private int findShamt(String bitcode)
	{
		String machineCode = bitcode.substring(16,22);
		int toReturn = Integer.parseInt(machineCode,2);
		return toReturn;
	}
	private int findRn(String bitcode)
	{
		String machineCode = bitcode.substring(22,27);
		int toReturn = Integer.parseInt(machineCode,2);
		return toReturn;
	}
}
