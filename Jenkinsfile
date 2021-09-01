pipeline{
    
    agent any

    environment {
        //podría mandar los JOBS ID acá (creo que no) o en el groovy script
        JOBS_ID = 'PRITDUR2'
    }
    
    stages {
        stage ("init groovy script"){
            steps{
                script{
                    gv = load "script.groovy"
                }
                }
            }

        stage ("Definir jobs id"){
            steps{
                script{
                    gv.jobidcheck()
                }
                }
            }

        stage ("Probando 2da parte del script de groovy"){
            steps{
                script{
                    gv.otropaso()
                }
                }
            }
        }
            
    post {
        always{
            echo 'post condition always'
        }
    }
}
