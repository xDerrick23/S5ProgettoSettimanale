package dinodidiodoro.S5ProgettoSettimanale.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UtenteRunner implements CommandLineRunner{

	@Autowired
	UtenteServiceImpl srv;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
