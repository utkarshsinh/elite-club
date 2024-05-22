package com.club.eliteclub.controller;

import com.club.eliteclub.model.ClubDTO;
import com.club.eliteclub.service.EliteClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EliteClubController {


    private EliteClubService eliteClubService;

    @Autowired
    public void setEliteClubService(EliteClubService eliteClubService) {
        this.eliteClubService = eliteClubService;
    }

    @GetMapping(path = "/clubs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubDTO> clubs() {
        return eliteClubService.getAll();
    }
    @GetMapping(path = "/club/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubDTO> searchClub(@RequestParam String clubName) {
        return eliteClubService.searchClub(clubName);
    }

    @DeleteMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteClub(@PathVariable long id) {
        eliteClubService.deleteClub(id);
    }

    @GetMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClubDTO clubWithId(@PathVariable long id) {
        return eliteClubService.getByID(id);
    }

    @PostMapping(path = "/club", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewClub(@RequestBody ClubDTO clubDTO) {
        eliteClubService.addClub(clubDTO.getClubName());
        return ResponseEntity.status(HttpStatus.CREATED).body(clubDTO);
    }

    @PutMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClubDTO updateClub(@PathVariable long id, @RequestBody ClubDTO clubDTO) {
        return eliteClubService.updateClub(id, clubDTO);
    }
    }