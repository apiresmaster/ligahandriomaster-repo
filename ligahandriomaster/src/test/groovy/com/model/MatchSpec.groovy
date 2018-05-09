package com.model

import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification

class MatchSpec extends Specification implements DomainUnitTest<Match> {

    def setup() {
    }

    def cleanup() {
    }

    @Shared
    def timeOne35Mais = new Team(name: "Time 35 Mais"
            ,nickname: "Time 35 Mais"
            ,genre: ETeamGenre.MALE
            ,category: ETeamCategory.TRINTAECINCOMAIS
    )

    @Shared
    def timeSecond35Mais = new Team(name: "Time2 35 Mais "
            ,nickname: "Time2 35 Mais"
            ,genre: ETeamGenre.MALE
            ,category: ETeamCategory.TRINTAECINCOMAIS
    )

    def dtNascimento = Date.parse("yyyy-MM-dd", "1981-04-03");

    void "Criar rodada com equipes 35 mais masculino"() {

        for (int i = 0; i < 10; i++) {
            timeOne35Mais.addToPlayers(new Player(name: "Jogador Time1 ${i}"
                    ,nickname: "Jogador ${i}"
                    ,cpf: "11111111111"
                    ,birthDate: dtNascimento
            ))
        }
        timeOne35Mais.save()

        for (int i = 0; i < 10; i++) {
            timeSecond35Mais.addToPlayers(new Player(name: "Jogador ${i}"
                    ,nickname: "Jogador Time2 ${i}"
                    ,cpf: "11111111111"
                    ,birthDate: dtNascimento
            ))
        }
        timeSecond35Mais.save()

        def dtRodada = Date.parse("yyyy-MM-dd", "2018-05-20")
        def rodadaAbril = new Match(roundDate: dtRodada, description: "Ginásio do CEFED")

        rodadaAbril.teamMatch = [new TeamMatch(teamOne: timeOne35Mais, teamSecond: timeSecond35Mais)]
        rodadaAbril.save() != null
        //votação no jogador 1
        def votoJogador1 = rodadaAbril.teamMatch[0].teamOne.players[0]
        def votacao1 = new CountVotes(votoJogador1)
        votacao1.vote()
        votacao1.vote()
        votacao1.vote()
        rodadaAbril.teamMatch[0].addToVotes(votacao1)

        //votação no jogador 2
        def votoJogador2 = rodadaAbril.teamMatch[0].teamOne.players[1]
        def votacao2 = new CountVotes(votoJogador2)
        votacao2.vote()
        votacao2.vote()
        votacao2.vote()
        //votacao2.vote()
        rodadaAbril.teamMatch[0].addToVotes(votacao2)
        rodadaAbril.save()

        def result = rodadaAbril.teamMatch[0].finishVotes()
        println(result)
        expect:"fix me"

    }
}
