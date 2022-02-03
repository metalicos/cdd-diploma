properties([disableConcurrentBuilds()])
pipeline {
  agent any
  triggers {
    pollSCM('* * * * *')
  }
  environment {
    IMAGE = ""
    VERSION = ""
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    skipDefaultCheckout(true)
    skipStagesAfterUnstable()
    timestamps()
  }
  stages {
    stage('Prepare') {
      steps {
        checkout scm
        script {
          IMAGE = readMavenPom().getArtifactId().toLowerCase()
          VERSION = readMavenPom().getVersion().toLowerCase()
        }
        echo IMAGE
        echo VERSION
      }
    }
    stage('Build') {
      steps {
        echo "=============================== STARTING BUILD ====================================="
        withMaven(maven: 'maven-latest') {
          bat "mvn clean install"
        }
        echo "=============================== BUILD SUCCESSFUL ==================================="
      }
    }
    stage('Create Docker Image') {
      steps {
        echo "========================== STARTING DOCKER IMAGE CREATION =========================="
        bat "docker build -t ${IMAGE}:${VERSION} -t ${IMAGE}:latest ."
        echo "======================== DOCKER IMAGE CREATION IS SUCCESSFUL ======================="
      }
    }
    stage('Run Docker Image') {
      steps {
        echo "=============================== STARTING DEPLOY ===================================="
        script {
          try {
            bat "docker stop ${IMAGE}-${VERSION}"
            bat "docker rm ${IMAGE}-${VERSION}"
          } catch (Exception e) {
            echo "${IMAGE}-${VERSION} container is not running."
          }
          bat "docker run -d -t -p 8761:8761 --name=${IMAGE}-${VERSION} ${IMAGE}"
          echo "=============================== DEPLOY SUCCESSFUL =================================="
        }
      }
    }
  }
}