#!groovy
node {

    stage("starting") {
         	    
    }
    
     stage("Patch Servers with Chef Knife") {
          try {

            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${credentialsId}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh "knife ssh name:${hostname} 'sudo -S ${command}' -P '${PASSWORD}' -x ${USERNAME}"
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



def notifyFailed(failure_email_address) {
		def details = """Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
		Check console output at '${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]"""
		
	  emailext (
		  to: '${failure_email_address}',
		  from: 'azu-jenkins02@footlocker.com',
		  subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
		  body: details,
		  recipientProviders: [[$class: 'DevelopersRecipientProvider']]
		)
}
