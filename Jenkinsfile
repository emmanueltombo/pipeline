pipeline {
    agent any

    options {
      timestamps()
    }

    tools {
        maven 'Maven 3.5.2' 
        jdk 'jdk8'
    }

    environment {}

    stages {
        stage('Build & Test') {
            echo "Salut hey"
        }

        stage('Build Docker Image') {}

        stage('Push Image to ECR') {}

        stage('Deploy in ECS') {}
    }

    post {}
}
