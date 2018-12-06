def gitUrl = 'git@github.com:kdeng/jenkins-app-demo.git'
def project = 'kdeng/jenkins-app-demo'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())

branches.each {
    def branchName = it.name
    def jobName = "${project}-${branchName}".replaceAll('/','-')
    job(jobName) {
        scm {
            git("git://github.com/${project}.git", branchName)
        }
        steps {
            maven("test -Dproject.name=${project}/${branchName}")
        }
    }
}
// job('PROJ-sonar') {
//     scm {
//         git(gitUrl)
//     }
//     triggers {
//         cron('15 13 * * *')
//     }
//     steps {
//         maven('sonar:sonar')
//     }
// }

// job('PROJ-integration-tests') {
//     scm {
//         git(gitUrl)
//     }
//     triggers {
//         cron('15 1,13 * * *')
//     }
//     steps {
//         maven('-e clean integration-test')
//     }
// }

// job('PROJ-release') {
//     scm {
//         git(gitUrl)
//     }
//     // no trigger
//     authorization {
//         // limit builds to just Jack and Jill
//         permission('hudson.model.Item.Build', 'jill')
//         permission('hudson.model.Item.Build', 'jack')
//     }
//     steps {
//         maven('-B release:prepare release:perform')
//         shell('cleanup.sh')
//     }
// }