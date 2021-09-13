pipeline {
    agent any
    parameters {
        choice(
            choices: ['SimpleFolder' , 'SmartFolder', 'SmartFolderComplex'],
            description: 'Select the folder that you want to deploy',
            name: 'filename')
    }
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
                #curl -k -v https://192.168.1.11:8443/automation-api

                #Test deploy job & get folder name
                folderName=$(curl -k -H "Authorization: Bearer $token" -X POST -F "definitionsFile=@$filename.json" "$endpoint/deploy" | grep deployedFolders | cut -d '"' -f 4)

                
                #Test run order of a non-deployed job & get run id (funciona)
                #runId=$(curl -k -H "Authorization: Bearer $token" -X POST  -F "jobDefinitionsFile=@job.json" "$endpoint/run" | grep runId | cut -d '"' -f 4)

                #Test find job definitions
                curl -k  -H "Authorization: Bearer $token" "$endpoint/deploy/jobs?server=$ctm&folder=$folderName"

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
                }" "$endpoint/run/order" | grep runId | cut -d '"' -f 4)

                echo "este es tu variable runId = $runId"                

                #Test find job definitions
                curl -k  -H "Authorization: Bearer $token" "$endpoint/deploy/jobs?server=$ctm&folder=$folderName"

                #Test get jobs outputs ¿De donde saco el jobId? 
                #The jobId is used to reference the specific job and is returned by ctm run status. The format of this ID is <ctm_server>:<orderId>.
                
                echo "GUARDO EN JOBID"
                jobId=$(curl -kH "Authorization: Bearer $token" "$endpoint/run/status/$runId")
                #| grep -HRl "jobId" . | cut -d '"' -f 4)
                echo $jobId

                #curl -k -H "Authorization: Bearer $token" "$endpoint/run/job/$jobId/output/?runNo=0"

                #Test status (no funciona)
                #curl -k -H "Authorization: Bearer $token" "$endpoint/run/status/$runId"

                #Test run status (no funciona)
                #curl -k -H "Authorization: Bearer $token" "$endpoint/run/status/$runId/log"

                #Test get log (no funciona)
                #curl -H "Authorization: Bearer $token" "$endpoint/run/job/$jobId/log"
                '''
            }
        }
        
    }
}


