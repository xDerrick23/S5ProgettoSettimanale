package dinodidiodoro.S5ProgettoSettimanale.devices;

import dinodidiodoro.S5ProgettoSettimanale.exceptions.BadRequestException;
import dinodidiodoro.S5ProgettoSettimanale.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoServiceImpl implements DispositivoService{

	private final DispositivoRepository repo;
	
	@Autowired
	public DispositivoServiceImpl(DispositivoRepository repo) {
		this.repo = repo;
	}
	 @Override
	    public Dispositivo createDispositivo(DispositivoPayload dispositivoPayload) throws BadRequestException {
	        Dispositivo dispositivo = new Dispositivo(dispositivoPayload.getTipo(), dispositivoPayload.getStato());
	        return repo.save(dispositivo);
	    }

	 @Override
	    public Page<Dispositivo> find(int page, int size, String sort) {
	        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
	        return repo.findAll(pageable);
	    }
	    @Override
	    public Dispositivo getDispositivoById(Long id) throws NotFoundException {
	        return repo.findById(id).orElseThrow(() -> new NotFoundException(id));
	    }

	    @Override
	    public Dispositivo updateDispositivo(Long id, DispositivoPayload dispositivoPayload) throws NotFoundException {
	        Dispositivo dispositivo = getDispositivoById(id);
	        dispositivo.setTipo(dispositivoPayload.getTipo());
	        dispositivo.setStato(dispositivoPayload.getStato());
	        return repo.save(dispositivo);
	    }

	    @Override
	    public void delete(Long id) throws NotFoundException {
	        repo.deleteById(id);
	    }
	}
