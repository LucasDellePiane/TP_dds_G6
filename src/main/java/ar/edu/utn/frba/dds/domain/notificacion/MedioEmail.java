package ar.edu.utn.frba.dds.domain.notificacion;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.exceptions.SeEnvioEmailException;
import ar.edu.utn.frba.dds.exceptions.SeEnvioWhatsappException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;


public class MedioEmail implements MedioComunicacion{
    private List<Usuario> usuariosSuscriptos;

    private final String username = "msolgarcialinf@gmail.com";
    

    @Override
    public void suscribirUsuario(Usuario usuario) {
        usuariosSuscriptos.add(usuario);
    }

    @Override
    public void enviarNotifiacion(List<Usuario> usuarios) {
        List<Usuario> usuariosANotificar = usuarios.stream()
            .filter(unUsuario -> usuariosSuscriptos.contains(unUsuario))
            .toList();
        // Enviar mensajes a los usuarios utilizando la API de Email
        for (Usuario usuario : usuariosANotificar) {
            this.enviarEmail(usuario.getEmail(),"Nuevo incidente reportado");
        }

    }

    private void enviarEmail(String emailUsuario, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        throw new SeEnvioEmailException("Mensaje enviado al usuario");

        /*
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, accessToken);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("msolgarcialinf@gmail.com"));
            message.setSubject("Notificación de incidente");
            message.setText(mensaje);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage());
        }
        */

    }


    public void Email() {
        List<Usuario> usuarios = new ArrayList<>(Arrays.asList());
        this.usuariosSuscriptos = usuarios;
    }


    @Override
    public List<Usuario> getUsuariosSuscriptos() {
        return usuariosSuscriptos;
    }

    public MedioEmail() {
        List<Usuario> usuariosSuscriptos = new ArrayList<>();
        this.usuariosSuscriptos = usuariosSuscriptos;
    }
}
