#!/usr/bin/env groovy

//final buscarElementoJSON = library('buscarElementoJSON')

def call(message, field1) {
    message = message.replace("\n", "")
    message = message.replace("{", "")
    message = message.replace("}", "")
    message = message.trim()

    echo "INFO: ${message} y ${field1}"
    String[] salida = message.tokenize( ',' )
    echo "sale ${salida[1]}"

    for (String item : salida) {
        if( item.contains(field1) ) {
            String[] itemList = item.tokenize( ':' )
            echo "sale itemList ${itemList[1]}"
            return "${itemList[1]}"
        }
    }
    return ""
}
//return this
