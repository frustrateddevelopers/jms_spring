import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class JmsConsumer {

	public static void main(String[] args) throws JMSException, FileNotFoundException, IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("C:\\Users\\Qasim\\Downloads\\resource.properties"));		
		String host = properties.getProperty("host");
		String que = properties.getProperty("queue");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+host+":61616");
		Connection connection = connectionFactory.createConnection();
		
		Thread triggerListerThd = new Thread(new JMSListenerTrigger(connection, que), "JMS_Listener_Trigger_Thread");
		triggerListerThd.start();
		try {
			triggerListerThd.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
