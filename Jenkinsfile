pipeline {
    agent any
    stages {
        stage('Build') {
            environment {
                CONTROLM_CREDS = credentials('CredControlM') //Ya están en Jenkins - Sería "ctmapiusr" o "CredControlM" ?
                ENDPOINT = 'https://192.168.1.17:8443/automation-api' //Cual sería el nuestro? "https://labctrolm:8443/automation-api"
            }
            steps {
                sh '''
                username=$CONTROLM_CREDS_USR
                password=$CONTROLM_CREDS_PSW
                # Login
                login=$(curl -k -s -H "Content-Type: application/json" -X POST -d \\{\\"username\\":\\"$username\\",\\"password\\":\\"$password\\"\\} "$ENDPOINT/session/login" )
                token=$(echo ${login##*token\\" : \\"} | cut -d '"' -f 1)
                # Build
                curl -v https://192.168.1.17:8443/automation-api
                '''
            }
        }
        // stage ('Job PRCMRCAL') {
        //     when {
        //         expression { params.JOB_ID == 'PRCMRCAL' }
        //     }
        //     steps {
        //         sh 'echo "Running $JOB_ID + PRCMRCALI"'
        //         sh 'echo "Executing Smart folder script"'
        //         sh 'echo "Running scripts"'
        //     }
        // }
        // stage ('Job PRITDUR2') {
        //     when {
        //         expression { params.JOB_ID == 'PRITDUR2' }
        //     }
        //     steps {
        //         sh '/run PRITDUR2' // ????
        //     }
        // }
        // stage ('Job PRITDUR4') {
        //     when {
        //         // Only say hello if a "greeting" is requested
        //         expression { params.JOB_ID == 'PRITDUR4' }
        //     }
        //     steps {
        //         sh 'echo "No pre-requisites needed. Running script for $JOB_ID"'
        //     }
        // }
        // stage ('Job PRRHPREJ') {
        //     when {
        //         // Only say hello if a "greeting" is requested
        //         expression { params.JOB_ID == 'PRRHPREJ' }
        //     }
        //     steps {
        //         sh 'echo "No pre-requisites needed. Running script for $JOB_ID"'
        //     }
        // }
        // stage ('Job PRRHVACA') {
        //     when {
        //         // Only say hello if a "greeting" is requested
        //         expression { params.JOB_ID == 'PRRHVACA' }
        //     }
        //     steps {
        //         sh 'echo "No pre-requisites needed. Running script for $JOB_ID"'
        //     }
        // }
    }
    // post {
    //     always {
    //         sh '''
    //             cat << EOF > test.py
    //                 import urllib3
    //                 http = urllib3.PoolManager()
    //                 r = http.request('GET', 'https://labctrolm:8443')
    //                 r.status
    //                 print(r.data)
    //             EOF
    //             python3.8 ./test.py
    //             '''
    //         sh 'curl -X https://labctrolm:8443/automation-api/deploy/jobs?format=json&folder=test&server=https%3A%2F%2Flabctrolm%3A8443%2F'
    //     }
    // }
}
