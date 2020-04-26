package ua.nure.odnokozov.railway.ticket.office.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EmailSender {
    
    private static final Logger LOG = Logger.getLogger(EmailSender.class);

    private static final String RESOURCE_NAME = "email.properties";

    public void sendActivationCode(String recipientAddress, String activationCode) {
        LOG.info("Sending activation code");
        Properties props = new Properties();
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(RESOURCE_NAME)) {
            props.load(input);
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("userName"), props.getProperty("password"));
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("from@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
                message.setSubject("Activation Code");
                message.setText(
                        "Dear Client, Your activation ñode: " + activationCode + " \n\n Please do not spam my email!");
                Transport.send(message);
            } catch (MessagingException e) {
               LOG.error("Exception while sending activation code", e);
            }
        } catch (IOException ex) {
            LOG.error("Exception while loading properties", ex);
        }
    }
}
