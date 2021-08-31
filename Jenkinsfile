pipeline {
    
    agent any

    environment {
        //podría mandar los JOBS ID acá
        JOBS_ID = 'PRITDUR2'
    }
    
    stages {

        stage ("jobidcheck"){

            steps{
                echo "probando su funciona ${JOBS_ID}"

            }
        }

        stage ("Prereqcheck"){
            
            steps{

            }

        }
    }

    post{ 
        always{
            //Close the ticket
        }
    }

}
