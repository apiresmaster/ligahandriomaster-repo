package com.model

import grails.gorm.services.Service
import grails.transaction.Transactional
import grails.transaction.NotTransactional
import grails.validation.ValidationException

@Service(Player)
@Transactional
class PlayerService {

    @NotTransactional
    Player get(Serializable id){
    	Player.get(id)
    }

    @NotTransactional
    List<Player> list(Map args){
    	Player.where{
    		team.id == args.teamId
    	}.list(args)
    }

    @NotTransactional
    Long count(){
    	Player.count()
    }

    @NotTransactional
    void delete(Serializable id){
    	Player.get(id).delete()
    }

    Player save(Player player, int teamId){    	
		def team = Team.get(teamId)
		player.team = team
		team.addToPlayers(player)
		if(!player.validate())
			throw new ValidationException("Player is not valid", player.errors)

    	player.save(flush:true)
    }

}