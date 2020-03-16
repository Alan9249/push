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
        sh 'mvn clean package'
      }
    }

    stage('SSH transfer') {
        steps([$class: 'BapSshPromotionPublisherPlugin']) {
            sshPublisher(
                continueOnError: false, failOnError: true,
                publishers: [
                    sshPublisherDesc(
                        configName: "jiangli",
                        verbose: true,
                        transfers: [
                            sshTransfer(sourceFiles: "target/**",)
                        ]
                    )
                ]
            )
        }
    }
  }
  
  tools {
    jdk 'jdk'
    maven 'maven'
  }
}
