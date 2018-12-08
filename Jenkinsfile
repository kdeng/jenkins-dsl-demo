
// pipeline {
//     agent none
//     stages {
//         stage('Build') {
//             steps {
//                 jobDsl ignoreExisting: true, sandbox: true, targets: 'src/jobs/job1.groovy'
//             }
//         }
//     }
// }


node {
    // jobDsl scriptText: 'job("example-2")'
    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        jobDsl  targets: ['./src/jobs/job2.groovy'].join('\n'),
                // ignoreExisting: true,
                // sandbox: true,
                removedJobAction: 'DELETE',
                removedViewAction: 'DELETE',
                lookupStrategy: 'SEED_JOB',
                additionalParameters: [message: 'Hello from pipeline', credentials: 'SECRET']
    }
}