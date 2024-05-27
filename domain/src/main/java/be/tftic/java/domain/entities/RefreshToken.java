package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String token;

	private Instant expiryDate;

	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	private Utilisateur utilisateur;

}