package com.model

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlayerController {

    PlayerService playerService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    //def index(Integer max) {
    //    params.max = Math.min(max ?: 10, 100)
    //    respond playerService.list(params), model:[playerCount: playerService.count()]
    //}

	def index(){
		def teamId = params.teamId
		respond Player.where{
			team.id == teamId
		}.list()
	}
    def show(Long id) {
        respond playerService.get(id)
    }

    def save(Player player) {
        if (player == null) {
            render status: NOT_FOUND
            return
        }
		
        try {
			def team = Team.get(params.teamId)
			player.team = team
			team.addToPlayers(player)
            playerService.save(player)
			
        } catch (ValidationException e) {
            respond player.errors, view:'create'
            return
        }

        respond player, [status: CREATED, view:"show"]
    }

    def update(Player player) {
        if (player == null) {
            render status: NOT_FOUND
            return
        }

        try {
            playerService.save(player)
        } catch (ValidationException e) {
            respond player.errors, view:'edit'
            return
        }

        respond player, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        playerService.delete(id)

        render status: NO_CONTENT
    }
}
