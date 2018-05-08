package javasender.config;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * givanaldoifrn@hotmail.com
 * @author 20171148060009
 */
public class MailSender {
    Properties credenciasUsario;
    Properties configuracaoServidorEmail;
    UsuarioAuthenticator auth;
    Session session;

    public MailSender() {
        credenciasUsario = new Properties();
        loadPreperties();
        System.out.println("c");
    }    
    private void loadPreperties() {
        try {
            credenciasUsario.load(ClassLoader.getSystemClassLoader().getResourceAsStream("javasender/config/credencias.properties"));
            // BUG
            System.out.println("a");
            configuracaoServidorEmail.load(ClassLoader.getSystemClassLoader().getResourceAsStream("javasender/config/config.properties"));
            System.out.println("b");
            
        } catch (IOException ex) {
            System.out.println("Erro ao carregar as propriedades");
        }
    }
    
    public void sendMensagem(String assunto, String mensagem, String destinatario) throws AddressException, MessagingException {
        if (auth != null) {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(auth.getEmail()));
            
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            
            message.setSubject(assunto);
            message.setContent(mensagem, "text/html");
            Transport.send(message);
        } else {
            autenticar();
            sendMensagem(assunto, mensagem, destinatario);
        }
    }
    
    private void autenticar() {
        auth = new UsuarioAuthenticator(credenciasUsario.getProperty("hotmail.email"), credenciasUsario.getProperty("hotmail.password"));
        // Inicia Debug
        session = Session.getDefaultInstance(configuracaoServidorEmail, auth);
        session.setDebug(true);
    }
}
