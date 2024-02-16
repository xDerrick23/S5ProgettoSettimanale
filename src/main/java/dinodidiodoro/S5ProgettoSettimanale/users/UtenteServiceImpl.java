package dinodidiodoro.S5ProgettoSettimanale.users;

import dinodidiodoro.S5ProgettoSettimanale.devices.Dispositivo;
import dinodidiodoro.S5ProgettoSettimanale.devices.DispositivoRepository;
import dinodidiodoro.S5ProgettoSettimanale.devices.Stato;
import dinodidiodoro.S5ProgettoSettimanale.exceptions.BadRequestException;
import dinodidiodoro.S5ProgettoSettimanale.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService {

	private final UtenteRepository repo;
	private final DispositivoRepository repod;

	@Autowired
	public UtenteServiceImpl(UtenteRepository repo, DispositivoRepository repod) {
		this.repo = repo;
		this.repod = repod;
	}

	@Override
	public Utente create(UtentePayload body) {
		Optional<Utente> existingUtente = repo.findByEmail(body.getEmail());
		if (existingUtente.isPresent()) {
			throw new BadRequestException("L'email è già stata utilizzata");
		}

		Utente newUtente = new Utente(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail(),
				body.getPassword());
		return repo.save(newUtente);
	}

	@Override
	public Page<Utente> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return repo.findAll(pageable);
	}

	@Override
	public Utente findById(Long id) throws NotFoundException {
		return repo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	public Utente findByIdAndUpdate(Long id, UtentePayload body) throws NotFoundException {
		Utente found = this.findById(id);
		found.setUsername(body.getUsername());
		found.setNome(body.getNome());
		found.setCognome(body.getCognome());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());
		return repo.save(found);
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		repo.deleteById(id);
	}

	@Override
	public Utente findByEmail(String email) {
		return repo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}

	@Override
	public Utente addDispositivoToUtente(Long utenteId, Long dispositivoId) throws NotFoundException {
		Utente utente = repo.findById(utenteId)
				.orElseThrow(() -> new NotFoundException("Utente con ID " + utenteId + " non trovato"));

		Dispositivo dispositivo = repod.findById(dispositivoId)
				.orElseThrow(() -> new NotFoundException("Dispositivo con ID " + dispositivoId + " non trovato"));

		if (dispositivo.getStato() != Stato.DISPONIBILE) {
			throw new BadRequestException(
					"Il dispositivo deve essere in stato 'DISPONIBILE' per poter essere aggiunto a un utente.");
		}
		
        dispositivo.setStato(Stato.ASSEGNATO);
		utente.addDispositivo(dispositivo);
		repod.save(dispositivo);
		repo.save(utente);
		
		return utente;
	}
}
