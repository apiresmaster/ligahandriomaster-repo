package com.model

import java.util.*
import grails.rest.*

@Resource(formats=['json'])
class Player {
    String name
    String nickname
    Date birthDate
    String cpf

    //static belongsTo = [team: Team]

    static constraints = {
        name maxSize: 128, unique: true
        nickname maxSize: 80
    }
}
