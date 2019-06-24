import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class ConsumerMessageListener implements MessageListener{

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage textMsg = (TextMessage)msg;
		try{
			System.out.println("Recieved... "+textMsg.getText());
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

}
