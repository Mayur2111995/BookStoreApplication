package com.example.bookstoreapplication.Service;


import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
@Slf4j
public class MailService {

    public static void send(String toEmail, String subject, String body,String Book,String totalPrice)
    {
        final String fromEmail ="barimayur1265@gmail.com";  //correct  for gmail id
        // requires valid gmail id
        final String password ="oqpyuyiujdpjtmmp"; // correct password for gmail id
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        // SMTP Host
        props.put("mail.smtp.port", "587");
        // TLS Port
        props.put("mail.smtp.auth", "true");
        // enable authentication
        props.put("mail.smtp.starttls.enable", "true");
        // enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // to check email sender credentials are valid or not
        Authenticator auth = new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(fromEmail, password);

            }
        };
        javax.mail.Session session = Session.getInstance(props, auth);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply"));
            msg.setReplyTo(InternetAddress.parse(("barimayur1265@gmail.com"), false));  //correct  for gmail id
            msg.setSubject(subject, "UTF-8");
            msg.setText(body+Book+" totalPrice= "+totalPrice, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
            log.info("Email Sent Successfully........."); }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
