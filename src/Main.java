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
                    addTrasitions(TM, bufferedReader);
                }
            }
            printTM(TM);
            System.out.println(TM.Run("f"));
            // Always close files.
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage()+" ");
        }

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

    private static void addTrasitions(TuringMachine TM, BufferedReader bufferedReader) throws IOException{
        String line = "";
        while((line = bufferedReader.readLine()) != null) {
            String[] content = line.split(" ");
            TM.addTransition(new Transition(content[0],content[1].charAt(0),content[2],content[3].charAt(0),content[4].charAt(0)));
        }
    }

    private static void printTM(TuringMachine TM){
        Iterator<String> states = TM.getStates().iterator();
        while(states.hasNext()){
            System.out.println(states.next());
        }
        Iterator<Transition> transitionIterator = TM.getTransitionTable().iterator();
        while (transitionIterator.hasNext()){
            Transition trans = transitionIterator.next();
            System.out.println("read state:" +trans.getReadState());
            System.out.println("read sym:" +trans.getReadSymbol());
            System.out.println("write state:" +trans.getWriteState());
            System.out.println("write sym:" +trans.getWriteSymbol());
            System.out.println("move dir:" +trans.getMoveDirection());
            System.out.println("---------");

        }
    }
}
