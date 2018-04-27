public class Main {

    public static void main(String[] args) {
        // given commands
        String TM_description = args[0];
        String input_string = args[1];
        String machine = args[2];

        TuringMachineFactory TMF = new TuringMachineFactory();
        // build TM
        TuringMachine TM = TMF.BuildTuringMachine(TM_description);
        // build NTM from TM
        NTM ntm = new NTM(TM.getStates(), TM.getTransitionTable(), TM.getStartState(), TM.getAcceptingStates());

        if (machine.equals("NTM")) {
            System.out.println(ntm.Run(true, input_string, ntm.getStartState(), 0));
        } else {
            if (machine.equals("Test")) {
                // for later
                TimeTaker timeTaker = new TimeTaker();
                // busy beaver
                System.out.println("Beavers");
                timeTaker.BeaverTuringTimer("beaver_test.csv");
                // divisibility
                System.out.println("Divisibility");
                timeTaker.TuringTimer(1, 250, "divide.txt", "divisibility_test.csv");
                // palindrome file
                System.out.println("Palindrome");
                timeTaker.TuringTimer(1, 250, "palindrome.txt", "palindrome_test.csv");
                // addition file
                System.out.println("Addition");
                timeTaker.TuringTimer(1, 250, "addition.txt", "addition_test.csv");
            } else {
                System.out.println(TM.Run(input_string, true));
            }
        }
    }
}
