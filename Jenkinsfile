pipeline{
    
    agent any

    environment {
        //podría mandar los JOBS ID acá (creo que no) o en el groovy script
        JOBS_ID = 'PRITDUR2'
    }
    
    stages {
        stage ("Definir jobs id"){
            steps{
                jobsid = ['job1','job2','job3'];
                }
            }
        }

        stage ("Echo jobs id"){
            steps{
                echo jobsid
                }
            }
        }
    post {
        always{
            echo 'post condition always'
        }
    }
