pipeline {
    agent any
    stages {
        stage('Build') {
            environment {
                CONTROLM_CREDS = credentials('CredentialsTest')
                ENDPOINT = 'https://192.168.1.17:8443/automation-api'
            }
            steps {
                sh '''
                username=$CONTROLM_CREDS_USR
                password=$CONTROLM_CREDS_PSW
                
                # Login
                login=$(curl -k -s -H "Content-Type: application/json" -X POST -d \\{\\"username\\":\\"$username\\",\\"password\\":\\"$password\\"\\} "$ENDPOINT/session/login" )
                token=$(echo ${login##*token\\" : \\"} | cut -d '"' -f 1)
                
                # Curl -v
                curl -k -v https://192.168.1.17:8443/automation-api

                #Test Build
                curl -k -H "Authorization: Bearer $token" -X POST -F "definitionsFile=@job.json" "$ENDPOINT/deploy"
                
                #Test Run
                runId=$(curl -k -H "Authorization: Bearer $token" -X POST -F "jobDefinitionsFile=@job.json" "$ENDPOINT/run")

                #¿Como consigo el runId para ponerlo en una variable ($runId)?

                echo $runId

                #Test status 
                #curl -k -H "Authorization: Bearer $token" "$ENDPOINT/run/status/$runId" "$ENDPOINT/run"


                '''
            }
        }
        
    }
}
