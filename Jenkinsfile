pipeline {
    
    agent any

    environment {
        //podría mandar los JOBS ID acá
        JOBS_ID = ('PRITDUR2', 'PRITDUR4', 'PRRHPREJ', 'RRRHVACA')
        VAR_TEST = 'Prueba Variable 1'
    }
    
    stages {

        stage ("init"){ //creo que es para llamar el script de groovy
            
            steps{
                script {
                    gv = load "script.groovy"
                }

            }

        }
        stage ("JobIDCheck"){

            steps{
                echo "probando si funcionan las variables ${JOBS_ID}"

            }
        }

        stage ("TestingGroovyScript"){
            
            steps{
                script {
                    gv.function()
                }

            }

        }
    }

    post{ 
        always{
            //Close the ticket
        }
    }

}
