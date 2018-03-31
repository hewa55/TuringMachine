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

    public boolean addState(String name, String accepting){
        if(accepting.equals("+")) {
            States.add(name);
            acceptingStates.add(name);
        } else {
            States.add(name);
        }
        return true;
    }

    public boolean addTransition(Transition transition){
        if(!States.contains(transition.getReadState()) || !States.contains(transition.getWriteState())){
            return false;
        }
        TransitionTable.add(transition);
        return true;
    }

    public boolean setStartState(String name){
        StartState = name;
        return true;
    }

    public boolean Run(String input){
        CurrentState = StartState;
        Tape = input;
        Transition currentTrasition = null;
        boolean foundTransition = false;
        Iterator<Transition> TrasitionIterator = TransitionTable.iterator();
        while(!acceptingStates.contains(CurrentState)) {
            while (TrasitionIterator.hasNext()) {
                currentTrasition = TrasitionIterator.next();
                if (currentTrasition.getReadState().equals(CurrentState) && currentTrasition.getReadSymbol() == Tape.charAt(CurrentSymbol)) {
                    foundTransition = true;
                    break;
                }
            }
            if (!foundTransition) {
                System.out.println("no valid transition found");
                return false;
            } else {
                CurrentState = currentTrasition.getWriteState();
                StringBuilder tape = new StringBuilder(Tape);
                switch (currentTrasition.getMoveDirection()) {
                    case 'R':
                        tape.setCharAt(CurrentSymbol, currentTrasition.getWriteSymbol());
                        if (CurrentSymbol == tape.length()) {
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
            }
        }
        return true;
    }

    public Set<String> getStates() {
        return States;
    }

    public Set<Transition> getTransitionTable() {
        return TransitionTable;
    }
}
