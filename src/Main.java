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
        TuringMachine TM = new TuringMachine();
        try{
            FileReader fileReader = new FileReader(TM_description);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                if(line.contains("states")){
                    int nStates = Integer.parseInt(line.split(" ")[1]);
                    addStates(TM,nStates, bufferedReader);
                }
                if(line.contains("alphabet")){
                    System.out.println(line);

                }
            }
            Iterator<String> states = TM.getStates().iterator();
            while(states.hasNext()){
                System.out.println(states.next());
            }

            // Always close files.
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage()+" ");
        }

    }
    private static void addStates(TuringMachine TM, int nStates, BufferedReader bufferedReader) throws IOException{
        String line = "";
        System.out.println("states:" + nStates);

        for (int i = 0; i < nStates; i++) {
            line = bufferedReader.readLine();
            String[] content = line.split(" ");

            if(i==0){

                TM.setStartState(content[0]);

            }
            System.out.println(content.length);
            if(content.length >1){
                System.out.println(content[0]);
                System.out.println("accept");
                TM.addState(content[0],content[1]);

            } else {
                System.out.println(content[0]);
                TM.addState(content[0], "-");

            }
        }
    }
}
