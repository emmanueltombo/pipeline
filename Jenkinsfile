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
    }

    stages {
        stage('Build & Test') {
            steps{
              withMaven(options: [artifactsPublisher(), mavenLinkerPublisher(), dependenciesFingerprintPublisher(disabled: true), jacocoPublisher(disabled: true), junitPublisher(disabled: true)]) {
                    sh "mvn -B -U clean package"
                }
            }
        }

        stage('Build Docker Image') {
              steps{
                withCredentials([string(credentialsId: 'AWS_ECR_SECRET_ACCESS_KEY', variable: '')]) {
                    script {
                        docker.build(${AWS_ECR_URL}:${POM_VERSION}", "--build-arg JAR_FILE=${JAR_NAME} .")
                     }
                }
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
