def function() {
    def ageOld = 6

    if(ageOld == 5){
        println("Go to kindergarten")
    } else if((ageOld > 5) && (ageOld < 18)){
        printf("Go to grande %d \n", (ageOld - 5));
    } else {
      println("Go to college");
    }
       
    }
}

return this //Esto es para importarlo al Jenkinsfile


//desde acá puedo llamar variables del Jenkinsfile
