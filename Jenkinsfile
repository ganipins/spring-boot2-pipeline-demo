pipeline {
  agent any
  
  tools {
    jdk 'jdk1.8.0_181'
  }
  
  environment {
    EMAIL_RECIPIENTS = 'sam@test.com'
  }
  
  triggers {
      pollSCM('*/5 * * * *')
  }

  stages {
    stage('Build') {
      steps {
        echo 'Building...'    
        echo "JDK installation path is: ${env.JAVA_HOME}"
        echo "env PATH is: ${env.PATH}"
        gradlew('clean', 'build')
      }
    }
    
    stage('Unit Tests') {
      steps {
        echo 'Unit Testing...'
        gradlew('test')
      }
      post {
          always {
              junit '**/build/test-results/test/TEST-*.xml'
          }
      }
    }
    
    stage('Verification') {
      parallel {
        stage('Code Analysis') {
          steps {
            echo 'Code Analysis'
          }
        }
        stage('Integration Testing') {
          steps {
            echo 'Integration Testing...'
          }
        }
      }
    }
    stage('Package') {
      steps {
        echo 'Packaging...'
        gradlew('assemble')
        stash includes: '**/build/libs/*.war', name: 'app'
      }
    }
    stage('Approve') {
      steps {
        echo 'Approve'
        timeout(time: 1, unit:'DAYS') {
            input 'Deploy to Production?'
        }
      }
    }
    stage('Deploy to Production') {
      steps {
        echo 'Deploying'
      }
    }
  }
  post {
     failure {
         mail to: 'sam.test@gmail.com', subject: 'Build failed', body: 'Please fix!'
     }
  }
}
  
def gradlew(String... args) {
    if (isUnix()) {
       sh "./gradlew ${args.join(' ')} -s"
    } else {
       bat("./gradlew ${args.join(' ')} -s")       
    }
}