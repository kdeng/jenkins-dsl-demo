
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

    jobDsl  ignoreExisting: true,
            // sandbox: true,
            targets: ['./src/jobs/job1.groovy', '**/job1.groovy'].join('\n'),
            removedJobAction: 'DELETE',
            removedViewAction: 'DELETE',
            lookupStrategy: 'SEED_JOB',
            additionalParameters: [message: 'Hello from pipeline', credentials: 'SECRET']
}