import java.util.Random;

public class TimeTaker {
    public void TuringTimer(int startSize, int endSize, String TM_description, String file_name) {
        TuringMachineFactory TMF = new TuringMachineFactory();
        TuringMachine TM = TMF.BuildTuringMachine(TM_description);

        Random rand = new Random(1234);
        for (int i = startSize; i < endSize; i++) {
            String input = inputCreator(TM_description, i, rand);
            System.out.println(TM.Run(input, false));
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
        }

        return input;
    }

    public String AdditionInputCreator(int size, Random rand) {
        String input = "";
        if (size > 31) {
            System.out.println("too large");
            return "please stay below 32";
        }
        int lower = (int) Math.pow(2.0, size - 1);
        int upper = (int) Math.pow(2.0, size) - 1;

        int first;
        int second;

        first = rand.nextInt((upper - lower) + 1) + lower;
        second = rand.nextInt((upper - lower) + 1) + lower;

        String binaryString1 = Integer.toBinaryString(first);
        String reverseBinaryString1 = reverseBinaryString(binaryString1);

        String binaryString2 = Integer.toBinaryString(second);
        String reverseBinaryString2 = reverseBinaryString(binaryString2);

        int result = first + second;
        String binaryStringResult = Integer.toBinaryString(result);
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
