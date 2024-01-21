package tn.iit.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tn.iit.core.ServerResponse;
import tn.iit.dto.mapper.match.ScorerAwayTeamMapper;
import tn.iit.dto.mapper.match.ScorerHomeTeamMapper;
import tn.iit.dto.mapper.match.SubstitutionAwayTeamMapper;
import tn.iit.dto.mapper.match.SubstitutionHomeTeamMapper;
import tn.iit.dto.request.match.*;
import tn.iit.dto.request.team.PlayerRequest;
import tn.iit.dto.response.match.*;
import tn.iit.dto.response.team.PlayerResponse;
import tn.iit.dto.response.team.TeamResponse;
import tn.iit.entity.DetailsMatch;
import tn.iit.entity.Match;
import tn.iit.feign.FeignTeam;
import tn.iit.repository.DetailsMatchRepository;
import tn.iit.repository.MatchRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DetailsMatchService {

    private DetailsMatchRepository mDetailsMatchRepository;
    private MatchRepository mMatchRepository;
    private ScorerHomeTeamMapper mScorerHomeTeamMapper;
    private ScorerAwayTeamMapper mScorerAwayTeamMapper;
    private SubstitutionHomeTeamMapper mSubstitutionHomeTeamMapper;
    private SubstitutionAwayTeamMapper mSubstitutionAwayTeamMapper;
    private FeignTeam feignTeam;

    public ServerResponse<List<ScorerHomeTeamResponse>> getAllScorerHomeTeamOfMatch(Integer matchId){
        List<ScorerHomeTeamResponse> scorerHomeTeamResponse = mDetailsMatchRepository.findByMatch_MatchId(matchId)
                .stream()
                .map(mScorerHomeTeamMapper::toDTO)
                .filter(scorerResponse -> scorerResponse.getScorer() != 0 && scorerResponse.getHomeTeam() != 0)
                .collect(Collectors.toList());


        ServerResponse<List<ScorerHomeTeamResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), scorerHomeTeamResponse);
    }

    public ServerResponse<List<ScorerAwayTeamResponse>> getAllScorerAwayTeamOfMatch(Integer matchId){

        List<ScorerAwayTeamResponse> scorerAwayTeamResponse = mDetailsMatchRepository.findByMatch_MatchId(matchId)
                .stream()
                .map(mScorerAwayTeamMapper::toDTO)
                .filter(scorerResponse -> scorerResponse.getScorer() != 0 && scorerResponse.getAwayTeam() != 0)
                .collect(Collectors.toList());
        ServerResponse<List<ScorerAwayTeamResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), scorerAwayTeamResponse);
    }

    public ServerResponse<List<SubstitutionAwayTeamResponse>> getAllSubstitutionAwayTeamOfMatch(Integer matchId){

        List<SubstitutionAwayTeamResponse> substitutionAwayTeamResponse = mDetailsMatchRepository.findByMatch_MatchId(matchId)
                .stream()
                .map(mSubstitutionAwayTeamMapper::toDTO)
                .filter(scorerResponse -> scorerResponse.getHolderPlayerId() != 0 && scorerResponse.getSubstitutionPlayerId() != 0 && scorerResponse.getAwayTeam() != 0)
                .collect(Collectors.toList());
        ServerResponse<List<SubstitutionAwayTeamResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), substitutionAwayTeamResponse);
    }

    public ServerResponse<List<SubstitutionHomeTeamResponse>> getAllSubstitutionHomeTeamOfMatch(Integer matchId){

        List<SubstitutionHomeTeamResponse> substitutionHomeTeamResponse = mDetailsMatchRepository.findByMatch_MatchId(matchId)
                .stream()
                .map(mSubstitutionHomeTeamMapper::toDTO)
                .filter(scorerResponse -> scorerResponse.getHolderPlayerId() != 0 && scorerResponse.getSubstitutionPlayerId() != 0 && scorerResponse.getHomeTeam() != 0)
                .collect(Collectors.toList());
        ServerResponse<List<SubstitutionHomeTeamResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), substitutionHomeTeamResponse);
    }

    public ServerResponse<ScorerHomeTeamResponse> createScorerHomeTeeam(ScorerHomeTeamRequest scorerHomeTeamRequest) {
        DetailsMatch mDetailsMatch = mScorerHomeTeamMapper.fromDTO(scorerHomeTeamRequest);
        ServerResponse<TeamResponse> teamLocalResponse = feignTeam.getByIdTeam(scorerHomeTeamRequest.getHomeTeam());
        ServerResponse<PlayerResponse> playerLocalResponse = feignTeam.getByIdPlayer(scorerHomeTeamRequest.getScorer());
        Optional<Match> mMatch = mMatchRepository.findById(scorerHomeTeamRequest.getMatch().getMatchId());
        ServerResponse<ScorerHomeTeamResponse> apiResponse = new ServerResponse<>();
        if (teamLocalResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Team Local not exist");
        }
        else if(mMatch.isEmpty()) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Match not exist");
        }
        else {
            mDetailsMatch.setAwayTeam(0);
            mDetailsMatch.setHolderPlayerId(0);
            mDetailsMatch.setSubstitutionPlayerId(0);
            playerLocalResponse.getData().setNumberOfGoals(playerLocalResponse.getData().getNumberOfGoals() + 1);
            feignTeam.updatePlayer(playerLocalResponse.getData(), playerLocalResponse.getData().getIdPlayer());
            DetailsMatch mDetailsMatchSaved = mDetailsMatchRepository.save(mDetailsMatch);
            Match m = mMatch.get();
            List<DetailsMatch> mDetailsMatchScorerHomeList = mDetailsMatchRepository.findByMatch_MatchIdAndHomeTeamAndHolderPlayerIdAndSubstitutionPlayerId(mDetailsMatch.getMatch().getMatchId(), m.getHomeTeamId(), 0,0);
            List<DetailsMatch> mDetailsMatchScorerAwayList = mDetailsMatchRepository.findByMatch_MatchIdAndAwayTeamAndHolderPlayerIdAndSubstitutionPlayerId(mDetailsMatch.getMatch().getMatchId(), m.getAwayTeamId(), 0,0);
            m.setResult(mDetailsMatchScorerHomeList.size() + "-" + mDetailsMatchScorerAwayList.size());
            mMatchRepository.save(m);
            return apiResponse.success(HttpStatus.CREATED.value(), mScorerHomeTeamMapper.toDTO(mDetailsMatchSaved));

        }
    }

    public ServerResponse<ScorerAwayTeamResponse> createScorerAwayTeeam(ScorerAwayTeamRequest scorerAwayTeamRequest) {
        DetailsMatch mDetailsMatch = mScorerAwayTeamMapper.fromDTO(scorerAwayTeamRequest);
        ServerResponse<TeamResponse> teamAwayResponse = feignTeam.getByIdTeam(scorerAwayTeamRequest.getAwayTeam());
        ServerResponse<PlayerResponse> playerAwayResponse = feignTeam.getByIdPlayer(scorerAwayTeamRequest.getScorer());
        Optional<Match> mMatch = mMatchRepository.findById(scorerAwayTeamRequest.getMatch().getMatchId());
        ServerResponse<ScorerAwayTeamResponse> apiResponse = new ServerResponse<>();
        if (teamAwayResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Team Away not exist");
        }
        else if(mMatch.isEmpty()) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Match not exist");
        }
        else {
            mDetailsMatch.setHomeTeam(0);
            mDetailsMatch.setHolderPlayerId(0);
            mDetailsMatch.setSubstitutionPlayerId(0);
            playerAwayResponse.getData().setNumberOfGoals(playerAwayResponse.getData().getNumberOfGoals() + 1);
            feignTeam.updatePlayer(playerAwayResponse.getData(), playerAwayResponse.getData().getIdPlayer());
            DetailsMatch mDetailsMatchSaved = mDetailsMatchRepository.save(mDetailsMatch);
            Match m = mMatch.get();
            List<DetailsMatch> mDetailsMatchScorerHomeList = mDetailsMatchRepository.findByMatch_MatchIdAndHomeTeamAndHolderPlayerIdAndSubstitutionPlayerId(mDetailsMatch.getMatch().getMatchId(), m.getHomeTeamId(), 0,0);
            List<DetailsMatch> mDetailsMatchScorerAwayList = mDetailsMatchRepository.findByMatch_MatchIdAndAwayTeamAndHolderPlayerIdAndSubstitutionPlayerId(mDetailsMatch.getMatch().getMatchId(), m.getAwayTeamId(), 0,0);
            m.setResult(mDetailsMatchScorerHomeList.size() + "-" + mDetailsMatchScorerAwayList.size());
            mMatchRepository.save(m);
            return apiResponse.success(HttpStatus.CREATED.value(), mScorerAwayTeamMapper.toDTO(mDetailsMatchSaved));

        }
    }

    public ServerResponse<SubstitutionHomeTeamResponse> createSubstitutionHomeTeeam(SubstitutionHomeTeamRequest substitutionHomeTeamRequest) {
        DetailsMatch mDetailsMatch = mSubstitutionHomeTeamMapper.fromDTO(substitutionHomeTeamRequest);
        ServerResponse<TeamResponse> teamLocalResponse = feignTeam.getByIdTeam(substitutionHomeTeamRequest.getHomeTeam());
        ServerResponse<PlayerResponse> holderPlayerLocalResponse = feignTeam.getByIdPlayer(substitutionHomeTeamRequest.getHolderPlayerId());
        ServerResponse<PlayerResponse> substiutionPlayerLocalResponse = feignTeam.getByIdPlayer(substitutionHomeTeamRequest.getSubstitutionPlayerId());
        Optional<Match> mMatch = mMatchRepository.findById(substitutionHomeTeamRequest.getMatch().getMatchId());
        ServerResponse<SubstitutionHomeTeamResponse> apiResponse = new ServerResponse<>();
        if (teamLocalResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Team Local not exist");
        }
        else if (holderPlayerLocalResponse.getData() == null){
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Holder Player Team Local not exist");
        }
        else if (substiutionPlayerLocalResponse.getData() == null){
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Substitution Player Team Local not exist");
        }
        else if(mMatch.isEmpty()) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Match not exist");
        }
        else {
            mDetailsMatch.setAwayTeam(0);
            mDetailsMatch.setScorer(0);
            DetailsMatch mDetailsMatchSaved = mDetailsMatchRepository.save(mDetailsMatch);
            return apiResponse.success(HttpStatus.CREATED.value(), mSubstitutionHomeTeamMapper.toDTO(mDetailsMatchSaved));

        }
    }

    public ServerResponse<SubstitutionAwayTeamResponse> createSubstitutionAwayTeeam(SubstitutionAwayTeamRequest substitutionAwayTeamRequest) {
        DetailsMatch mDetailsMatch = mSubstitutionAwayTeamMapper.fromDTO(substitutionAwayTeamRequest);
        ServerResponse<TeamResponse> teamAwayResponse = feignTeam.getByIdTeam(substitutionAwayTeamRequest.getAwayTeam());
        ServerResponse<PlayerResponse> holderPlayerAwayResponse = feignTeam.getByIdPlayer(substitutionAwayTeamRequest.getHolderPlayerId());
        ServerResponse<PlayerResponse> substiutionPlayerAwayResponse = feignTeam.getByIdPlayer(substitutionAwayTeamRequest.getSubstitutionPlayerId());
        Optional<Match> mMatch = mMatchRepository.findById(substitutionAwayTeamRequest.getMatch().getMatchId());
        ServerResponse<SubstitutionAwayTeamResponse> apiResponse = new ServerResponse<>();
        if (teamAwayResponse.getData() == null) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Team Away not exist");
        }
        else if (holderPlayerAwayResponse.getData() == null){
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Holder Player Team Away not exist");
        }
        else if (substiutionPlayerAwayResponse.getData() == null){
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Substitution Player Team Away not exist");
        }
        else if(mMatch.isEmpty()) {
            return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Id Match not exist");
        }
        else {
            mDetailsMatch.setHomeTeam(0);
            mDetailsMatch.setScorer(0);
            DetailsMatch mDetailsMatchSaved = mDetailsMatchRepository.save(mDetailsMatch);
            return apiResponse.success(HttpStatus.CREATED.value(), mSubstitutionAwayTeamMapper.toDTO(mDetailsMatchSaved));

        }
    }
}
