package dinodidiodoro.S5ProgettoSettimanale.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DispositivoRunner implements CommandLineRunner{
	
	@Autowired
	DispositivoServiceImpl srv;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
