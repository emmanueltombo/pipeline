pipeline{
  agent any
  stages{
    stage("test"){
      steps{
        sh "sudo mkdir /home/pipelineProject"
        sh "cd /home/pipelineProject/"
        sh "git clone https://github.com/emmanueltombo/pipeline.git"
      }
    }
  }
}
