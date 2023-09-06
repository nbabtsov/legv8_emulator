import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
	public static void main(String[] args) throws IOException {
//		String filename = "things.legv8asm.machine";

		String filename = "";

		if (args.length > 0) {
			filename = args[0];
		}
		if (filename.equals("")) {
			System.err.println("You need to provide a filename when starting the file.");
			System.exit(1);
		}

		File file = new File(filename);
		byte[] contents = Files.readAllBytes(Path.of(file.getPath()));
		ArrayList<String> instructionList = new ArrayList<>();

		instructionList.add("main:");
		for (int i = 0; i < contents.length; i += 4) {
			int result = 0x00FF & contents[i];
			result <<= 8;
			result += 0x00FF & contents[i + 1];
			result <<= 8;
			result += 0x00FF & contents[i + 2];
			result <<= 8;
			result += 0x00FF & contents[i + 3];

			String binary = String.format("%032d", new BigInteger(Integer.toBinaryString(result)));
			Instruction instruction = Instruction.instructionType(binary);
			instructionList.add(instruction.toString());
			instructionList.add("");
//			System.out.println(instruction.toString());
		}

		//		***************************************************************************************

		HashMap<Integer, Integer> hashMap = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> multi = new HashMap<>();
		ArrayList<Integer> positionList = new ArrayList<>();

		int k = 0;
		String[] arrOfStr;
		for(String s : instructionList){
			//find the instructions which have Branching excluding BR X30
			if(!s.equals("") && s.toCharArray()[0] == 'B' && s.toCharArray()[1] != 'R'){
				arrOfStr = s.split(" ");
				//put the position/line number (key) and label number(value)
				hashMap.put(k, Integer.parseInt(arrOfStr[1]));
				positionList.add(k);
			}
			else if(!s.equals("") && s.toCharArray()[0] == 'C' && s.toCharArray()[1] == 'B'){
				arrOfStr = s.split(" ");
				hashMap.put(k, Integer.parseInt(arrOfStr[2]));
				positionList.add(k);
			}

			k++;
		}

		int iListLength = instructionList.size();
		String tempStr;
		int tempInt;
		ArrayList<Integer> tempArr;
		for(int pos : positionList){
			//forming the dependency tree
			//Creates a hashmap with key the position of the label in the instruction list and the value
			//is an arraylist of positions who map too that label position. This can be calculated by teh next line of code.
			tempInt = ((hashMap.get(pos))*2)-1+pos;
			if(tempInt < iListLength) {
				if (multi.containsKey(tempInt)) {
					tempArr = multi.get(tempInt);
				} else {
					tempArr = new ArrayList<>();
				}
				tempArr.add(pos);
				multi.put(tempInt, tempArr);
			}
		}

		for(int x : multi.keySet()){
			//Sets the position in the instruction array with the new label name
			instructionList.set(x, "label"+(x/2)+":");
			for(int y : multi.get(x)){
				//if its a CB instruction we need the first two parts of the instruction and replace the third which is the last part
				if(instructionList.get(y).split(" ")[0].contains("CB")){
					tempStr = instructionList.get(y).split(" ")[0]+ " " + instructionList.get(y).split(" ")[1] + " label"+(x/2);
				}
				//If its not we only need the first part and replace the second which is the last
				else {
					tempStr = instructionList.get(y).split(" ")[0] + " label"+(x/2);
				}
				//Sets the position in the instruction array to the new string having the the new label name
				instructionList.set(y, tempStr);
			}
		}

//		System.out.println("\n\n");
//		for(String s : instructionList){
//			if(!s.equals("")){
//				System.out.println(s);
//			}
//		}

		try {

			PrintWriter pw = new PrintWriter(new FileWriter("output.legv8asm"));

			for(String s : instructionList){
				if(!s.equals("")){
					pw.write(s+"\n");
				}
			}

			pw.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}


