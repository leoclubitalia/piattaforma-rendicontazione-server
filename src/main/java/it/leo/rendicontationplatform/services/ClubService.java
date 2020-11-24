package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Club;
import it.leo.rendicontationplatform.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;


    @Transactional(readOnly = false)
    public Club updateCurrentPartnersQuantity(int clubId, int newQuantity) {
        Club club = clubRepository.findClubById(clubId);
        club.setCurrentPartners(newQuantity);
        return club;
    }

    @Transactional(readOnly = false)
    public Club updateAspirantPartnersQuantity(int clubId, int newQuantity) {
        Club club = clubRepository.findClubById(clubId);
        club.setAspirantPartners(newQuantity);
        return club;
    }


}
