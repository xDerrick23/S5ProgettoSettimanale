package dinodidiodoro.S5ProgettoSettimanale.devices;

import dinodidiodoro.S5ProgettoSettimanale.exceptions.BadRequestException;
import dinodidiodoro.S5ProgettoSettimanale.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

public interface DispositivoService {
	
    Dispositivo createDispositivo(DispositivoPayload dispositivoPayload) throws BadRequestException;
    
    Page<Dispositivo> find(int page, int size, String sort);
    
    Dispositivo getDispositivoById(Long id) throws NotFoundException;
    
    Dispositivo updateDispositivo(Long id, DispositivoPayload dispositivoPayload) throws NotFoundException;
    
    void delete(Long id) throws NotFoundException;
}
