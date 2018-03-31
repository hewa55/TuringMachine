public class State {
    private String name;
    private boolean accepting;

    public State(String name, boolean accepting){
        this.name = name;
        this.accepting = accepting;
    }

    public String getName() {
        return name;
    }

    public boolean isAccepting() {
        return accepting;
    }

}
