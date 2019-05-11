# jmqput
Simple MQ client using the native MQ Java API, reads a file (or stdin) and puts the content
of the file as a message to a queue declared on the command line.

# installing
Clone from GIT, then compile/build via Maven

# running
   java -jar put-0.0.1-SNAPSHOT-jar-with-dependencies.jar [<options>] file...
or to read stdin:
   java -jar put-0.0.1-SNAPSHOT-jar-with-dependencies.jar [<options>] 
   
   
