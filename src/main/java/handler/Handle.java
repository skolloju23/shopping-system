package handler;

import auth.User;
import main.InputAndOutput;

public class Handle {


    InputAndOutput inputAndOutput;
    static User user;

    public Handle(InputAndOutput inputAndOutput) {
        this.inputAndOutput = inputAndOutput;
    }

    public void start() {
        State state = State.StartState;
        while (state != State.ExitState) {
            try {
                state.process(this);
                state = state.next();
            } catch (Exception e) {
                System.out.println("Invalid option");
            }
        }
    }

}
