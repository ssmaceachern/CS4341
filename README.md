# CS4341
Sean MacEachern, Zachary Arnold

How to build the Project:
Inside the source folder is an Ant script that automatically compiles the code into a JAR 
simply by going to Project > Build All (Ctrl+B) within eclipse. The Jar file, namely testPlayer3.jar 
should be located within the project folder, making it easer to run the referee from within the 
directory.

In some instances, eclipse may throw an error for the build.xml file. To ammend this this, go into the project 
properties and go to Builders > Create Player > Edit. Re-upload the build.xml file by clicking 'Browse Workspace' button and locating the build.xml within the src folder. This should fix that compile error.

The program was compiled with Java SDK/SRE 8. The Referee.JAR may not run on earlier version so we would advise running the program on an updated computer for maximum satisfaction.

Neglect the 'search' folder. It is evil and full of nasty viruses.