pipeline {
  agent any
  stages {
    stage('GetCode') {
      steps {
        git(url: 'https://github.com/Alan9249/push.git', branch: 'master', changelog: true)
      }
    }

  }
}