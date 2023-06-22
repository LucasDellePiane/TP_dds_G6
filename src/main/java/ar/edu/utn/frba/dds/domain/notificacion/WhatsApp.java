package ar.edu.utn.frba.dds.domain.notificacion;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsApp implements MedioComunicacion {
    private List<Usuario> usuariosSuscriptos;
    private static final String apiURL = "https://api.whatsapp.com/send?";
    private Retrofit retrofit;

    // Constructor e inicialización de las credenciales de Twilio
    public WhatsApp() {
        String ACCOUNT_SID = "your_account_sid";
        String AUTH_TOKEN = "your_auth_token";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void suscribirUsuario(Usuario usuario) {
        usuariosSuscriptos.add(usuario);
    }

    @Override
    public void enviarNotifiacion(List<Usuario> usuarios) {
        List<Usuario> usuariosANotificar = usuarios.stream()
                .filter(unUsuario -> usuariosSuscriptos.contains(unUsuario))
                .toList();
        // Enviar mensajes a los usuarios utilizando la API de WhatsApp (Twilio en este ejemplo)
        for (Usuario usuario : usuariosANotificar) {
            enviarMensajeWhatsApp(usuario.getTelefono(), "Nuevo incidente reportado");
        }
    }

    // Método para enviar un mensaje de WhatsApp utilizando Twilio
    private void enviarMensajeWhatsApp(String numeroTelefono, String mensaje) {
        PhoneNumber to = new PhoneNumber(numeroTelefono);
        PhoneNumber from = new PhoneNumber("your_twilio_phone_number");
        Message.creator(to, from, mensaje).create();
    }

}
