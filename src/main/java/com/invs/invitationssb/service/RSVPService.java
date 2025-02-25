package com.invs.invitationssb.service;

import com.invs.invitationssb.model.Guest;
import com.invs.invitationssb.model.RSVP;
import com.invs.invitationssb.repository.RSVPRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RSVPService {
    private final RSVPRepository rsvpRepository;

    public RSVPService(RSVPRepository rsvpRepository) {
        this.rsvpRepository = rsvpRepository;
    }

    public RSVP saveRSVP(RSVP rsvp) {
        if (rsvp.getGuestList() != null) {
            for (Guest guest : rsvp.getGuestList()) {
                guest.setRsvp(rsvp);
            }
        }

        return rsvpRepository.save(rsvp);
    }

    public List<RSVP> getAllRSVPs() {
        return rsvpRepository.findAll();
    }

    public Optional<RSVP> getRSVPById(Long id) {
        return rsvpRepository.findById(id);
    }

    public RSVP addGuestToRSVP(Long rsvpId, Guest guest) {
        Optional<RSVP> optionalRsvp = rsvpRepository.findById(rsvpId);

        if (optionalRsvp.isPresent()) {
            RSVP rsvp = optionalRsvp.get();
            rsvp.getGuestList().add(guest);

            return rsvpRepository.save(rsvp);
        }

        throw new RuntimeException(String.format("RSVP with id %s not found", rsvpId));
    }

    public RSVP removeGuestFromRSVP(Long rsvpId, String guestName) {
        Optional<RSVP> optionalRsvp = rsvpRepository.findById(rsvpId);
        if (optionalRsvp.isPresent()) {
            RSVP rsvp = optionalRsvp.get();
            rsvp.getGuestList().removeIf(guest -> guest.getName().equals(guestName));

            return rsvpRepository.save(rsvp);
        }

        throw new RuntimeException(String.format("RSVP with id %s not found", rsvpId));
    }
}
