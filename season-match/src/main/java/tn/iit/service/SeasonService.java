package tn.iit.service;

import tn.iit.core.ServerResponse;
import tn.iit.dto.mapper.SeasonMapper;
import tn.iit.dto.request.SeasonRequest;
import tn.iit.dto.response.SeasonResponse;
import tn.iit.entity.Season;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tn.iit.repository.SeasonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SeasonService {
    private SeasonRepository mSeasonRepository;
    private SeasonMapper mSeasonMapper;
    public ServerResponse<List<SeasonResponse>> getAll() {
        List<SeasonResponse> seasonResponses = mSeasonRepository.findAll()
                .stream()
                .map(mSeasonMapper::toDTO)
                .collect(Collectors.toList());
        ServerResponse<List<SeasonResponse>> apiResponse = new ServerResponse<>();
        return apiResponse.success(HttpStatus.OK.value(), seasonResponses);
    }
    public ServerResponse<SeasonResponse> getById(String seasonId) {
        Optional<Season> season= mSeasonRepository.findById(seasonId);
        ServerResponse<SeasonResponse> apiResponse = new ServerResponse<>();
        if (season.isPresent()){
            return apiResponse.success(HttpStatus.OK.value(), mSeasonMapper.toDTO(season.get()));
        }
        else{
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"IDENTIFIANT INVALIDE");
        }
    }

    public ServerResponse<SeasonResponse> create(SeasonRequest seasonRequest) {
        Season mSeason = mSeasonMapper.fromDTO(seasonRequest);
        Optional <Season> season= mSeasonRepository.findById(mSeason.getSeasonId());
        ServerResponse<SeasonResponse> apiResponse = new ServerResponse<>();
        if (season.isPresent()){
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"exist");
        }
        else if (mSeason.getStartDate().after(mSeason.getEndDate())) {
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "StartDate should be before EndDate");
        }
        else{
            mSeason.setState("Not Started");
            Season seasonSaved = mSeasonRepository.save(mSeason);
            return apiResponse.success(HttpStatus.CREATED.value(), mSeasonMapper.toDTO(seasonSaved));
        }
    }
    public ServerResponse<SeasonResponse> update(Season season, String id) {
        Optional <Season> seasonToUpdate = mSeasonRepository.findById(id);
        ServerResponse<SeasonResponse> apiResponse = new ServerResponse<>();
        if (seasonToUpdate.isPresent()){
            if (season.getStartDate().after(season.getEndDate())) {
                return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "StartDate should be before EndDate");
            }
            Season getSeasonToUpdate = seasonToUpdate.get();
            getSeasonToUpdate.setEndDate(season.getEndDate());
            getSeasonToUpdate.setStartDate(season.getStartDate());
            Season seasonUpdated = mSeasonRepository.save(getSeasonToUpdate);
            return apiResponse.success(HttpStatus.OK.value(), mSeasonMapper.toDTO(seasonUpdated));
        }
        else{
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"IDENTIFIANT INVALIDE");
        }
    }
    public ServerResponse<SeasonResponse> delete(String id) {
        Optional <Season> seasonToDelete = mSeasonRepository.findById(id);
        ServerResponse<SeasonResponse> apiResponse = new ServerResponse<>();
        if (seasonToDelete.isPresent()){
            mSeasonRepository.deleteById(id);
            return apiResponse.success(HttpStatus.OK.value(), null);
        }
        else{
            return apiResponse.echec(HttpStatus.BAD_REQUEST.value(),"IDENTIFIANT INVALIDE");
        }
    }




}
