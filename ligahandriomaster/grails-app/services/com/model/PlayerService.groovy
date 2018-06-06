package com.model

import grails.gorm.services.Service
import grails.transaction.Transactional
import grails.transaction.NotTransactional
import grails.validation.ValidationException

@Service(Player)
class PlayerService {	
	
	static scope = "prototype"

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

	@Transactional
    Player save(Player player, Long teamId){
		def team = Team.get(teamId)
		if(player.id == null)
			team.addToPlayers(player)

		if(!team.validate())
			throw new ValidationException("Player is not valid", team.errors)
		
    	team.save(flush:true)
		
		return player
    }
	
}