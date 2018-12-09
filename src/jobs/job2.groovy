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
    String jobName = "$basePath/$safeBranchName"

    folder "$basePath"

    pipelineJob("$jobName") {

        description("Pipeline for $project-${branch.name}")

        definition {
            cpsScm {
              lightweight(true)
              scriptPath('Jenkinsfile')
              scm {
                  git {
                    branches('master')
                    remote {
                      url("git://github.com/${project}.git")
                    }
                    extensions {
                    }  // required as otherwise it may try to tag the repo, which you may not want
                  }
              }
            }
        }


        // scm {
        //     git("git://github.com/${project}.git", branch.name)
        // }
        // steps {
        //     // GIT_BRANCH_LOCAL = sh (
        //     //     script: "git rev-parse --abbrev-ref HEAD",
        //     //     returnStdout: true
        //     // ).trim()
        //     // echo "Git branch: ${GIT_BRANCH_LOCAL}"
        //     maven("test -Dproject.name=${project}/${safeBranchName} -Dversion=\"${BUILD_NUMBER}\"")
        // }
    }

    // job("$jobName/deploy") {
    //     parameters {
    //         stringParam 'host'
    //     }
    //     steps {
    //         shell 'echo hello "${BUILD_NUMBER}"'
    //     }
    // }

}
