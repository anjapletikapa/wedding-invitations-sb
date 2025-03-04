package com.invs.invitationssb.controller;

import com.invs.invitationssb.model.Guest;
import com.invs.invitationssb.model.RSVP;
import com.invs.invitationssb.service.RSVPService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rsvp")
public class RSVPController {
    private final RSVPService rsvpService;
    private final HttpHeaders headers = new HttpHeaders();

    public RSVPController(RSVPService rsvpService) {
        this.rsvpService = rsvpService;
    }

    @PostMapping
    public ResponseEntity<?> submitRSVP(@RequestBody RSVP rsvp, HttpServletRequest request) {
        try {
            return ResponseEntity.ok()
                    .headers(setCorsHeaders(request))
                    .body(rsvpService.saveRSVP(rsvp));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Error: %s", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<RSVP>> getAllRSVPs(HttpServletRequest request) {
        return ResponseEntity.ok()
                .headers(setCorsHeaders(request))
                .body(rsvpService.getAllRSVPs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RSVP>> getRSVPById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok()
                .headers(setCorsHeaders(request))
                .body(rsvpService.getRSVPById(id));
    }

    @PostMapping("/{rsvpId}/guests")
    public ResponseEntity<RSVP> addGuestToRSVP(@PathVariable Long rsvpId, @RequestBody Guest guest, HttpServletRequest request) {
        return ResponseEntity.ok()
                .headers(setCorsHeaders(request))
                .body(rsvpService.addGuestToRSVP(rsvpId, guest));
    }

    @DeleteMapping("/{rsvpId}/guests/{guestName}")
    public ResponseEntity<RSVP> removeGuestFromRSVP(@PathVariable Long rsvpId, @PathVariable String guestName, HttpServletRequest request) {
        return ResponseEntity.ok()
                .headers(setCorsHeaders(request))
                .body(rsvpService.removeGuestFromRSVP(rsvpId, guestName));
    }

    private HttpHeaders setCorsHeaders(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Allow-Origin", (origin != null) ? origin : "*");
        return headers;
    }
}
