import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String TM_description = args[0];
        String input_file = args[1];
        TuringMachineFactory TMF = new TuringMachineFactory();
        TuringMachine TM = TMF.BuildTuringMachine(TM_description);
        System.out.println(TM.Run("1#0#1",false));
        System.out.println(TM.Run("0#0#0",false));
        System.out.println(TM.Run("00#00#00",false));
        System.out.println(TM.Run("1#1#01",false));
        System.out.println(TM.Run("1#01#11",false));
        System.out.println(TM.Run("00#111#111",false));
        System.out.println(TM.Run("#01#01",true));
    }
}
