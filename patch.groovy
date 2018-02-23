#!groovy
node {

    stage("starting") {
         	    
    }
    
     stage("Patch Servers with Chef Knife") {
          try {

            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${credentialsId}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh "knife ssh chef_environment:${chef_env} 'sudo -S ${command}' -P '${PASSWORD}' -x ${USERNAME}"
              }
          } catch (e) {
              currentBuild.result = "FAILED"
              notifyFailed("${failure_email_address}")
              throw e
          }

     }
     

     stage("ending") {
         
     }

}



