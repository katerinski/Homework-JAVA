pipeline {
    agent any
    stages {
       stage("Preparation") {
          steps {
             echo 'Download updates'
             git 'https://github.com/katerinski/Homework-JAVA.git'
          }
       }
           stage("Unit tests") {
              steps {
                 bat 'mvn clean -DsuiteXmlFile=multythread-testng.xml test'
           }
       }

           stage("UI tests") {
              steps {
                 bat 'mvn clean -DsuiteXmlFile=testng.xml test'
           }
       }
    }
    post {
        always {
           script {
                 allure([
                                      includeProperties: false,
                                      jdk: '',
                                      properties: [],
                                      reportBuildPolicy: 'ALWAYS',
                                      results: [[path: 'allure-results']]
                 ])
           }

        }
    }
}