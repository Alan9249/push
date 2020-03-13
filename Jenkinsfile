pipeline {
  agent any
  tools {
        jdk 'jdk'
        maven 'maven'
    }
  stages {
    stage('GetCode') {
      steps {
        git(url: 'https://github.com/Alan9249/push.git', branch: 'master', changelog: true)
      }
    }
  }
}
