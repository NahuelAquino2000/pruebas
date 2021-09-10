pipeline {
    agent any
    stages {
        stage('Build') {
            environment {
                CONTROLM_CREDS = credentials('CredentialsTest')
                endpoint = 'https://192.168.1.11:8443/automation-api'
                ctm = 'workbench'
            }
            steps {
                sh '''
                username=$CONTROLM_CREDS_USR
                password=$CONTROLM_CREDS_PSW
                
                # Login
                login=$(curl -k -s -H "Content-Type: application/json" -X POST -d \\{\\"username\\":\\"$username\\",\\"password\\":\\"$password\\"\\} "$endpoint/session/login" )
                token=$(echo ${login##*token\\" : \\"} | cut -d '"' -f 1)
                
                # Curl -v
                curl -k -v https://192.168.1.11:8443/automation-api

                #Test deploy job & get folder name
                folderName=$(curl -k -H "Authorization: Bearer $token" -X POST -F "definitionsFile=@job.json" "$endpoint/deploy" | grep deployedFolders | cut -d '"' -f 4)

                
                #Test run order of a non-deployed job & get run id (funciona)
                #runId=$(curl -k -H "Authorization: Bearer $token" -X POST  -F "jobDefinitionsFile=@job.json" "$endpoint/run" | grep runId | cut -d '"' -f 4)

                #Test Run order of a deployed job & get Run id
                runId=$(curl -k -H "Authorization: Bearer $token" -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{
                  \\"ctm\\": \\"$ctm\\",
                  \\"folder\\": \\"$folderName\\",
                  \\"hold\\": \\"true\\",
                  \\"ignoreCriteria\\": \\"true\\",
                  \\"orderDate\\": \\"20210910\\",
                  \\"waitForOrderDate\\": \\"false\\",
                  \\"orderIntoFolder\\": \\"Recent\\",
                  \\"variables\\": [{\\"arg\\":\\"12345\\"}]
                }" "$endpoint/run/order" | grep runId | cut -d '"' -f 2)

                echo "este es tu variable runId = $runId"                
                
                #Test status 
                curl -k -H "Authorization: Bearer $token" "$endpoint/run/status/$runId"

                for i in {1...5}
                do
                    curl -k -H "Authorization: Bearer $token" "$endpoint/run/status/$runId"
                done
                '''
            }
        }
        
    }
}
