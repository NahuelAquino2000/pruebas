def jobidcheck() {
    
    def jobsid = ['job1','job2','job3','job4'];
    echo "${jobsid}"
    println("prueba de println " + jobsid)

}

def otropaso() {
    
    def otropasovar = 'random1'

    echo "${jobsid}"
    echo "${otropasovar}"
    println("prueba de println " + jobsid[3])

}

return this //Esto es para importarlo al Jenkinsfile


//desde acá puedo llamar variables del Jenkinsfile
