import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class TimeTaker {
    public void TuringTimer(int startSize, int endSize, String TM_description, String file_name) {
        TuringMachineFactory TMF = new TuringMachineFactory();
        TuringMachine TM = TMF.BuildTuringMachine(TM_description);

        Random rand = new Random(1234);
        try {
            FileWriter writer;
            writer = new FileWriter(file_name);

            writer.append("problem_size");
            writer.append(",");
            writer.append("transitions");
            writer.append(",");
            writer.append("input");
            writer.append('\n');

            for (int i = startSize; i < endSize; i++) {
                if (i % 10 == 0) {
                    System.out.println(i);
                }
                String input = inputCreator(TM_description, i, rand);
                int transitions = TM.Run(input, false);

                writer.append(Integer.toString(i));
                writer.append(",");
                writer.append(Integer.toString(transitions));
                writer.append(",");
                writer.append(":" + input);
                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }

    public void BeaverTuringTimer(String file_name) {
        try {

            FileWriter writer;
            writer = new FileWriter(file_name);

            writer.append("problem_size");
            writer.append(",");
            writer.append("transitions");
            writer.append(",");
            writer.append("input");
            writer.append('\n');

            TuringMachineFactory TMF = new TuringMachineFactory();
            for (int i = 2; i < 5; i++) {
                System.out.println(i);
                TuringMachine TM = TMF.BuildTuringMachine("busy_beaver_" + i + ".txt");
                long start = System.nanoTime();
                int transitions = TM.Run("_", false);
                System.out.println(System.nanoTime() - start);
                writer.append(Integer.toString(i));
                writer.append(",");
                writer.append(Integer.toString(transitions));
                writer.append(",");
                writer.append(":" + "_");
                writer.append('\n');
            }
            writer.flush();
            writer.close();


        } catch (IOException e) {
            System.out.println("exception");
        }
    }


    private String inputCreator(String TM_description, int size, Random rand) {
        String input = "";
        switch (TM_description) {
            case "addition.txt":
                input = AdditionInputCreator(size, rand);
                break;
            case "palindrome.txt":
                input = PalindromeInputCreator(size, rand);
                break;
            case "divide.txt":
                input = divideInputCreator(size);
                break;
        }

        return input;
    }

    public String AdditionInputCreator(int size, Random rand) {
        String s1 = "";
        String s2 = "";
        if (size < 1) {
            return "##";
        }
        for (int i = 0; i < size; i++) {
            int temp1 = rand.nextInt(2);
            int temp2 = rand.nextInt(2);
            s1 = s1 + temp1;
            s2 = s2 + temp2;
        }
        BigInteger first = new BigInteger(s1, 2);
        BigInteger second = new BigInteger(s2, 2);

        BigInteger result = first.add(second);
        //if first and second are padded with zeros at the end
        if (result.toString(2).length() < s1.length()) {
            s1 = "1" + s1.substring(1, s1.length());
            first = new BigInteger(s1, 2);
            result = first.add(second);
        }
        String binaryStringResult = result.toString(2);

        String reverseBinaryString1 = reverseBinaryString(s1);
        String reverseBinaryString2 = reverseBinaryString(s2);
        String reverseBinaryStringResult = reverseBinaryString(binaryStringResult);

        return reverseBinaryString1 + "#" + reverseBinaryString2 + "#" + reverseBinaryStringResult;
    }

    public String PalindromeInputCreator(int size, Random rand) {
        String input = "";
        for (int i = 0; i < size; i++) {
            int num = rand.nextInt(3);
            input = num + input + num;
        }
        return input;
    }

    private String divideInputCreator(int size) {
        BigInteger big = new BigInteger("1");
        BigInteger three = new BigInteger("3");
        do {
            big = big.multiply(three);
            System.out.println(big.bitLength());
            System.out.println(big);
            System.out.println("---------------");
        } while (reverseBinaryString(big.toString(2)).length() < size);
        return reverseBinaryString(big.toString(2));
    }

    private String reverseBinaryString(String binary) {
        char[] content = binary.toCharArray();
        int j = content.length - 1;
        for (int i = 0; i < j; i++, j--) {
            char temp = content[i];
            content[i] = content[j];
            content[j] = temp;
        }
        return new String(content);
    }

}
