import groovy.json.JsonSlurper

String basePath = "jenkins-demo"

def gitUrl = 'git@github.com:kdeng/jenkins-app-demo.git'
def project = 'kdeng/jenkins-app-demo'
URL branchApi = new URL("https://api.github.com/repos/${project}/branches")
List branches = new JsonSlurper().parse(branchApi.newReader())

folder(basePath) {
    description 'This shows how to crate a set of jobs for each github branch, each in its own folder.'
}

branches.each { branch ->
    String safeBranchName = branch.name.replaceAll('/', '-')

    folder "$basePath/$safeBranchName"

    String jobName = "$basePath/$safeBranchName/${project}-${safeBranchName}"

    job("$jobName/build") {
        scm {
            git("git://github.com/${project}.git", branch.name)
        }
        steps {
            maven("test -Dproject.name=${project}/${branchName}")
        }
    }
    job("$jobName/deploy") {
        parameters {
            stringParam 'host'
        }
        steps {
            shell 'echo hello'
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