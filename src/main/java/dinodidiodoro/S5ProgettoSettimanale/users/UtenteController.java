package dinodidiodoro.S5ProgettoSettimanale.users;

import dinodidiodoro.S5ProgettoSettimanale.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

	private final UtenteServiceImpl srv;

	@Autowired
	public UtenteController(UtenteServiceImpl srv) {
		this.srv = srv;
	}

	@PostMapping
	public ResponseEntity<Utente> createUser(@RequestBody UtentePayload body) {
		Utente newUser = srv.create(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	@GetMapping
	public ResponseEntity<Page<Utente>> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
		Page<Utente> usersPage = srv.find(page, size, sort);
		return ResponseEntity.ok(usersPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Utente> getUserById(@PathVariable Long id) throws NotFoundException {
		Utente user = srv.findById(id);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Utente> updateUser(@PathVariable Long id, @RequestBody UtentePayload body)
			throws NotFoundException {
		Utente updatedUser = srv.findByIdAndUpdate(id, body);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws NotFoundException {
		srv.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Utente> getUserByEmail(@PathVariable String email) throws NotFoundException {
		Utente user = srv.findByEmail(email);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/{utenteId}/add-dispositivo/{dispositivoId}")
	public ResponseEntity<Utente> addDispositivoToUtente(@PathVariable Long utenteId, @PathVariable Long dispositivoId)
			throws NotFoundException {
		Utente utente = srv.addDispositivoToUtente(utenteId, dispositivoId);
		return ResponseEntity.ok(utente);
	}
}
