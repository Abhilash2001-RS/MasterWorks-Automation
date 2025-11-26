package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.BaseFramework;
import com.aurigo.masterworks.testframework.utilities.helper.EnvironmentHelper;
import org.testng.util.Strings;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailUtil extends BaseFramework {

    static Properties envProperties = EnvironmentHelper.loadProperties();

    //Get the session object
    static Properties properties = System.getProperties();
    static Session session;
    private static final String automationEmailId = "automationteam@aurigo.com";

    /**
     * Send email method with chart and attachments
     *
     * @param subject email subject
     * @param htmlContent html body content of the email
     * @param attachmentFilePath file location of attachment
     * @param chartPath Chart file path
     * @param chartCid Chart id
     */
    public static void sendEmail(String subject, String htmlContent, String attachmentFilePath, String chartPath, String chartCid) {
        setSession();
        try {
            var message = new MimeMessage(session);
            appendFromAndRecipientList(message);
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(htmlContent, "text/html");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFilePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("ConsolidatedReport.html");
            multipart.addBodyPart(messageBodyPart);

            if (Strings.isNotNullAndNotEmpty(chartPath) && Strings.isNotNullAndNotEmpty(chartCid)) {
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.attachFile(chartPath + ".png"); //TODO:Need to work on the extension part
                imagePart.setContentID("<" + chartCid + ">");
                imagePart.setDisposition(MimeBodyPart.INLINE);
                multipart.addBodyPart(imagePart);
            }

            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Send email method accessibility test
     *
     * @param subject            Mail subject
     * @param htmlContent        Mail body content
     * @param attachmentFilePath The pdf attachment generated in accessibility run is passed
     */
    public static void sendEmailAccessibility(String subject, String htmlContent, String attachmentFilePath) {
        setSession();
        try {
            var message = new MimeMessage(session);

            appendFromAndRecipientList(message);
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(htmlContent, "text/html");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFilePath);
            messageBodyPart.setDataHandler(new DataHandler(source));

            String fileName = String.format("%s" + "_AccessibilityReport.pdf", currentEnvironment.getName());

            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to send an email without an attachment
     *
     * @param subject email subject
     * @param htmlContent html bdy content
     */
    public static void sendEmail(String subject, String htmlContent) {
        setSession();
        try {
            var message = new MimeMessage(session);
            appendFromAndRecipientList(message);
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html");
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("Error sending email:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Setting up session for email
     */
    private static void setSession() {
        properties.put("mail.smtp.host", envProperties.getProperty("emailReport.smtpServer"));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols","TLSv1.2");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", envProperties.getProperty("emailReport.smtpPort"));
        properties.put("mail.smtp.ssl.trust", envProperties.getProperty("emailReport.smtpServer"));
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(envProperties.getProperty("emailReport.smtpUsername"), envProperties.getProperty("emailReport.smtpPassword"));
                    }
                });
    }

    /**
     * Appends the from,to & cc address to the email message
     *
     * @param message Mime Message object
     */
    private static void appendFromAndRecipientList(MimeMessage message){
        var from = envProperties.getProperty("emailReport.fromId");
        from  = Strings.isNullOrEmpty(from) ? automationEmailId : from;
        System.out.printf("From:%s%n",from);
        var to = envProperties.getProperty("emailReport.toList");
        to = Strings.isNullOrEmpty(to) ? automationEmailId : to;
        System.out.printf("To:%s%n",to);
        var cc = System.getProperty("email.toList");
        cc = Strings.isNullOrEmpty(cc) ? "" : cc;
        System.out.printf("Cc:%s%n",cc);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, to);
            message.addRecipients(Message.RecipientType.CC, cc);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
