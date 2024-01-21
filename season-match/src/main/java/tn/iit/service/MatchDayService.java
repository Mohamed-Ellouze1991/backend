package tn.iit.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tn.iit.core.ServerResponse;
import tn.iit.dto.mapper.MatchDayMapper;
import tn.iit.dto.mapper.SeasonMapper;
import tn.iit.dto.request.MatchDayRequest;
import tn.iit.dto.request.SeasonRequest;

import tn.iit.dto.response.MatchDayResponse;
import tn.iit.dto.response.SeasonResponse;
import tn.iit.entity.MatchDay;
import tn.iit.entity.Season;
import tn.iit.repository.MatchDayRepository;
import tn.iit.repository.SeasonRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MatchDayService {
    private MatchDayRepository mMatchDayRepositoy;
    private SeasonRepository mSeasonRepository;
    private MatchDayMapper mMatchDayMapper;
    private SeasonService mSeasonService;
    public ServerResponse<List<MatchDayResponse>> getAll() {
        List<MatchDayResponse> matchDayResponses = mMatchDayRepositoy.findAll()
                .stream()
                .map(mMatchDayMapper::toDTO)
                .collect(Collectors.toList());
        ServerResponse<List<MatchDayResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), matchDayResponses);
    }
    public ServerResponse<MatchDayResponse> getById(Integer matchDayId) {
        Optional<MatchDay> matchDay= mMatchDayRepositoy.findById(matchDayId);
        ServerResponse<MatchDayResponse> apiResponse = new ServerResponse<>();
        if (matchDay.isPresent()){
            return apiResponse.success(HttpStatus.OK.value(), mMatchDayMapper.toDTO(matchDay.get()));
        }
        else{
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"IDENTIFIANT INVALIDE");
        }
    }

    public ServerResponse<MatchDayResponse> create(MatchDayRequest matchDayRequest) {
        MatchDay mMatchDay = mMatchDayMapper.fromDTO(matchDayRequest);
        ServerResponse<MatchDayResponse> apiResponse = new ServerResponse<>();

        // Check if the match date is within the season's start and end dates
        Season season = mMatchDay.getSeason();


        if (season != null && isDateWithinSeason(mMatchDay.getStartDate(), season) && isDateWithinSeason(mMatchDay.getEndDate(), season)) {
            if (mMatchDay.getStartDate().after(mMatchDay.getEndDate())) {
                return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "StartDate should be before EndDate");}
            else {
                MatchDay matchDaySaved = mMatchDayRepositoy.save(mMatchDay);
                season.setState("Started");
                mSeasonRepository.save(season);
                return apiResponse.success(HttpStatus.CREATED.value(), mMatchDayMapper.toDTO(matchDaySaved));
            }

        } else {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Invalid match date for the season");
        }
    }
    public ServerResponse<MatchDayResponse> update(MatchDay matchDay, Integer id) {
        Optional<MatchDay> matchDayToUpdate = mMatchDayRepositoy.findById(id);
        ServerResponse<MatchDayResponse> apiResponse = new ServerResponse<>();

        if (matchDayToUpdate.isPresent()) {
            MatchDay existingMatchDay = matchDayToUpdate.get();
            Season season = existingMatchDay.getSeason();
            if (season != null && isDateWithinSeason(matchDay.getStartDate(), season)&& isDateWithinSeason(matchDay.getEndDate(), season)) {
                if (matchDay.getStartDate().after(matchDay.getEndDate())) {
                    return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "StartDate should be before EndDate");}
                existingMatchDay.setName(matchDay.getName());
                existingMatchDay.setStartDate(matchDay.getStartDate());
                existingMatchDay.setEndDate(matchDay.getEndDate());
                MatchDay matchDayUpdated = mMatchDayRepositoy.save(existingMatchDay);
                return apiResponse.success(HttpStatus.OK.value(), mMatchDayMapper.toDTO(matchDayUpdated));
            } else {
                return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Invalid match date for the season");
            }
        } else {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Invalid MatchDay ID");
        }
    }

    public ServerResponse<MatchDayResponse> delete(Integer id) {
        Optional <MatchDay> matchDayToDelete = mMatchDayRepositoy.findById(id);
        ServerResponse<MatchDayResponse> apiResponse = new ServerResponse<>();
        if (matchDayToDelete.isPresent()){
            mMatchDayRepositoy.deleteById(id);
            return apiResponse.success(HttpStatus.OK.value(), null);
        }
        else{
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"IDENTIFIANT INVALIDE");
        }
    }

    private boolean isDateWithinSeason(Date date, Season season) {
        Date startDate = season.getStartDate();
        Date endDate = season.getEndDate();
        return date.after(startDate) && date.before(endDate);
    }
    public ServerResponse<List<MatchDayResponse>> getAllBySeason(String seasonId) {
        List<MatchDayResponse> matchDayResponses = mMatchDayRepositoy.findBySeason_SeasonId(seasonId)
                .stream()
                .map(mMatchDayMapper::toDTO)
                .collect(Collectors.toList());
        ServerResponse<List<MatchDayResponse>> apiResponse = new ServerResponse<>();
        if(matchDayResponses.isEmpty()){
            ServerResponse<SeasonResponse> season = mSeasonService.getById(seasonId);
            if(season.getData()==null){
                return apiResponse.echec(HttpStatus.NOT_FOUND.value(),"season n'existe pas");
            }else{
                return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"season n'a pas de MatchDays");
            }
        }
        else{
            return apiResponse.success(HttpStatus.OK.value(), matchDayResponses);
        }

    }

}
