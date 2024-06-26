package com.club.eliteclub.service;

import com.club.eliteclub.dao.EliteClubRepository;
import com.club.eliteclub.entity.EliteClub;
import com.club.eliteclub.model.ClubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EliteClubService {

    private static final Logger LOG = LoggerFactory.getLogger(EliteClubService.class);
    private EliteClubRepository eliteClubRepository;

    @Autowired
    public void setEliteClubRepository(EliteClubRepository eliteClubRepository) {
        this.eliteClubRepository = eliteClubRepository;
    }

    public List<ClubDTO> getAll() {
        return eliteClubRepository.findAll().stream().map(c -> new ClubDTO(c.getClubName())).collect(Collectors.toList());
    }


    public ClubDTO getByID(long clubId) {
        return new ClubDTO(eliteClubRepository.getOne(clubId).getClubName());
    }


    public void deleteClub(long clubId) {
        eliteClubRepository.deleteById(clubId);
    }



    public List<ClubDTO> searchClub(String searchTerm) {
        LOG.info("Searching term {}", searchTerm);
        List<ClubDTO> result = eliteClubRepository.findClubs(buildLikePattern(searchTerm)).stream().map(c -> new ClubDTO(c.getClubName())).collect(Collectors.toList());
        LOG.info("Search Result: {} ", result);
        return result;
    }
    private String buildLikePattern(String searchTerm) {
        return searchTerm.toLowerCase() + "%";
    }


        public void addClub(String... clubNames) {
        for (String clubName : clubNames) {
            EliteClub eliteClub = new EliteClub();
            eliteClub.setClubName(clubName);
            eliteClubRepository.save(eliteClub);
        }
    }

    public ClubDTO updateClub(long clubId, ClubDTO updatedClub) {
        EliteClub eliteClub = new EliteClub();
        eliteClub.setId(clubId);
        eliteClub.setClubName(updatedClub.getClubName());
        final EliteClub saved = eliteClubRepository.save(eliteClub);
        return new ClubDTO(saved.getClubName());
    }
    }