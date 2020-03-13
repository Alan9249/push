pipeline {
  agent any
  tools {
        jdk 'jdk-8u111'
        maven 'mvn-3.6.3'
    }
  stages {
    stage('GetCode') {
      steps {
        git(url: 'https://github.com/Alan9249/push.git', branch: 'master', changelog: true)
      }
    }
  }
}
