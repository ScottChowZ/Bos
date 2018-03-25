package com.scott.activemq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

/**  
 * ClassName:SmsConsumer <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午4:52:40 <br/>       
 */
@Component("smsConsumer")
public class SmsConsumer implements MessageListener{

    @Override
    public void onMessage(Message mes) {
          MapMessage mapMessage=(MapMessage) mes;
          
          try {
            String code = mapMessage.getString("seCode");
            String tel = mapMessage.getString("tel");
            System.out.println(code+":::"+tel);
        } catch (JMSException e) {
              
             
            e.printStackTrace();  
            
        }
         
        
    }

}
  
