import java.util.HashSet;
import java.util.Iterator;

public class TuringMachine {
    private HashSet<String> States;
    private HashSet<Transition> TransitionTable;
    private String StartState;
    private HashSet<String> acceptingStates;

    private String Tape;
    private String CurrentState;
    private int CurrentSymbol;

    public TuringMachine() {
        States = new HashSet<String>();
        TransitionTable = new HashSet<Transition>();
        this.StartState = "";
        this.acceptingStates = new HashSet<String>();
        Tape = "";
        CurrentState = "";
        CurrentSymbol = 0;
    }

    public void addState(String name, String accepting){
        if(accepting.equals("+")) {
            States.add(name);
            acceptingStates.add(name);
        } else {
            States.add(name);
        }
        //return true;
    }

    public void addTransition(Transition transition){
        //if(!States.contains(transition.getReadState()) || !States.contains(transition.getWriteState())){
        //    return false;
        //}
        TransitionTable.add(transition);
        //return true;
    }

    public void setStartState(String name){
        StartState = name;
    }

    public int Run(String input, boolean steps) {
        CurrentState = StartState;
        Tape = input;
        int transition_counter = 0;


        while(!acceptingStates.contains(CurrentState)) {
            transition_counter++;
            Iterator<Transition> TransitionIterator = TransitionTable.iterator();
            Transition currentTransition = null;
            boolean foundTransition = false;
            if(steps){
                System.out.println("_________");
                System.out.println("Tape: "+Tape + " at "+ CurrentSymbol+" which is "+ Tape.charAt(CurrentSymbol) +" in state " + CurrentState);
            }

            while (TransitionIterator.hasNext()) {
                currentTransition = TransitionIterator.next();

                if (currentTransition.getReadState().equals(CurrentState) && currentTransition.getReadSymbol() == Tape.charAt(CurrentSymbol)) {
                    foundTransition = true;
                    break;
                }
            }
            if (!foundTransition) {
                if(steps){
                    System.out.println("no valid transition found");
                }
                reset_machine();
                return -1;
            } else {
                CurrentState = currentTransition.getWriteState();
                StringBuilder tape = new StringBuilder(Tape);
                switch (currentTransition.getMoveDirection()) {
                    case 'R':
                        tape.setCharAt(CurrentSymbol, currentTransition.getWriteSymbol());
                        if (CurrentSymbol == tape.length()-1) {
                            tape = new StringBuilder(tape + "_");
                        }
                        CurrentSymbol++;
                        break;
                    case 'L':
                        tape.setCharAt(CurrentSymbol, currentTransition.getWriteSymbol());
                        if (CurrentSymbol == 0) {
                            tape = new StringBuilder("_" + tape);
                        } else {
                            CurrentSymbol--;
                        }
                        break;
                    case 'S':
                        tape.setCharAt(CurrentSymbol, currentTransition.getWriteSymbol());
                        break;
                }
                Tape = tape.toString();
            }
        }
        reset_machine();
        return transition_counter;
    }

    private void reset_machine(){
        this.Tape = "";
        this.CurrentSymbol = 0;

    }

    public HashSet<String> getStates() {
        return States;
    }

    public HashSet<Transition> getTransitionTable() {
        return TransitionTable;
    }

    public HashSet<String> getAcceptingStates() {
        return acceptingStates;
    }

    public String getStartState() {
        return StartState;
    }

    public void printTM(){
        Iterator<String> states = this.States.iterator();
        while(states.hasNext()){
            System.out.println(states.next());
        }
        Iterator<Transition> transitionIterator = this.TransitionTable.iterator();
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
