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
        }

        stage ("Definir jobs id"){
            steps{
                script{
                    gv.jobidcheck
                }
                }
            }
        }

        stage ("Echo jobs id"){
            steps{
                echo "${jobsid}"
                echo jobsid
                }
            }
    post {
        always{
            echo 'post condition always'
        }
    }
