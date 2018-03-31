import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TuringMachine {
    private Set<String> States;
    private Set<Transition> TransitionTable;
    private String startState;
    private Set<String> acceptingStates;

    private String Tape;
    private String CurrentState;
    private String CurrentString;

    public TuringMachine() {
        States = new HashSet<String>();
        TransitionTable = new HashSet<Transition>();
        this.startState = "";
        this.acceptingStates = new HashSet<String>();
        Tape = "";
        CurrentState = "";
        CurrentString = "";
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

    public boolean addTransition(String rState, String rSymbol, String wState, String wSymbol, char direction){
        if(!States.contains(rState) || !States.contains(wState)){
            return false;
        }
        TransitionTable.add(new Transition(rState,rSymbol,wState,wSymbol,direction));
        return true;
    }

    public boolean setStartState(String name){
        startState = name;
        return true;
    }

    public Set<String> getStates() {
        return States;
    }
}
