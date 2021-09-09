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
                # Build
                curl -k -v https://192.168.1.17:8443/automation-api
                '''
            }
        }

        stage('TestJob1') {
            environment {
                ENDPOINT = 'https://192.168.1.17:8443/automation-api'
            }
            steps {
                sh '''
                curl -k -H "Authorization: Bearer $token" -X POST -F "definitionsFile=pruebas/job.json" "$ENDPOINT/build"
                '''
            }    
        }
        
    }
}
