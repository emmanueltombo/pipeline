pipeline{
  agent any
  
  options{
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
