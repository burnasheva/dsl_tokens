import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.1"

project {

    vcsRoot(MavenProject)

    buildType(RunTests)
}

object RunTests : BuildType({
    name = "run tests"

    params {
        password("password.value", "credentialsJSON:33db4c5c-70f7-4fbf-856d-8f3e9216f1c1")
        password("second.pwd.value", "credentialsJSON:9abede93-e7a4-4bb8-8334-7f549f9dbeb9")
        password("third.pwd.value", "credentialsJSON:2ca745b1-b0f3-4458-8a8d-745eef6bbd46")
    }

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    features {
        pullRequests {
            provider = github {
                authType = token {
                    token = "credentialsJSON:4f3b99e1-e9c5-4bc5-bd6e-001c01c78e16"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})

object MavenProject : GitVcsRoot({
    name = "maven project"
    url = "https://github.com/burnasheva/maven_unbalanced_messages"
})
