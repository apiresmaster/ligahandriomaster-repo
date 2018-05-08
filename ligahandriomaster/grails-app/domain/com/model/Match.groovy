package com.model

import java.time.LocalDate
import grails.rest.*

@Resource(readOnly = false, formats = ['json'])
class Match {
    LocalDate roundDate
    String description

    static hasMany = [teamMatch: TeamMatch]

    static constraints = {
    }
}