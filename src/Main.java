import java.util.BitSet;

public class Main {
    static BitSet bs = new BitSet(3);
    public static void main(String[] args) {

        String TM_description = args[0];
        String input_file = args[1];
        TuringMachineFactory TMF = new TuringMachineFactory();
        TuringMachine TM = TMF.BuildTuringMachine(TM_description);

        //System.out.println(TM.Run(new TimeTaker().PalindromeInputCreator(3),true));

        //BitSet bitSet = new BitSet(5);
        //bitSet.set(0,4);
        //System.out.println(bitSet.cardinality());
        //bitSet.flip(2);
        //System.out.println(bitSet.toString());
        //fill(0,3);

        TimeTaker timeTaker = new TimeTaker();
        timeTaker.TuringTimer(1, 1000, "palindrome.txt", "name");

    }

    static void fill(int k, int n) {
        if (k == n) {
            System.out.println(bs);
            return;
        }
        bs.set(k, false);
        fill(k + 1, n);
        bs.set(k, true);
        fill(k + 1, n);
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


}
