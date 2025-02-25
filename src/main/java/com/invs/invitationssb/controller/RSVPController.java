package com.invs.invitationssb.controller;

import com.invs.invitationssb.model.Guest;
import com.invs.invitationssb.model.RSVP;
import com.invs.invitationssb.service.RSVPService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rsvp")
@CrossOrigin(origins = "http://localhost:3000")
public class RSVPController {
    private final RSVPService rsvpService;

    public RSVPController(RSVPService rsvpService) {
        this.rsvpService = rsvpService;
    }

    @PostMapping
    public ResponseEntity<?> submitRSVP(@RequestBody RSVP rsvp) {
        try {
            RSVP savedRsvp = rsvpService.saveRSVP(rsvp);

            return ResponseEntity.ok(savedRsvp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Error: %s", e.getMessage()));
        }
    }

    @GetMapping
    public List<RSVP> getAllRSVPs() {
        return rsvpService.getAllRSVPs();
    }

    @GetMapping("/{id}")
    public Optional<RSVP> getRSVPById(@PathVariable Long id) {
        return rsvpService.getRSVPById(id);
    }

    @PostMapping("/{rsvpId}/guests")
    public RSVP addGuestToRSVP(@PathVariable Long rsvpId, @RequestBody Guest guest) {
        return rsvpService.addGuestToRSVP(rsvpId, guest);
    }

    @DeleteMapping("/{rsvpId}/guests/{guestName}")
    public RSVP removeGuestFromRSVP(@PathVariable Long rsvpId, @PathVariable String guestName) {
        return rsvpService.removeGuestFromRSVP(rsvpId, guestName);
    }
}
