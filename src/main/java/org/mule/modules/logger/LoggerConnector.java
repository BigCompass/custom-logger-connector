/**
 *
 */
package org.mule.modules.logger;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Source;
import org.mule.api.annotations.SourceStrategy;
import org.mule.api.callback.SourceCallback;
import org.mule.api.annotations.lifecycle.OnException;
import org.mule.api.annotations.param.Default;
import org.mule.modules.logger.config.ConnectorConfig;
//import org.mule.modules.logger.error.ErrorHandler;
import org.apache.log4j.Logger;

@Connector(name="logger", friendlyName="Custom Logger")
//@OnException(handler=ErrorHandler.class)
public class LoggerConnector {

	private final static Logger logger = Logger.getLogger(LoggerConnector.class);
	
	public enum LogFormat {
		JSON, STANDARD;
	}
	
	public enum LogLevel {
		ERROR, WARN, TRACE, DEBUG, INFO;
	}
	
//    @Config
//    ConnectorConfig config;

    
    
    /**
     *  Custom Source Processor Example. Commented out intentionally
     *  Use @Source annotation for specifying an inbound action to your flow
     *
     *  @param callback Any callback
     *  @throws Exception error produced while processing the payload
     */
//    @Source(sourceStrategy = SourceStrategy.POLLING,pollingPeriod=5000)
//    public void getNewMessages(SourceCallback callback) throws Exception {
//        /*
//         * Every 5 the flow using this processor will be called and the payload will be the one defined here.
//         * 
//         * The PAYLOAD can be anything. In this example a String is used.  
//         */
//        callback.process("Start working");
//    }
	
//public ConnectorConfig getConfig() {
//  return config;
//}
//
//public void setConfig(ConnectorConfig config) {
//  this.config = config;
//}

    /**
     *  Custom Logging Processor to log arbitrary number of key/value pairs in a standard format to MuleSoft server logs
     *  Use @Source annotation for specifying an inbound action to your flow
     *
     *  @param logParams HashMap of key/value pairs to log
     *  @param logForm String can either equal JSON or Standard to format a log into each format
     */
    @Processor
    public void sendLog(HashMap<String, String> logParams, LogFormat logFormat, LogLevel logLevel) {
    	try {
    		String log = null;
    		if (logFormat.name().equals("JSON")) {
    			log = formatJSONLog(logParams);
    		}
    		else {
    			log = formatStandardLog(logParams);
    		}  
    		log(log, logLevel);
    	}
    	catch (Exception e) {
    		log("Exception occurred: " + e, LogLevel.ERROR);
    	}
    }
    
    /**
     *  Format hashmap into JSON
     *
     *  @param logParams HashMap of key/value pairs to log
     */
    public String formatJSONLog(HashMap<String, String> logParams) {
    	StringBuffer runningLog = new StringBuffer("{");
    	//Loop over logParams and format into JSON
    	for (HashMap.Entry<String, String> entry : logParams.entrySet()) {
    		StringBuffer formatEntry = new StringBuffer();
    		formatEntry.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
    		runningLog.append(formatEntry);
    	}
    	//Delete the last comma and append the final curly brace
    	runningLog.deleteCharAt(runningLog.length() - 1);
    	runningLog.append("}");
    	String log = runningLog.toString();
    	return log;
    }
    
    /**
     *  Format hashmap into key = "value" format
     *
     *  @param logParams HashMap of key/value pairs to log
     */
    public String formatStandardLog(HashMap<String, String> logParams) {
    	StringBuffer runningLog = new StringBuffer();
    	//Loop over logParams and format into key1=value1, key2=value2 structure
    	for (HashMap.Entry<String, String> entry : logParams.entrySet()) {
    		StringBuffer formatEntry = new StringBuffer();
    		formatEntry.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\", ");
    		runningLog.append(formatEntry);
    	}
    	//Delete the last comma and space
    	runningLog.deleteCharAt(runningLog.length() - 2);
    	String log = runningLog.toString();
    	return log;
    }
    

    /**
     *  Log string to MuleSoft server log with certain log level
     *
     *  @param logMessage String to log
     *  @param logLevel String to denote the log level
     */
    private void log(String logMessage, LogLevel logLevel) {
    	if (logLevel.name().equals("ERROR")) {
    		logger.error(logMessage);
    	}
    	else if (logLevel.name().equals("WARN")) {
    		logger.warn(logMessage);
    	}
    	else if (logLevel.name().equals("DEBUG")) {
    		logger.debug(logMessage);
    	}
    	else if (logLevel.name().equals("TRACE")) {
    		logger.trace(logMessage);
    	}
    	else {
    		logger.info(logMessage);
    	}
    }

}