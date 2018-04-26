import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        String TM_description = args[0];
        String input_file = args[1];
        TuringMachineFactory TMF = new TuringMachineFactory();
        TuringMachine TM = TMF.BuildTuringMachine(TM_description);

        //TM.Run("_",true);

        TimeTaker timeTaker = new TimeTaker();
        timeTaker.BeaverTuringTimer("beaver_test.csv");
        //timeTaker.TuringTimer(1, 250, "divide.txt", "divisibility_test.csv");
        // palindrome file
        //timeTaker.TuringTimer(1, 250, "palindrome.txt", "palindrome_test.csv");

        // addition file
        //timeTaker.TuringTimer(1, 250, "addition.txt", "addition_test.csv");

    }


    private static String reverseBinaryString(String binary) {
        char[] content = binary.toCharArray();
        int j = content.length - 1;
        for (int i = 0; i < j; i++, j--) {
            char temp = content[i];
            content[i] = content[j];
            content[j] = temp;
        }
        return new String(content);
    }

    private static String divideInputCreator(int size) {
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


}
