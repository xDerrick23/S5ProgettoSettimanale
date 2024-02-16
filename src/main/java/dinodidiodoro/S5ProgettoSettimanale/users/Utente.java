package dinodidiodoro.S5ProgettoSettimanale.users;

import dinodidiodoro.S5ProgettoSettimanale.devices.Dispositivo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;

    @OneToMany(mappedBy = "utente")
    private List<Dispositivo> dispositivi = new ArrayList<>();

	public Utente(String username, String nome, String cognome, String email, String password) {
		super();
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", dispositivi=" + dispositivi + "]";
	}
	
	public void addDispositivo(Dispositivo dispositivo) {
        dispositivi.add(dispositivo);
        dispositivo.setUtente(this);
    }
}
