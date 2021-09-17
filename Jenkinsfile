import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

@NonCPS //esto es para que no rompa en el jenkins

// aca vendría el json del service desk
// String json = '''[
// { "name" : "Omar" , "lastname" : "Bautista" , "age" : 33 },
// { "name" : "Jorge" , "lastname" : "Valenzuela" , "age" : 29 },
// { "name" : "Maria" , "lastname" : "Ojeda" , "age" : 30 }
// ]'''

// String bar = '''[{
// 	"hd": "123123", 
// 	"area": "administracion de personal", 
// 	"folderName": "SimpleFlow", 
// 	"variables": {
// 	   "%%FECHA": "010921",
// 	   "%%VAR": "1"
// 	}
//   }]'''

def hd = ''
def area = ''
def folderName = ''
def variables = ''

// node () {
//     writeFile(
//         file: "bar.json",
//         text: """\
//             {
//             "hd": "123123", 
//             "area": "administracion de personal", 
//             "folderName": "SERVCONT", 
//             "variables": {
//                 "%%FECHA":"010921","%%VAR":"1"
//                 }
//             }
//         """.stripIndent()
//     )
// }

node () {
    writeFile(
        file: "bar.json",
        text: """\
            {
                "SimpleFolder" : {
                    "Type" : "SimpleFolder",

                    "Job1": {
                        "Type" : "Job:Command",
                        "Comment" : "Nahuel tests",
                        "Command" : "echo 'Job1 de SimpleFolder'",
                        "RunAs" : "workbench"  
                    },
                    "Job2": {
                        "Type": "Job:Command",
                        "Comment" : "Nahuel tests",
                        "Command" : "echo 'Job2 de SimpleFolder'",
                        "RunAs": "workbench"
                    }        
            }
            }
        """.stripIndent()
    )
}


pipeline {
    agent any
    stages {
        stage('chequear las variables traidas del json con el slurper') {
            steps {

                checkout scm

                script {  
                    def json = readFile(file: "bar.json") 
                    def data = new JsonSlurper().parseText(bar)
                    echo "fecha: ${data.variables.'%%FECHA'} \n hd number: ${data.hd} \n var = ${data.'%%VAR'}"

                    env.HD_NUMBER = "${data.hd}"
                    env.VAR_1 = "${data.'%%VAR'}"

                    areaName = "${data.area}"
                    folderName = "${data.folderName}"
                }
                
                
                echo "HD_NUMBER = ${env.HD_NUMBER}"
                echo "VAR_1 = ${env.VAR_1}"

                echo "areaName = ${areaName}"
                echo "folderName = ${folderName}"

                
            }
        }

        stage('PRUEBA DE VARIABLES DE ENTORNO ENTRE STAGES') {
            steps {
                
                echo "HD_NUMBER = ${env.HD_NUMBER}"
                echo "VAR_1 = ${env.VAR_1}"
                
                echo "areaName = ${areaName}"
                echo "folderName = ${folderName}"

            }
        }

        stage('logearse en control-m') {
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
                '''
            }
        }
        
        stage('correr los jobs necesarios') {
            steps {
                
                echo "ESTA ES LA FOLDERNAME PARA CORRER EL JOB folderName = ${folderName}"
                
                sh '''
                #Test deploy job & get folder name
                #folderName=$(  | grep deployedFolders | cut -d '"' -f 4)
                
                curl -k -H "Authorization: Bearer $token" -X POST -F "definitionsFile=@SimpleFolder.json" "$endpoint/deploy"
                 

                #Encontrar la manera de conseguir el foldername sin hacer un deploy

                #Test find job definitions
                curl -k  -H "Authorization: Bearer $token" "$endpoint/deploy/jobs?server=$ctm&folder=${folderName}"

                #Test Run order of a deployed job & get Run id
                runId=$(curl -k -H "Authorization: Bearer $token" -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{
                  \\"ctm\\": \\"$ctm\\",
                  \\"folder\\": \\"$folderName\\",
                  \\"hold\\": \\"true\\",
                  \\"ignoreCriteria\\": \\"true\\",
                  \\"orderDate\\": \\"20210910\\",
                  \\"waitForOrderDate\\": \\"false\\",
                  \\"independentFlow\\": \\"true\\",
                  \\"orderIntoFolder\\": \\"Recent\\",
                  \\"variables\\": [{\\"arg\\":\\"12345\\"}]
                }" "$endpoint/run/order" | grep runId | cut -d '"' -f 4)

                echo "este es tu variable runId = $runId"                

                #Test get jobs outputs ¿De donde saco el jobId? 
                #The jobId is used to reference the specific job and is returned by ctm run status. The format of this ID is <ctm_server>:<orderId>.
                
                echo "GUARDO EN JOBID"
                jobId=$(curl -kH "Authorization: Bearer $token" "$endpoint/run/status/$runId" | grep -HRl "jobId" . | cut -d '"' -f 4)
                echo $jobId

                #curl -k -H "Authorization: Bearer $token" "$endpoint/run/job/$jobId/output/?runNo=0"

                #Test status (no funciona)
                #curl -k -H "Authorization: Bearer $token" "$endpoint/run/status/$runId"
                '''
            }
        }

        
    }

    // post {
    //     // Clean after build //Esto es un ejemplo, acomodar
    //     always {
    //         cleanWs(cleanWhenNotBuilt: false,
    //                 deleteDirs: true,
    //                 disableDeferredWipeout: true,
    //                 notFailBuild: true)
    //     }    
}

