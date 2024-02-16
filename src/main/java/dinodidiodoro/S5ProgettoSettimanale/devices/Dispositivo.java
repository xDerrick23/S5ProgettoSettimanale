package dinodidiodoro.S5ProgettoSettimanale.devices;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dinodidiodoro.S5ProgettoSettimanale.users.Utente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@NoArgsConstructor
public class Dispositivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	@Enumerated(EnumType.STRING)
	private Stato stato;
	@ManyToOne
	@JsonIgnore
	private Utente utente;

	public Dispositivo(String tipo, Stato stato) {
		super(); 
		this.tipo = tipo;
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Dispositivi [tipo=" + tipo + ", stato=" + stato + ", utente=" + utente + "]";
	}

}
