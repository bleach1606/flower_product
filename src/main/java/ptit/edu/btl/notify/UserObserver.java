package ptit.edu.btl.notify;

public class UserObserver extends Observer{

    public UserObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "thông báo đơn hàng cho user : " + subject.getState());
    }
}
