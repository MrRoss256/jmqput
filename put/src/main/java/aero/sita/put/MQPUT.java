package aero.sita.put;


import java.io.IOException;
import java.util.Hashtable;

import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class MQPUT {

	// main
    public static void main(String[] args) {
        // Create a connection to the queue manager
        Hashtable<String, Object> props = new Hashtable<String, Object>();
        props.put(MQConstants.CHANNEL_PROPERTY, "MGDM1_DS_CLIENT");
        props.put(MQConstants.PORT_PROPERTY, 1520);
        props.put(MQConstants.HOST_NAME_PROPERTY, "10.229.1.70");

        String qManager = "IBRDS1";
        //String queueName = "MYDM0_TYPEBIN";
        String queueName = "PRDM0_TYPEBIN";
        
        MQQueueManager qMgr = null;
        try {
            qMgr = new MQQueueManager(qManager, props);

            // MQOO_OUTPUT = Open the queue to put messages. The queue is opened for use with subsequent MQPUT calls.
            // MQOO_INPUT_AS_Q_DEF = Open the queue to get messages using the queue-defined default.
            int openOptions = MQConstants.MQOO_OUTPUT | MQConstants.MQOO_INPUT_AS_Q_DEF;

            // creating destination
            MQQueue queue = qMgr.accessQueue(queueName, openOptions);

            // specify the message options...
            MQPutMessageOptions pmo = new MQPutMessageOptions(); // default
            
            // create message
            MQMessage message = new MQMessage();
            
            // MQFMT_STRING = The application message data can be either an SBCS string (single-byte character set),
            // or a DBCS string (double-byte character set). Messages of this format can be converted
            // if the MQGMO_CONVERT option is specified on the MQGET call.
            message.format = MQConstants.MQFMT_STRING;
            message.writeString("QN GBRRMOK\r\n" + 
            ".YOWAAXH KL/041643\r\n" + 
            "UNA:+.? 'UNB+UNOA:4+KLMUS:ZZ+BCSAPIS:ZZ+190514:1643+00000000042994++APIS'UNG+PAXLST+CODECO:ZZ+BCSAPIS:ZZ+190514:1643+1+UN+D:02B'UNH+PAX001+PAXLST:D:02B:UN:IATA+SQ6680/190514/1910+02:F'BGM+745'NAD+MS+++BHANOT:KARAN'COM+12345678901234567890123456789012349:TE+12345678901234567890123456789012348:FX'COM+312049293:TE+9028732610:FX'TDT+20+SQ6680'LOC+125+SIN'DTM+189:1905130900:201'LOC+87+LHR'DTM+232:1905131930:201'NAD+FL+++JONES:DAVID'ATT+2++M'DTM+329:841212'LOC+22+SIN'LOC+178+SIN'LOC+179+LHR'LOC+180+GBR:::LONDON'NAT+2+GBR'RFF+AVF:UERTZQ'DOC+P+123456789'DTM+36:101024'DTM+182:151231'LOC+91+GBR:::LONDON'CNT+41:2'UNT+187+PAX001'UNE+1+1'UNZ+1+00000000042994'\r\n" + 
            ""); 
            queue.put(message, pmo);
            queue.close();
        } catch (MQException e) {
            System.err.println("Exception putting mesage [" + props.toString() + "]" + "to queue manager [" + qManager + "]");
            e.printStackTrace();
        } catch (IOException e) {
        	System.err.println("Exception putting mesage.");
        	e.printStackTrace();
        } finally {
            try {
                qMgr.disconnect();
            } catch (MQException e) {
            	System.err.println("Exception closing queue manager.");
            	e.printStackTrace();
            }
        }
    }
}
