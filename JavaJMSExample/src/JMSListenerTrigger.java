import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;


public class JMSListenerTrigger implements Runnable{

	private Connection jmsConnection;
	private String queue;
	
	public JMSListenerTrigger(Connection jmsConnection, String queue) {
		// TODO Auto-generated constructor stub
		this.jmsConnection = jmsConnection;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Session session = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queue);
			
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new ConsumerMessageListener());
			
			System.out.println(Thread.currentThread().getName());
			boolean flag = false;
			synchronized (this) {
				try{
					//start JMS Connection and put the thread in waiting
					//state so that Message Listener would always be alive
					//Until and Unless process gets killed
					jmsConnection.start();
					while(!flag){
						this.wait();
					}
				}catch(InterruptedException e){
					e.printStackTrace();
					flag = true;
				}
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try{
				if(jmsConnection != null){
					jmsConnection.close();
				}
			}catch(JMSException e){
				e.printStackTrace();
			}
		}
		System.out.println("Thread Completed...");
	}

}
