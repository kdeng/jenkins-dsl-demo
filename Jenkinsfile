
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
    jobDsl scriptText: 'job("example-2")'
    
    jobDsl  ignoreExisting: true,
            sandbox: true,
            targets: 'src/jobs/job1.groovy',
            removedJobAction: 'DELETE',
            removedViewAction: 'DELETE',
            lookupStrategy: 'SEED_JOB'
}