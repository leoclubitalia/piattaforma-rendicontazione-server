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
    public Club updateCurrentMembersQuantity(String email, int newQuantity) {
        Club club = clubRepository.findClubByEmail(email);
        club.setCurrentMembers(newQuantity);
        return club;
    }

    @Transactional(readOnly = false)
    public Club updateAspirantMembersQuantity(String email, int newQuantity) {
        Club club = clubRepository.findClubByEmail(email);
        club.setAspirantMembers(newQuantity);
        return club;
    }

    @Transactional(readOnly = true)
    public Club getClubByEmail(String email) {
        return clubRepository.findClubByEmail(email);
    }


}
