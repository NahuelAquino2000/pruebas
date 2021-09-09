pipeline {
    agent any
    stages {
        stage('Build') {
            environment {
                CONTROLM_CREDS = credentials('CredentialsTest')
                ENDPOINT = 'https://192.168.1.11:8443/automation-api'
                // ctm = 'Workbench'
            }
            steps {
                sh '''
                username=$CONTROLM_CREDS_USR
                password=$CONTROLM_CREDS_PSW
                # ctm= Workbench
                
                # Login
                login=$(curl -k -s -H "Content-Type: application/json" -X POST -d \\{\\"username\\":\\"$username\\",\\"password\\":\\"$password\\"\\} "$ENDPOINT/session/login" )
                token=$(echo ${login##*token\\" : \\"} | cut -d '"' -f 1)
                
                # Curl -v
                curl -k -v https://192.168.1.11:8443/automation-api

                #Test deploy & get folder name
                folderName=$(curl -k -H "Authorization: Bearer $token" -X POST -F "definitionsFile=@job.json" "$ENDPOINT/deploy" | grep deployedFolders | cut -d '"' -f 4)

                
                #Testeo tiene que funcionar
                runId=$(curl -k -H "Authorization: Bearer $token" -X POST  -F "jobDefinitionsFile=@job.json" "$endpoint/run" | grep runId | cut -d '"' -f 4)

                #Test Run order & get Run id
                #runId=$(curl -kX POST -H "Authorization: Bearer $token" --header "Content-Type: application/json" --header "Accept: application/json" -d "{ \"ctm\": \"$ctm\", \"folder\": \"$folderName\", \"hold\": \"false\", \"ignoreCriteria\": \"true\", \"orderDate\": \"20210909\", \"waitForOrderDate\": \"false\", \"orderIntoFolder\": \"Recent\", \"variables\": [{\"arg\":\"12345\"}]}" "$endpoint/run/order" | grep runId | cut -d '"' -f 4)

                #runId=$(curl -X POST -H "Authorization: Bearer $token" --header "Content-Type: application/json" --header "Accept: application/json" -d "{
                #  \"ctm\": \"$ctm\",
                #  \"folder\": \"$folderName\",
                #  \"hold\": \"true\",
                #  \"ignoreCriteria\": \"true\",
                #  \"orderDate\": \"20170903\",
                #  \"waitForOrderDate\": \"false\",
                #  \"orderIntoFolder\": \"Recent\",
                #  \"variables\": [{\"arg\":\"12345\"}]
                #}" "$ENDPOINT/run/order" | grep runId | cut -d '"' -f 4)

                echo "este es tu variable runId = $runId"                
                
                #Test status 
                curl -k -H "Authorization: Bearer $token" "$ENDPOINT/run/status/$runId"
                
                #Test get jobid
                curl -kH "Authorization: Bearer $token" "$ENDPOINT/run/status/$runId" | grep jobId | cut -d '"' -f 4
                '''
            }
        }
        
    }
}
