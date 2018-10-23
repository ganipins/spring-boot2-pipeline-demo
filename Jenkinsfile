pipeline {
  agent any
  
  tools {
    jdk 'jdk1.8.0_181'
    gradle 'gradle-4.10.2'
  }
  
  environment {
    EMAIL_RECIPIENTS = 'sam@test.com'
  }
  
  triggers {
      pollSCM('H/5 * * * *')
  }

  stages {
    stage('Initialize') {
       steps {
           echo 'Initializing...'    
           echo "JDK installation path is: ${env.JAVA_HOME}"
           echo "Gradle installation path is: ${env.GRADLE}"
           echo "env PATH is: ${env.PATH}"
           echo "Starting Build, triggered by $BRANCH_NAME";
           echo "Building ${env.BUILD_ID}";
           echo "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER}"
       }
    }

    stage('Build') {
      steps {
         echo 'Building...'
         gradlew('clean', 'build')
      }
    }
    
    stage('SonarQube Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube Server') {
                   gradlew('sonarqube')
                }
            }
    }
    
    stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    // Requires SonarQube Scanner for Jenkins 2.7+
                    waitForQualityGate abortPipeline: true
                }
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
        gradlew('bootJar')
        stash includes: '**/build/libs/*.jar', name: 'spring-boot2-pipeline-demo'
      }
    }
    
    stage('Deploy to Test') {
        when { branch 'test' } 
        steps {
            script {
                echo "Deploying  to Test"
            }
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
      when {
          branch 'master'
      }

      steps {
        echo 'Deploying Master branch to Production'
      }
    }
    
    stage('slack-notify') {
        steps {
            echo 'slack-notify'
        }
    }
    
  }
  post {
     always {
         archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
         junit 'build/reports/**/*.xml'
     }

     failure {
         mail to: 'sam.test@gmail.com', subject: 'Build failed', body: 'Please fix the build ASAP!'
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