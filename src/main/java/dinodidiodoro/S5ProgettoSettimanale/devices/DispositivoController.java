package dinodidiodoro.S5ProgettoSettimanale.devices;

import dinodidiodoro.S5ProgettoSettimanale.exceptions.BadRequestException;
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
@RequestMapping("/api/dispositivi")
public class DispositivoController {

    private final DispositivoServiceImpl srv;

    @Autowired
    public DispositivoController(DispositivoServiceImpl srv) {
        this.srv = srv;
    }

    @PostMapping
    public ResponseEntity<Dispositivo> createDispositivo(@RequestBody DispositivoPayload dispositivoPayload) throws BadRequestException {
        Dispositivo newDispositivo = srv.createDispositivo(dispositivoPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDispositivo);
    }

    @GetMapping
    public ResponseEntity<Page<Dispositivo>> getAllDispositivi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        Page<Dispositivo> dispositivi = srv.find(page, size, sort);
        return ResponseEntity.ok(dispositivi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> getDispositivoById(@PathVariable Long id) throws NotFoundException {
        Dispositivo dispositivo = srv.getDispositivoById(id);
        return ResponseEntity.ok(dispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> updateDispositivo(@PathVariable Long id, @RequestBody DispositivoPayload dispositivoPayload) throws NotFoundException {
        Dispositivo updatedDispositivo = srv.updateDispositivo(id, dispositivoPayload);
        return ResponseEntity.ok(updatedDispositivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispositivo(@PathVariable Long id) throws NotFoundException {
        srv.delete(id);
        return ResponseEntity.noContent().build();
    }
}