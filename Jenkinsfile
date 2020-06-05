pipeline {
    agent any
    stages {
       stage("Preparation") {
          steps {
             echo 'Download updates'
             git 'https://github.com/katerinski/Homework-JAVA.git'
          }
       }
           stage("Second") {
              steps {
                 echo 'mvn clean -DsuiteXmlFile=multythread-testng.xml test'
           }
       }

           stage("Third") {
              steps {
                 echo 'Bye'
           }
       }
    }
}