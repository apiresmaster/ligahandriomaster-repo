package ligahandriomaster

import com.model.Team
import com.model.Player
import com.model.ETeamCategory
import com.model.ETeamGenre
import com.model.TeamMatch
import com.model.Match

class BootStrap {

    def init = { servletContext ->
		def t1 = new Team(name:"time1", nickname:"time1", genre: ETeamGenre.MALE
            ,category: ETeamCategory.TRINTAECINCOMAIS)
			 .addToPlayers(new Player(name: "Jogador 1 Time 1"
                 ,nickname: "Jogador 1 Time 1"
                 ,cpf: "1111111111",
			 	,birthDate:Date.parse("yyyy-MM-dd", "1980-04-03")))
			.save(flush:true)
		
		// def t2 = new Team(name:"Time 2 Teste", nickname:"Time 2 Teste", genre: ETeamGenre.MALE
  //           ,category: ETeamCategory.TRINTAECINCOMAIS)
		// 	.addToPlayers(new Player(name: "Jogador 1 Time 2"
  //               ,nickname: "Jogador 1 Time 2"
  //               ,cpf: "111111111111",
		// 		,birthDate:Date.parse("yyyy-MM-dd", "1981-04-03")))
		// 	.save(flush:true)
		
		def rodada1 = new Match(roundDate:Date.parse("yyyy-MM-dd", "2018-04-03")
							   ,description:"Rodada CEFED")
						.save(flush:true)
    }
    def destroy = {
    }
}
