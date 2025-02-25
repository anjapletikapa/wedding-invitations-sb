package com.invs.invitationssb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class RSVP {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String guestName;
	private boolean attending;

	@OneToMany(mappedBy = "rsvp", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Guest> guestList = new ArrayList<>();
}
