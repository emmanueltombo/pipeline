pipeline{
  agent any
  
  tools{
    jdk 'java-1.8.0-openjdk-devel'
    maven 'maven 3.5.2'
    dockerTool 'docker-latest'
  }
  
  options{
    buildDiscarder(logRotator(numToKeepStr: '10'))
    disableConcurrentBuilds()
    timeout(time: 1, unit: 'HOURS')
    timestamps()
  }
  
  
  stages{
    stage("test"){
      steps{
        sh "cd /home"
        sh "git clone https://github.com/emmanueltombo/pipeline.git"
      }
    }
  }
}
