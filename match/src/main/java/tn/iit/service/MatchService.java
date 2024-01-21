package tn.iit.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tn.iit.core.ServerResponse;
import tn.iit.dto.mapper.match.MatchMapper;
import tn.iit.dto.request.match.MatchRequest;
import tn.iit.dto.response.match.MatchResponse;
import tn.iit.dto.response.season.MatchDayResponse;
import tn.iit.dto.response.team.TeamResponse;
import tn.iit.entity.DetailsMatch;
import tn.iit.entity.Match;
import tn.iit.feign.FeignMatchDay;
import tn.iit.feign.FeignTeam;
import tn.iit.repository.DetailsMatchRepository;
import tn.iit.repository.MatchRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class MatchService {

    private MatchRepository matchRepository;
    private DetailsMatchRepository mDetailsMatchRepository;
    private MatchMapper matchMapper;
    private FeignTeam feignTeam;
    private FeignMatchDay feignMatchDay;

    public ServerResponse <List<MatchResponse>> getAll(){
        List<MatchResponse> matchResponses = matchRepository.findAll().stream().map(matchMapper::toDTO)
                .collect(Collectors.toList());
        ServerResponse<List<MatchResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), matchResponses);
    }
    public ServerResponse <List<MatchResponse>> getAllByMatchDayId(Integer matchDayId){
        List<MatchResponse> matchResponses = matchRepository.findByMatchDayId(matchDayId).stream()
                .map(matchMapper::toDTO).collect(Collectors.toList());
        ServerResponse<List<MatchResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value() , matchResponses);
    }

    public ServerResponse <MatchResponse> getById(Integer id){
        Optional<Match> match = matchRepository.findById(id);
        ServerResponse<MatchResponse> apiResponse =new ServerResponse<>();
        if(match.isPresent()){
            return apiResponse.success(HttpStatus.OK.value(), matchMapper.toDTO(match.get()));
        }
        else {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
        }
    }
    public ServerResponse<MatchResponse> create(MatchRequest matchRequest) {
        Match mMatch = matchMapper.fromDTO(matchRequest);
        ServerResponse<MatchResponse> apiResponse = new ServerResponse<>();
        ServerResponse<TeamResponse> teamLocalResponse = feignTeam.getByIdTeam(matchRequest.getHomeTeamId());
        ServerResponse<TeamResponse> teamAwayResponse = feignTeam.getByIdTeam(matchRequest.getAwayTeamId());
        ServerResponse<MatchDayResponse> matchDayResponse = feignMatchDay.getById(matchRequest.getMatchDayId());
        Match match= matchRepository.findByMatchDayIdAndAwayTeamIdAndHomeTeamId(matchRequest.getMatchDayId(), matchRequest.getAwayTeamId(), matchRequest.getHomeTeamId());
        Match matchGO= matchRepository.findByAwayTeamIdAndHomeTeamId(matchRequest.getAwayTeamId(), matchRequest.getHomeTeamId());
        Match matchReturn= matchRepository.findByAwayTeamIdAndHomeTeamId(matchRequest.getHomeTeamId(),matchRequest.getAwayTeamId());

        if (teamLocalResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Team Local not exist");
        }
        else if (teamAwayResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Team Away not exist");
        }
        else if (matchDayResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id MatchDay not exist");
        }
        else if (match != null) {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Match already exist");
        }
        else if (matchGO != null &&  matchReturn != null) {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "The Two teams already Played");
        }
        else if (!isDateWithinMatchDay(matchRequest.getDate(),matchDayResponse.getData())){
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Invalid match date for the Match Day");
        }
        else {
            mMatch.setState("Not Finished");
            Match matchSaved = matchRepository.save(mMatch);
            return apiResponse.success(HttpStatus.CREATED.value(), matchMapper.toDTO(matchSaved));
        }


    }
    public ServerResponse<MatchResponse> update(Match match, Integer id) {
        Optional<Match> matchToUpdate = matchRepository.findById(id);
        ServerResponse<MatchResponse> apiResponse = new ServerResponse<>();
        if (matchToUpdate.isPresent()) {
            Match existingMatch = matchToUpdate.get();
                existingMatch.setDate(match.getDate());
                existingMatch.setReferee(match.getReferee());
                existingMatch.setStadium(match.getStadium());
                Match matchUpdated = matchRepository.save(existingMatch);
                return apiResponse.success(HttpStatus.OK.value(), matchMapper.toDTO(matchUpdated));
            }
         else {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Invalid Match ID");
        }
    }

    public ServerResponse<MatchResponse> updateFinishMatch(Integer id) {
        Optional<Match> matchToUpdate = matchRepository.findById(id);
        if (matchToUpdate.isPresent()) {
            Match existingMatch = matchToUpdate.get();
            existingMatch.setState("Finished");
            Match matchUpdated = matchRepository.save(existingMatch);
            ServerResponse<TeamResponse> teamLocalResponse = feignTeam.getByIdTeam(existingMatch.getHomeTeamId());
            ServerResponse<TeamResponse> teamAwayResponse = feignTeam.getByIdTeam(existingMatch.getAwayTeamId());
            List<DetailsMatch> mDetailsMatchScorerHomeList = mDetailsMatchRepository.findByMatch_MatchIdAndHomeTeamAndHolderPlayerIdAndSubstitutionPlayerId(existingMatch.getMatchId(), existingMatch.getHomeTeamId(), 0,0);
            List<DetailsMatch> mDetailsMatchScorerAwayList = mDetailsMatchRepository.findByMatch_MatchIdAndAwayTeamAndHolderPlayerIdAndSubstitutionPlayerId(existingMatch.getMatchId(), existingMatch.getAwayTeamId(), 0,0);
            if(mDetailsMatchScorerHomeList.size() > mDetailsMatchScorerAwayList.size()){
                teamLocalResponse.getData().setTeamPoints(teamLocalResponse.getData().getTeamPoints() + 3);
                feignTeam.updateTeam(teamLocalResponse.getData(),teamLocalResponse.getData().getIdTeam());
            }
            else if(mDetailsMatchScorerHomeList.size() < mDetailsMatchScorerAwayList.size()){

                teamAwayResponse.getData().setTeamPoints(teamAwayResponse.getData().getTeamPoints() + 3);
                feignTeam.updateTeam(teamAwayResponse.getData(),teamAwayResponse.getData().getIdTeam());
            }
            else{
                teamLocalResponse.getData().setTeamPoints(teamLocalResponse.getData().getTeamPoints() + 1);
                teamAwayResponse.getData().setTeamPoints(teamLocalResponse.getData().getTeamPoints() + 1);
                feignTeam.updateTeam(teamLocalResponse.getData(),teamLocalResponse.getData().getIdTeam());
                feignTeam.updateTeam(teamAwayResponse.getData(),teamAwayResponse.getData().getIdTeam());
            }
            return new ServerResponse<MatchResponse>().success(HttpStatus.OK.value(), matchMapper.toDTO(matchUpdated));
        }
        else {
            return new ServerResponse<MatchResponse>().echec(HttpStatus.BAD_REQUEST.value(), "Invalid Match ID");
        }
    }

    public ServerResponse<MatchResponse> delete(Integer id) {
        Optional <Match> matchToDelete = matchRepository.findById(id);
        ServerResponse<MatchResponse> apiResponse = new ServerResponse<>();
        if (matchToDelete.isPresent()){
            matchRepository.deleteById(id);
            return apiResponse.success(HttpStatus.OK.value(), null);
        }
        else{
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"IDENTIFIANT INVALIDE");
        }
    }

    private boolean isDateWithinMatchDay(Date date, MatchDayResponse matchDayResponse) {
        Date startDate = matchDayResponse.getStartDate();
        Date endDate = matchDayResponse.getEndDate();
        return date.after(startDate) && date.before(endDate);
    }

}
