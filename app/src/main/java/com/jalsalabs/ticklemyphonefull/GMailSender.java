package com.jalsalabs.ticklemyphonefull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GMailSender extends Authenticator {
    private String mailhost = "smtp.gmail.com";
    private String password;
    private Session session;
    private String user;

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            this.data = data;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            if (this.type == null) {
                return "application/octet-stream";
            }
            return this.type;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(this.data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }

    static {
        Security.addProvider(new JSSEProvider());
    }

    public GMailSender(String user, String password) {
        this.user = user;
        this.password = password;
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", this.mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
        props.put("mail.smtp.starttls.enable", "true");
        this.session = Session.getDefaultInstance(props, this);
    }

    /* Access modifiers changed, original: protected */
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients, String attach_filename) throws Exception {
        try {
            MimeMessage message = new MimeMessage(this.session);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(handler);
            MimeBodyPart body1 = new MimeBodyPart();
            MimeBodyPart attachMent = new MimeBodyPart();
            attachMent.setDataHandler(new DataHandler(new FileDataSource(new File(attach_filename))));
            attachMent.setFileName(attach_filename);
            attachMent.setDisposition("attachment");
            Multipart multipart = new MimeMultipart();
            body1.setText(body);
            multipart.addBodyPart(body1);
            multipart.addBodyPart(attachMent);
            message.setContent(multipart);
            if (recipients.indexOf(44) > 0) {
                message.setRecipients(RecipientType.TO, InternetAddress.parse(recipients));
            } else {
                message.setRecipient(RecipientType.TO, new InternetAddress(recipients));
            }
            Transport.send(message);
        } catch (Exception e) {
            TML_Library.Debug("ERROR1****************" + e);
        }
        return;
    }
}
