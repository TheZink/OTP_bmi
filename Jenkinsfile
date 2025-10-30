ipeline {
    agent any

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        
        // Define Docker Hub credentials ID
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        // Define Docker Hub repository name
        DOCKERHUB_REPO = 'ilkkasin/otp_bmi'
        // Define Docker image tag
        DOCKER_IMAGE_TAG = 'latest'
    }
tools{
    maven 'MAVEN_HOME' // MAVEN_HOME is the name of *your own* defined maven name in Jenkins.
}
    stages {
        stage('Checkout') {
            steps {
                git branch: 'Main', url:'https://github.com/TheZink/OTP_bmi.git'
            }
        }
        stage('Build') {
            steps {
                    bat 'mvn clean install'
            }
        }
        
        stage('Test') {
            steps{
                    bat 'mvn test'
                }
        }

        stage('Code Coverage') {
            steps {
                    bat 'mvn jacoco:report'
                }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        
         stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                    } else {
                        bat "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                            }
                        }
                    }
                }
    }
}