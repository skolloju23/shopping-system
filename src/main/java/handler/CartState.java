package handler;

public interface CartState {
    void process(Handle handle);
    State next();
}
