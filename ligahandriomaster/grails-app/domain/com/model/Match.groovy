package com.model

import java.util.*
import grails.rest.*

@Resource(readOnly = false, formats = ['json'])
class Match {
    Date roundDate
    String description

    static hasMany = [teamMatch: TeamMatch]

    static constraints = {
    }
}