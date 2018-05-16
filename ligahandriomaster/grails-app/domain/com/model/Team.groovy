package com.model

import grails.rest.*
import static java.util.Calendar.*
	
@Resource()
class Team {
    private def dtAtual = new Date()
    String name
    String nickname
    List<Player> players

    static belongsTo = [genre: ETeamGenre, category: ETeamCategory]
    static hasMany = [players: Player]
    static constraints = {
        name blank: false , maxSize: 128 , unique: true
        players maxSize: 30, validator: {pl, obj, errors ->
            pl.find{ p ->
                if((obj.dtAtual[YEAR] - p.birthDate[YEAR]) <= obj.category.limitAge)
                    errors.rejectValue('players', 'noMatch')
            }
        }
    }
}
