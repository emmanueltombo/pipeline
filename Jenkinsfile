pipeline {
    agent any

    options {
      timestamps()
    }

    tools {
        jdk 'jdk8'
        maven 'm3' 
    }

    environment {
        JAR_NAME = "pipelineDemo"
    }

    stages {
        stage('Build & Test') {
            steps{
                withCredentials([string(credentialsId: 'creasmit_aws_ecr_dev', variable: 'AWS_ECR_URL')]) {
                    script {
                        docker.build("${AWS_ECR_URL}:${POM_VERSION}", "--build-arg JAR_FILE=${JAR_NAME} .")
                     }
                }
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
