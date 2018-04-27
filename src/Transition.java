public class Transition {
    /// class representing a transition in the TM
    private String readState;
    private char readSymbol;

    private String writeState;
    private char writeSymbol;

    private char moveDirection; //R right, L left, S stay

    public Transition(String rState,char rSymbol, String wState, char wSymbol, char mDirection){
        this.readState = rState;
        this.readSymbol = rSymbol;
        this.writeState = wState;
        this.writeSymbol = wSymbol;
        this.moveDirection = mDirection;
    }

    public String getReadState() {
        return readState;
    }

    public char getReadSymbol() {
        return readSymbol;
    }

    public String getWriteState() {
        return writeState;
    }

    public char getWriteSymbol() {
        return writeSymbol;
    }

    public char getMoveDirection() {
        return moveDirection;
    }
}
