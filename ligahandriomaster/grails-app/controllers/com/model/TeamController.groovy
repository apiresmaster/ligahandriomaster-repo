package com.model


import grails.rest.*
import grails.converters.*

class TeamController extends RestfulController {
    static responseFormats = ['json']
    TeamController() {
        super(Team)
    }
}
