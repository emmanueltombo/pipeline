pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }

    tools {
        jdk 'jdk8'
        maven 'm3' 
    }

    environment {
        POM_VERSION=getVersion()
        JAR_NAME = getJarName()
        AWS_ECR_REGION='us-east-2'
        AWS_ECS_TASK_DEFINITION='pipeline-demo-task-definition'
        AWS_ECS_COMPATIBILITY='EC2'
        AWS_ECS_NETWORK_MODE='awsvpc'
        AWS_ECS_CPU='256'
        AWS_ECS_MEMORY='256'
        AWS_ECS_CLUSTER='finex-clusterr'
        AWS_ECS_SERVICE='pipeline-demo-service'
        
        
    }

    stages {
        stage('Build & Test') {
            steps{
               sh "mvn -B -U clean package"
            }
        }

        stage('Build Docker Image') {
              steps{
                  script{
                    docker.build("${AWS_ECR_URL}:${POM_VERSION}", "--build-arg JAR_FILE=${JAR_NAME} .")
                  }
            }
        }

        stage('Push Image to ECR') {
            steps{
                withCredentials([string(credentialsId:'AWS_ECR_SECRET_ACCESS_KEY',variable:'')]){
                    withAWS(region: "${AWS_ECR_REGION}", credentials: 'creasmit_aws_ecr_dev') {
                     script {
                         def login = ecrLogin()
                         sh('#!/bin/sh -e\n' + "${login}") 
                         docker.image("${AWS_ECR_URL}:${POM_VERSION}").push()
                        }
                     }
                }
            }
        }

        stage('Deploy in ECS') {
            steps {
                withCredentials([string(credentialsId: 'AWS_ECR_SECRET_ACCESS_KEY', variable: '')]) {
                    script {
                        updateContainerDefinitionJsonWithImageVersion()
                        sh("aws ecs register-task-definition --region ${AWS_ECR_REGION} --family ${AWS_ECS_TASK_DEFINITION} --execution-role-arn ${AWS_ECS_EXECUTION_ROL} --requires-compatibilities ${AWS_ECS_COMPATIBILITY} --network-mode ${AWS_ECS_NETWORK_MODE} --cpu ${AWS_ECS_CPU} --memory ${AWS_ECS_MEMORY} --container-definitions file://${AWS_ECS_TASK_DEFINITION_PATH}")
                        def taskRevision = sh(script: "/usr/local/bin/aws ecs describe-task-definition --task-definition ${AWS_ECS_TASK_DEFINITION} | egrep \"revision\" | tr \"/\" \" \" | awk '{print \$2}' | sed 's/\"\$//'", returnStdout: true)
                        sh("aws ecs update-service --cluster ${AWS_ECS_CLUSTER} --service ${AWS_ECS_SERVICE} --task-definition ${AWS_ECS_TASK_DEFINITION}:${taskRevision}")
                    }
                }
            }
        }
    }

//post {}
}

//functions def
//get jar name of project
def getJarName(){
    def jarName = getName() + '-' + getVersion() + '.jar'
    echo "jarName: ${jarName}"
    return  jarName
}

def getVersion() {
    def pom = readMavenPom file: './pom.xml'
    return pom.version
}

def getName() {
    def pom = readMavenPom file: './pom.xml'
    return pom.name
}

def updateContainerDefinitionJsonWithImageVersion(){
    def containerDefinitionJson = readJSON file: AWS_ECS_TASK_DEFINITION_PATH, returnPojo: true
    containerDefinitionJson[0]['image'] = "${AWS_ECR_URL}:${POM_VERSION}".inspect()
    echo "task definiton json: ${containerDefinitionJson}"
    writeJSON file: AWS_ECS_TASK_DEFINITION_PATH, json: containerDefinitionJson
}
