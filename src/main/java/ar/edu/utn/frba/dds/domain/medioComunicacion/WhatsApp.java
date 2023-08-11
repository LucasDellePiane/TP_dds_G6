package ar.edu.utn.frba.dds.domain.medioComunicacion;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import ar.edu.utn.frba.dds.exceptions.SeEnvioWhatsappException;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

public class WhatsApp implements MedioComunicacion {

    private static final String apiURL = "https://api.whatsapp.com/send?";
    private Retrofit retrofit;

    // Constructor e inicialización de las credenciales de Twilio
    public WhatsApp() {
        String ACCOUNT_SID = "your_account_sid";
        String AUTH_TOKEN = "your_auth_token";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    private void enviarNotificacion(String numeroTelefono, String mensaje) {
        PhoneNumber to = new PhoneNumber(numeroTelefono);
        PhoneNumber from = new PhoneNumber("your_twilio_phone_number");
        throw new SeEnvioWhatsappException("Mensaje enviado al usuario");
       // Message.creator(to, from, mensaje).create();
    }

}
