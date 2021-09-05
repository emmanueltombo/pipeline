pipeline {
    agent any

    options {
      timestamps()
    }

    tools {
        maven 'Maven 3.5.2' 
        jdk 'jdk8'
    }

    environment {
        POM_VERSION = getVersion()
        JAR_NAME = getJarName()
    }

    stages {
        stage('Build & Test') {
            steps{
                 echo "Salut hey"
            }
        }

        stage('Build Docker Image') {
            steps{
                echo ${POM_VERSION}
            }
        }

        stage('Push Image to ECR') {}

        stage('Deploy in ECS') {}
    }

    post {}
}
