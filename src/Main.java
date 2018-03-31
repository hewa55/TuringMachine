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
        System.out.println(TM.Run("111",true));
        System.out.println(TM.Run("1111",false));

    }
}
