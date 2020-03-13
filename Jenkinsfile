pipeline {
  agent any
  stages {
    stage('GetCode') {
      steps {
        git(url: 'https://github.com/Alan9249/push.git', branch: 'master', changelog: true)
      }
    }

    stage('Package') {
      steps {
        tool(name: 'jdk', type: '\'jdk-8u111\'')
        tool(name: 'maven', type: '\'mvn-3.6.3\'')
      }
    }

  }
}