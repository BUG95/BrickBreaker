package game.brickbraker.states;

public class StateManager {

    private static State currentState = null;
    private StateManager(){}

    public static void setCurrentState(State state){
         currentState = state;
    }

    public static State getCurrentState(){
        return currentState;
    }
}
