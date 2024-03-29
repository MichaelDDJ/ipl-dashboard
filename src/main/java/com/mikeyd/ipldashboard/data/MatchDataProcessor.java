package com.mikeyd.ipldashboard.data;

import com.mikeyd.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;


//Data Processor from Spring batch to take data from csv file and create Match instances
//It takes MatchInput and creates Match
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

        private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

        @Override
        public Match process(final MatchInput matchInput) throws Exception{

            //new match instance
            Match match = new Match();

            //id created as String so parse into long for Match model
            match.setId(Long.parseLong(matchInput.getId()));
            match.setCity(matchInput.getCity());
            match.setDate(LocalDate.parse(matchInput.getDate()));
            match.setPlayerOfMatch(matchInput.getPlayer_of_match());
            match.setVenue(matchInput.getVenue());

            //Set team 1 and 2 depending on innings order
            String firstInningsTeam, secondInningsTeam;

            if("bat".equals(matchInput.getToss_decision())) {
                firstInningsTeam = matchInput.getToss_winner();
                secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                        ? matchInput.getTeam2() : matchInput.getTeam1();
            } else {
                secondInningsTeam = matchInput.getToss_winner();
                firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                        ? matchInput.getTeam2() : matchInput.getTeam1();
            }

            match.setTeam1(firstInningsTeam);
            match.setTeam2(secondInningsTeam);

            match.setTossWinner(matchInput.getToss_winner());
            match.setTossDecision(matchInput.getToss_decision());

            match.setResult(matchInput.getResult());
            match.setResultMargin(matchInput.getResult_margin());
            match.setUmpire1(matchInput.getUmpire1());
            match.setUmpire2(matchInput.getUmpire2());

            return match;
        }

}

