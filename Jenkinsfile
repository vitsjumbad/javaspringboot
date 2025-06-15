pipeline {
    agent any

    tools {
        maven 'Maven 3'  // Must match the tool name configured in Jenkins
    }

    stages {
        stage('Clone Repo') {
            steps {
                echo 'Cloning GitHub repository...'
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t springboot-bookapi .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 9090:9090 --name bookapi springboot-bookapi'
            }
        }
    }
}

