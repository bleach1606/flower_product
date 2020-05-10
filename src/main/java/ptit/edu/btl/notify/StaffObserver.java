package ptit.edu.btl.notify;

public class StaffObserver extends Observer{

    public StaffObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "thông báo đơn hàng cho staff : " + subject.getState());
    }
}
