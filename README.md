# Logger Anypoint Connector
This custom connector takes a HashMap as input, formats it either as JSON or key/value pairs, and logs the result using the org.apache.log4j.Logger class.

# Mule supported versions
3.8.x and 3.9.x

# Installation 
For use with a Mule Project, you can either:  
 - Download the source code and run `mvn clean install`, then insert this dependency in your pom.xml 
```
<dependency>
    <groupId>37513a5c-61c8-4566-8c30-c9d2a08150d8</groupId>
    <artifactId>logger-connector</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
``` 
 - Download the source code and build it with AnyPoint Studio devkit to find it available on your local repository. Then you can add it to Studio. Details here: https://docs.mulesoft.com/connector-devkit/3.9/creating-an-anypoint-connector-project. Use this option if you would like to make changes to the connector.

For released connectors you can download them from the update site in Anypoint Studio. 
Open Anypoint Studio, go to Help → Install New Software and select Anypoint Connectors Update Site where you’ll find all avaliable connectors.

# Reporting Issues

Feel free to report any issues here: https://github.com/BigCompass/custom-logger-connector/issues
