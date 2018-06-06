package com.model

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TeamController {

    TeamService teamService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond teamService.list(params), model:[teamCount: teamService.count()]
    }

    def show(Long id) {
        respond teamService.get(id)
    }

    def save(Team team) {
        if (team == null) {
            render status: NOT_FOUND
            return
        }

        try {
            teamService.save(team)
        } catch (ValidationException e) {
            respond team.errors, view:'create'
            return
        }

        respond team, [status: CREATED, view:"show"]
    }

    def update(Team team) {
        if (team == null) {
            render status: NOT_FOUND
            return
        }

        try {
            teamService.save(team)
        } catch (ValidationException e) {
            respond team.errors, view:'edit'
            return
        }

        respond team, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        teamService.delete(id)

        render status: NO_CONTENT
    }
	
}
