public class Transition {
    private String readState;
    private String readSymbol;

    private String writeState;
    private String writeSymbol;

    private char moveDirection; //R right, L left, S stay

    public Transition(String rState,String rSymbol, String wState, String wSymbol, char mDirection){
        this.readState = rState;
        this.readSymbol = rSymbol;
        this.writeState = wState;
        this.writeSymbol = wSymbol;
        this.moveDirection = mDirection;
    }

    public String getReadState() {
        return readState;
    }

    public String getReadSymbol() {
        return readSymbol;
    }

    public String getWriteState() {
        return writeState;
    }

    public String getWriteSymbol() {
        return writeSymbol;
    }

    public char getMoveDirection() {
        return moveDirection;
    }
}
