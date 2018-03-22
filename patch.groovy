#!groovy
node {

    stage("starting") {
         	    
    }
    
     stage("Patch Servers with Chef Knife") {
          try {

            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${credentials}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh "knife ssh tags:${chef_env} 'sudo -S ${command}' -P '${PASSWORD}' -x ${USERNAME}"
                  }
          } 

     }
   }   

     stage("ending") {
         
     }

}



