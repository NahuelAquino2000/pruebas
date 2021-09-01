pipeline{
    
    agent any

    environment {
        //podría mandar los JOBS ID acá (al parecer no se puede) o en el groovy script
        // JOBS_ID = 'PRITDUR2' //no puedo poner listas
        def variable1 = "true"
        def variable2 = "false"
        // JOB_PREREQ_ACCOMP = true
        // JOB_PREREQ_CANBEEXEC = true
        // JOB_HAS_MULTIJOBS = true
        
    }

    stages {

        stage ("Primero"){
            when { //¿¿esto solo funciona con las variables que te da JENKINS??
                expression {
                    variable1 == "true" 
                }
            }
            steps{
                //comparar el job id de la petición contra los jobs id que existen
                //sería algo como 
                //if job_id matches jobs_ids_list then do nothing
                //if job_id doesn't matches jobs_ids_list then do JOBS_ID_EXIST = false y terminar ticket
                echo "primero prueba"
                // JOB_HAS_PREREQ = false
                // echo "${JOB_HAS_PREREQ} is true?"
                }
                }

        stage ("Segundo"){
            when { //¿¿esto solo funciona con las variables que te da JENKINS??
                expression {
                    variable2 = "true"  
                }
            }
            steps{
                //comparar el job id de la petición contra los jobs id que existen
                //sería algo como 
                //if job_id matches jobs_ids_list then do nothing
                //if job_id doesn't matches jobs_ids_list then do JOBS_ID_EXIST = false y terminar ticket
                echo "funcionó la wea"
                }
                }

            }
}
