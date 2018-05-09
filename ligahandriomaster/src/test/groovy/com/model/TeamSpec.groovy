package com.model

import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification

class TeamSpec extends Specification implements DomainUnitTest<Team> {

    def setup() {
    }

    def cleanup() {
    }

    @Shared
    def time35Mais = new Team(name: "Time 35 Mais"
            ,nickname: "Time 35 Mais"
            ,genre: ETeamGenre.MALE
            ,category: ETeamCategory.TRINTAECINCOMAIS
    )

    @Shared
    def time42Mais = new Team(name: "Time 42 Mais"
            ,nickname: "Time 42 Mais"
            ,genre: ETeamGenre.MALE
            ,category: ETeamCategory.QUARENTAEDOIS
    )

    void "Não permitir inclusão de mais de trinta jogadores"() {

        def dtNascimento = Date.parse("yyyy-MM-dd", "1981-04-03");

        for (int i = 0; i < 32; i++) {

            time35Mais.addToPlayers(new Player(name: "Jogador1"
                ,nickname: "Jogador1"
                ,cpf: "11111111111"
                ,birthDate: dtNascimento
            ))
        }

        expect:"Não persistir os dados, pois foram inclusos mais de trinta atletas"
            time35Mais.save() == null
    }

    void "Validar idade minima dos atletas da categoria 35+"(){
        def dtNascimento = Date.parse("yyyy-MM-dd", "2014-04-03");
        time35Mais.addToPlayers(new Player(name: "Jogador2"
                ,nickname: "Jogador2"
                ,cpf: "11111111111"
                ,birthDate: dtNascimento
        ))

        def dtNascimento2 = Date.parse("yyyy-MM-dd", "2014-04-03");
        time35Mais.addToPlayers(new Player(name: "Jogador3"
                ,nickname: "Jogador3"
                ,cpf: "11111111111"
                ,birthDate: dtNascimento2
        ))
        expect:"Não persistir, existe atleta com idade inferior a permitida"
        time35Mais.save() == null
    }

    void "Validar idade minima dos atletas da categoria 42+"(){
        def dtNascimento = Date.parse("yyyy-MM-dd", "2014-04-03");
        time42Mais.addToPlayers(new Player(name: "Jogador4"
                ,nickname: "Jogador4"
                ,cpf: "11111111111"
                ,birthDate: dtNascimento
        ))

        def dtNascimento2 = Date.parse("yyyy-MM-dd", "2014-04-03");
        time42Mais.addToPlayers(new Player(name: "Jogador5"
                ,nickname: "Jogador5"
                ,cpf: "11111111111"
                ,birthDate: dtNascimento2
        ))
        expect:"Não persistir, existe atleta com idade inferior a permitida"
        time42Mais.save() == null
    }
}
