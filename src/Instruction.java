public class Instruction
{
	private String bitcode;
	protected String instructionName;
	public Instruction(String bitcode)
	{
		this.bitcode=bitcode;
		this.instructionName=findName(bitcode);
	}
	public static Instruction instructionType(String bitcode)
	{
		String bType = bitcode.substring(0,6);
		String dType = bitcode.substring(0,11);
		String cbType = bitcode.substring(0,8);
		String iType = bitcode.substring(0,10);
		String imType = bitcode.substring(0,9);
		String rType = bitcode.substring(0,11);

		if(bType.equals("000101")/*B*/ || bType.equals("100101")/*BL*/)
		{
			return new BTypeInstruction(bitcode);
		}
		else if(dType.equals("00111000000")/*STURB*/ || dType.equals("00111000010")/*LDURB*/|| dType.equals("01111000000")/*STURH*/|| dType.equals("01111000010")/*LDURH*/|| dType.equals("10111000000")/*STURW*/|| dType.equals("10111000100")/*LDURSW*/|| dType.equals("11001000000")/*STXR*/|| dType.equals("11001000010")/*LDXR*/|| dType.equals("11111000000")/*STUR*/|| dType.equals("11111000010")/*LDUR*/)
		{
			return new DTypeInstruction(bitcode);
		} else if(cbType.equals("01010100")/*B.cond*/ || cbType.equals("10110100")/*CBZ*/|| cbType.equals("10110101")/*CBNZ*/)
		{
			return new CBTypeInstruction(bitcode);
		} else if(iType.equals("1001000100")/*ADDI*/ || iType.equals("1001001000")/*ANDI*/|| iType.equals("1011000100")/*ANDIS*/|| iType.equals("1011001000")/*ORRI*/|| iType.equals("1101000100")/*SUBI*/|| iType.equals("1101001000")/*EORI*/|| iType.equals("1111000100")/*SUBIS*/|| iType.equals("1111001000")/*ANDIS*/)
		{
			return new ITypeInstruction(bitcode);
		} else if(imType.equals("110100101")/*MOVZ*/ || imType.equals("111100101")/*MOVK*/)
		{
			return new IMTypeInstruction(bitcode);
		} else
		{
			return new RTypeInstruction(bitcode);
		}
	}
	@Override
	public String toString() {
		return instructionName;
	}
	private String findName(String bitcode) {

		String bType = bitcode.substring(0,6);
		String dType = bitcode.substring(0,11);
		String cbType = bitcode.substring(0,8);
		String iType = bitcode.substring(0,10);
		String imType = bitcode.substring(0,9);
		String rType = bitcode.substring(0,11);
		String shamtBinary = bitcode.substring(16,22);

		if(bType.equals("000101")/*B*/)
		{
			return "B";
		}
		else if(bType.equals("100101")/*BL*/)
		{
			return "BL";
		}
		else if(dType.equals("00111000000")/*STURB*/)
		{
			return "STURB";
		}
		else if(dType.equals("00111000010")/*LDURB*/)
		{
			return "LDURB";
		}
		else if(dType.equals("01111000000")/*STURH*/)
		{
			return "STURH";
		}
		else if(dType.equals("01111000010")/*LDURH*/)
		{
			return "LDURH";
		}
		else if(dType.equals("10111000000")/*STURW*/)
		{
			return "STURW";
		}
		else if(dType.equals("10111000100")/*LDURSW*/)
		{
			return "LDURSW";
		}
		else if(dType.equals("11001000000")/*STXR*/)
		{
			return "STXR";
		}
		else if(dType.equals("11001000010")/*LDXR*/)
		{
			return "LDXR";
		}
		else if(dType.equals("11111000000")/*STUR*/)
		{
			return "STUR";
		}
		else if(dType.equals("11111000010")/*LDUR*/)
		{
			return "LDUR";
		}
		else if(cbType.equals("01010100")/*B.cond*/)
		{
			return "B.cond";
		}
		else if(cbType.equals("10110100")/*CBZ*/)
		{
			return "CBZ";
		}
		else if(cbType.equals("10110101")/*CBNZ*/)
		{
			return "CBNZ";
		}
		else if(iType.equals("1001000100")/*ADDI*/)
		{
			return "ADDI";
		}
		else if(iType.equals("1001001000")/*ANDI*/)
		{
			return "ANDI";
		}
		else if(iType.equals("1011000100")/*ANDIS*/)
		{
			return "ANDIS";
		}
		else if(iType.equals("1011001000")/*ORRI*/)
		{
			return "ORRI";
		}
		else if(iType.equals("1101000100")/*SUBI*/)
		{
			return "SUBI";
		}
		else if(iType.equals("1101001000")/*EORI*/)
		{
			return "EORI";
		}
		else if(iType.equals("1111000100")/*SUBIS*/)
		{
			return "SUBIS";
		}
		else if(iType.equals("1111001000")/*ANDIS*/)
		{
			return "ANDIS";
		}
		else if(imType.equals("110100101")/*MOVZ*/)
		{
			return "MOVZ";
		}
		else if(imType.equals("111100101")/*MOVK*/)
		{
			return "MOVK";
		}
		else if(rType.equals("00011110001")/*FMULS*/ & shamtBinary.equals("000010"))
		{
			return "FMULS";
		}
		else if(rType.equals("00011110001")/*FDIVS*/ & shamtBinary.equals("000110"))
		{
			return "FDIVS";
		}
		else if(rType.equals("00011110001")/*FCMPS*/ & shamtBinary.equals("001000"))
		{
			return "FCMPS";
		}
		else if(rType.equals("00011110001")/*FADDS*/ & shamtBinary.equals("001010"))
		{
			return "FADDS";
		}
		else if(rType.equals("00011110001")/*FSUBS*/ & shamtBinary.equals("001110"))
		{
			return "FSUBS";
		}
		else if(rType.equals("00011110011")/*FMULD*/ & shamtBinary.equals("000010"))
		{
			return "FMULD";
		}
		else if(rType.equals("00011110011")/*FDIVD*/ & shamtBinary.equals("000110"))
		{
			return "FDIVD";
		}
		else if(rType.equals("00011110011")/*FCMPD*/ & shamtBinary.equals("001000"))
		{
			return "FCMPD";
		}
		else if(rType.equals("00011110011")/*FADDD*/ & shamtBinary.equals("001010"))
		{
			return "FADDD";
		}
		else if(rType.equals("00011110011")/*FSUBD*/ & shamtBinary.equals("001110"))
		{
			return "FSUBD";
		}
		else if(rType.equals("10001010000")/*AND*/)
		{
			return "AND";
		}
		else if(rType.equals("10001011000")/*ADD*/)
		{
			return "ADD";
		}
		else if(rType.equals("10011010110")/*SDIV*/ & shamtBinary.equals("000010"))
		{
			return "SDIV";
		}
		else if(rType.equals("10011010110")/*UDIV*/ & shamtBinary.equals("000011"))
		{
			return "UDIV";
		}
		else if(rType.equals("10011011000")/*MUL*/)
		{
			return "MUL";
		}
		else if(rType.equals("10011011010")/*SMULH*/)
		{
			return "SMULH";
		}
		else if(rType.equals("10011011110")/*UMULH*/)
		{
			return "UMULH";
		}
		else if(rType.equals("10101010000")/*ORR*/)
		{
			return "ORR";
		}
		else if(rType.equals("10101011000")/*ADDS*/)
		{
			return "ADDS";
		}
		else if(rType.equals("10111100000")/*STURS*/)
		{
			return "STURS";
		}
		else if(rType.equals("10111100010")/*LDURS*/)
		{
			return "LDURS";
		}
		else if(rType.equals("11001010000")/*EOR*/)
		{
			return "EOR";
		}
		else if(rType.equals("11001011000")/*SUB*/)
		{
			return "SUB";
		}
		else if(rType.equals("11010011010")/*LSR*/)
		{
			return "LSR";
		}
		else if(rType.equals("11010011011")/*LSL*/)
		{
			return "LSL";
		}
		else if(rType.equals("11010110000")/*BR*/)
		{
			return "BR";
		}
		else if(rType.equals("11101010000")/*ANDS*/)
		{
			return "ANDS";
		}
		else if(rType.equals("11101011000")/*SUBS*/)
		{
			return "SUBS";
		}
		else if(rType.equals("11111100000")/*STURD*/)
		{
			return "STURD";
		}
		else if(rType.equals("11111100010")/*LDURD*/)
		{
			return "LDURD";
		}
		else if(rType.equals("11111111101")/*PRNT*/)
		{
			return "PRNT";
		}
		else if(rType.equals("11111111100")/*PRNL*/)
		{
			return "PRNL";
		}
		else if(rType.equals("11111111110")/*DUMP*/)
		{
			return "DUMP";
		}
		else if(rType.equals("11111111111")/*HAULT*/)
		{
			return "HAULT";
		} else
		{
			return "You should not be seeing this";
		}
	}
	static String findTwoscomplement(StringBuffer str)
	{
		int n = str.length();

		// Traverse the string to get first '1' from
		// the last of string
		int i;
		for (i = n-1 ; i >= 0 ; i--)
			if (str.charAt(i) == '1')
				break;

		// If there exists no '1' concat 1 at the
		// starting of string
		if (i == -1)
			return "1" + str;

		// Continue traversal after the position of
		// first '1'
		for (int k = i-1 ; k >= 0; k--)
		{
			//Just flip the values
			if (str.charAt(k) == '1')
				str.replace(k, k+1, "0");
			else
				str.replace(k, k+1, "1");
		}

		// return the modified string
		return str.toString();
	}
}
