package game.brickbraker.states;

public class StateManager {

    private State currentState = null;
    private static StateManager stateManager = null;

    public static StateManager getInstance() {
        if(stateManager == null){
            stateManager = new StateManager();
        }
        return stateManager;
    }

    private StateManager(){}

    public void setCurrentState(State state){
         currentState = state;
    }

    public State getCurrentState(){
        return currentState;
    }
}
