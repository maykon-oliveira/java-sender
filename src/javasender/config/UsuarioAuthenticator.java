package javasender.config;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author 20171148060009
 */
public class UsuarioAuthenticator extends Authenticator {
    private final String email;
    private final String password;

    public UsuarioAuthenticator(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
