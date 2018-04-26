import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NTM {
    private HashSet<String> States;
    private HashSet<Transition> TransitionTable;
    private String StartState;
    private HashSet<String> acceptingStates;

    public NTM() {
        States = new HashSet<String>();
        TransitionTable = new HashSet<Transition>();
        this.StartState = "";
        this.acceptingStates = new HashSet<String>();
    }

    public NTM(HashSet<String> StateSet, HashSet<Transition> TransitionSet, String Start, HashSet<String> accepting) {
        States = StateSet;
        TransitionTable = TransitionSet;
        this.StartState = Start;
        this.acceptingStates = accepting;
    }

    public void addState(String name, String accepting) {
        if (accepting.equals("+")) {
            States.add(name);
            acceptingStates.add(name);
        } else {
            States.add(name);
        }
    }

    public void addTransition(Transition transition) {

        TransitionTable.add(transition);
    }

    public void setStartState(String name) {
        StartState = name;
    }

    public int Run(boolean steps, String Tape, String CurrentState, int CurrentSymbol) {
        if (steps) {
            System.out.println(Tape + " in " + CurrentState + " at symbol " + CurrentSymbol + " which is " + Tape.charAt(CurrentSymbol));
        }

        if (acceptingStates.contains(CurrentState)) {
            return 1;
        }

        Iterator<Transition> TransitionIterator = TransitionTable.iterator();
        Transition currentTransition;
        Set<Transition> possibleTransitions = new HashSet<Transition>();


        while (TransitionIterator.hasNext()) {
            currentTransition = TransitionIterator.next();

            if (currentTransition.getReadState().equals(CurrentState) && currentTransition.getReadSymbol() == Tape.charAt(CurrentSymbol)) {
                possibleTransitions.add(currentTransition);

            }
        }
        if (possibleTransitions.isEmpty()) {
            if (steps) {
                System.out.println("no valid transition found");
            }
            return -1;
        } else {
            Iterator<Transition> possibleIterator = possibleTransitions.iterator();
            while (possibleIterator.hasNext()) {
                Transition possibility = possibleIterator.next();
                String FutureState = possibility.getWriteState();
                int FutureSymbol = CurrentSymbol;
                StringBuilder tape = new StringBuilder(Tape);
                switch (possibility.getMoveDirection()) {
                    case 'R':
                        tape.setCharAt(CurrentSymbol, possibility.getWriteSymbol());
                        if (FutureSymbol == tape.length() - 1) {
                            tape = new StringBuilder(tape + "_");
                        }
                        FutureSymbol++;
                        break;
                    case 'L':
                        tape.setCharAt(CurrentSymbol, possibility.getWriteSymbol());
                        if (FutureSymbol == 0) {
                            tape = new StringBuilder("_" + tape);
                        } else {
                            FutureSymbol--;
                        }
                        break;
                    case 'S':
                        tape.setCharAt(CurrentSymbol, possibility.getWriteSymbol());
                        break;
                }
                String temp_Tape = tape.toString();
                // if machine is now in an accepting state, return 1, success
                if (this.Run(steps, temp_Tape, FutureState, FutureSymbol) == 1) {
                    return 1;
                }

            }
            System.out.println("do I reach this?");

        }

        return 1;
    }

    public HashSet<String> getStates() {
        return States;
    }

    public HashSet<Transition> getTransitionTable() {
        return TransitionTable;
    }

    public String getStartState() {
        return StartState;
    }

    public void printTM() {
        Iterator<String> states = this.States.iterator();
        while (states.hasNext()) {
            System.out.println(states.next());
        }
        Iterator<Transition> transitionIterator = this.TransitionTable.iterator();
        while (transitionIterator.hasNext()) {
            Transition trans = transitionIterator.next();
            System.out.println("read state:" + trans.getReadState());
            System.out.println("read sym:" + trans.getReadSymbol());
            System.out.println("write state:" + trans.getWriteState());
            System.out.println("write sym:" + trans.getWriteSymbol());
            System.out.println("move dir:" + trans.getMoveDirection());
            System.out.println("---------");

        }
    }
}
