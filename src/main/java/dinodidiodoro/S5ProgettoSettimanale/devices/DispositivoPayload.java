package dinodidiodoro.S5ProgettoSettimanale.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DispositivoPayload {
    private String tipo;
    private Stato stato;
}