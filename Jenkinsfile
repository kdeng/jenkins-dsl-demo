pipeline {
    agent none
    stages {
        stage('Build') {
            steps {
                jobDsl ignoreExisting: true, sandbox: true, targets: 'src/jobs/**/*.groovy'
            }
        }
    }
}