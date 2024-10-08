import java.util.EventObject;

public class MessageEvent extends Evenement {

    private String message;

    public MessageEvent(long date, String message) {
        super(date);
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println("Message Event : " + message);
    }
}
