pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
      }
    }
    stage('Unit Tests') {
      steps {
        echo 'Unit Testing...'
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
      }
    }
    stage('Approve') {
      steps {
        echo 'Approve'
      }
    }
    stage('Deploy to Production') {
      steps {
        echo 'Deploying'
      }
    }
  }
  environment {
    EMAIL_RECIPIENTS = 'sam@test.com'
  }
}