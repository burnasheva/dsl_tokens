package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'RunTests'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("RunTests")) {
    params {
        expect {
            password("second.pwd.value", "credentialsJSON:9abede93-e7a4-4bb8-8334-7f549f9dbeb9")
        }
        update {
            password("second.pwd.value", "credentialsJSON:1caa5e34-e021-435b-9b0f-2c54523bedb6")
        }
    }
}
