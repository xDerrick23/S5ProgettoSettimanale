package dinodidiodoro.S5ProgettoSettimanale.users;

import dinodidiodoro.S5ProgettoSettimanale.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

public interface UtenteService {

	Utente create(UtentePayload body);

	Page<Utente> find(int page, int size, String sort);

	Utente findById(Long id) throws NotFoundException;

	Utente findByIdAndUpdate(Long id, UtentePayload body) throws NotFoundException;

	void delete(Long id) throws NotFoundException;

	Utente findByEmail(String email);

	public Utente addDispositivoToUtente(Long utenteId, Long dispositivoId) throws NotFoundException;
}
