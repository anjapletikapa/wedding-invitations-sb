package com.invs.invitationssb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	@Column(name = "is_child")
	private boolean isChild;

	public boolean getIsChild() {
		return isChild;
	}

	public void setIsChild(boolean isChild) {
		this.isChild = isChild;
	}

	@ManyToOne
	@JoinColumn(name = "rsvp_id")
	@JsonBackReference
	private RSVP rsvp;
}
