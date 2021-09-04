pipeline{
  agent any
  stages{
    stage("test"){
      steps{
        sh "mkdir /home/pipelineProject"
        sh "cd /home/pipelineProject/"
        sh "git clone https://github.com/emmanueltombo/pipeline.git"
      }
    }
  }
}
