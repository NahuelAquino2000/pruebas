pipeline {
    agent any
    stages {
        stage('Build') {
            environment {
                // CONTROLM_CREDS = credentials('CredControlM') //Ya están en Jenkins - Sería "ctmapiusr" o "CredControlM" ?
                ENDPOINT = 'https://192.168.1.17:8443/automation-api' //Cual sería el nuestro? "https://labctrolm:8443/automation-api"
            }
            steps {
                sh '''
                username=workbench
                password=workbench
                # Login
                login=$(curl -k -s -H "Content-Type: application/json" -X POST -d \\{\\"username\\":\\"$username\\",\\"password\\":\\"$password\\"\\} "$ENDPOINT/session/login" )
                token=$(echo ${login##*token\\" : \\"} | cut -d '"' -f 1)
                # Build
                curl -k -v https://192.168.1.17:8443/automation-api
                '''
            }
        }
        
    }
}
