import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TuringMachineFactory {
    public TuringMachine BuildTuringMachine(String TM_description){
        // parse the input file
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
                    addTransitions(TM, bufferedReader);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage()+" ");
        }
        return TM;
    }
    private static void addStates(TuringMachine TM, int nStates, BufferedReader bufferedReader) throws IOException{


        String line = "";

        for (int i = 0; i < nStates; i++) {
            line = bufferedReader.readLine();
            String[] content = line.split(" ");

            if(i==0){

                TM.setStartState(content[0]);

            }
            if(content.length >1){
                TM.addState(content[0],content[1]);

            } else {
                TM.addState(content[0], "-");

            }
        }
    }

    private static void addTransitions(TuringMachine TM, BufferedReader bufferedReader) throws IOException{
        String line = "";
        while((line = bufferedReader.readLine()) != null) {
            String[] content = line.split(" ");
            TM.addTransition(new Transition(content[0],content[1].charAt(0),content[2],content[3].charAt(0),content[4].charAt(0)));
        }
    }
}
