package ptit.edu.btl.notify;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}