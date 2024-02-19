First Step --> Go to pom.xml and change <mainClass></mainClass> to <mainClass>"the full path to your MainFX.java"</mainClass>
Second Step --> got on the right to maven and reload all maven projects
Third Step --> go to maven -> plugins -> javafx -> javafx:run to run your project


JDBC is the CONNECTOR TO THE MYSQL DB

Transactions is the class where you put all your SQL Requests

NOTES !!!!! : 
1) when you add a new fxml window in your project under resouces please make sure to link fx:controller="tn.CodeCommanders.path-to-the-controller class-of-this-window"
2) verify that in your MainFX in the method start that your are linking <<<<< FXMLLoader loader = new FXMLLoader(getClass().getResource("/name_of_your_main_window.fxml")); >>>>
3) ENJOY A COMPILING AND FULLY organized project <3 




 #FIGHT_FOR_OPEN_SOURCE
