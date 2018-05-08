package com.model


import grails.rest.*

@Resource(readOnly = false, formats = ['json'])
class TeamMatch {
    Team teamOne
    Team teamSecond


    def finishVotes(){
        return this.votes.findAll {
            it.quantity == this.votes.max{it.quantity}.quantity
        }
    }
	
	static hasMany = [votes: CountVotes]
}