pipeline{
    
    agent any

    environment {
        //podría mandar los JOBS ID acá
        JOBS_ID = 'PRITDUR2'
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
        stage ("Probando Groovy Scripts"){

            steps{
                gv.function()
        
            }
        }
}
}
