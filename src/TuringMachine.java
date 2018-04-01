import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TuringMachine {
    private Set<String> States;
    private Set<Transition> TransitionTable;
    private String StartState;
    private Set<String> acceptingStates;

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

    public boolean Run(String input, boolean steps){
        CurrentState = StartState;
        Tape = input;


        while(!acceptingStates.contains(CurrentState)) {
            Iterator<Transition> TransitionIterator = TransitionTable.iterator();
            Transition currentTrasition = null;
            boolean foundTransition = false;
            if(steps){
                System.out.println("_________");
                System.out.println("Tape: "+Tape + " at "+ CurrentSymbol+" which is "+ Tape.charAt(CurrentSymbol) +" in state " + CurrentState);
            }

            while (TransitionIterator.hasNext()) {
                currentTrasition = TransitionIterator.next();

                if (currentTrasition.getReadState().equals(CurrentState) && currentTrasition.getReadSymbol() == Tape.charAt(CurrentSymbol)) {
                    foundTransition = true;
                    break;
                }
            }
            if (!foundTransition) {
                if(steps){
                    System.out.println("no valid transition found");
                }
                reset_machine();
                return false;
            } else {
                CurrentState = currentTrasition.getWriteState();
                StringBuilder tape = new StringBuilder(Tape);
                switch (currentTrasition.getMoveDirection()) {
                    case 'R':
                        tape.setCharAt(CurrentSymbol, currentTrasition.getWriteSymbol());
                        if (CurrentSymbol == tape.length()-1) {
                            tape = new StringBuilder(tape + "_");
                        }
                        CurrentSymbol++;
                        break;
                    case 'L':
                        tape.setCharAt(CurrentSymbol, currentTrasition.getWriteSymbol());
                        if (CurrentSymbol == 0) {
                            tape = new StringBuilder("_" + tape);
                        } else {
                            CurrentSymbol--;
                        }
                        break;
                    case 'S':
                        tape.setCharAt(CurrentSymbol, currentTrasition.getWriteSymbol());
                        break;
                }
                Tape = tape.toString();
            }
        }
        reset_machine();
        return true;
    }

    private void reset_machine(){
        this.Tape = "";
        this.CurrentSymbol = 0;

    }
    public Set<String> getStates() {
        return States;
    }

    public Set<Transition> getTransitionTable() {
        return TransitionTable;
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
