package com.model


import grails.rest.*

@Resource(readOnly = false, formats = ['json'])
class CountVotes {
    /**
     * Jogador votado
     */
    final Player player
    /**
     * Total de votos
     */
    Integer quantity

    CountVotes(player){
        this.player = player
        this.quantity = 0
    }

    /**
     * Computa voto para atleta
     */
    void vote(){
        this.quantity++
    }

    static constraints = {

    }
}