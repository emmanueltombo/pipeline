pipeline {
    agent any

    options {
      timestamps()
    }

    tools {
        jdk 'jdk8'
        maven 'M3' 
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
                echo "${POM_VERSION}"
            }
        }

        stage('Push Image to ECR') {
            steps{
                echo "${POM_VERSION}"
            }
        }

        stage('Deploy in ECS') {
            steps{
                echo "${POM_VERSION}"
            }
        }
    }

//post {}
}
