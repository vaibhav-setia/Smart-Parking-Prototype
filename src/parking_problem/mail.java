package parking_problem;


import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class mail {
    public static void main(String[] args) {
          Properties props = new Properties();
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.host", "smtp.gmail.com");
          props.put("mail.smtp.port", "587");
          props.put("mail.debug", "true");
          Session session = Session.getInstance(props,
                new Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication("your e-mail address here", "your password here");
                  }
                });
        
           try {
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("sender's email address here"));
                msg.setRecipients(Message.RecipientType.TO, "reciever's email address here");
                msg.setSubject("Here is your parking ticket.");
                msg.setSentDate(new Date());
                
                Multipart multipart = new MimeMultipart();
                
                MimeBodyPart textPart = new MimeBodyPart();
                String textContent = "Car number: "+user_pge.textField.getText()+"\n"+"Phone number: "
                +user_pge.textField_1.getText()+"\n"+"Slot number :"+allotment.ticket+"\n"+
                "Booking time: "+allotment.current_time+"\n"+"Arrive by : "+ allotment.current_time1
                        ;
                textPart.setText(textContent);
                multipart.addBodyPart(textPart);
                
              /*  MimeBodyPart attachementPart = new MimeBodyPart();
                attachementPart.attachFile(new File("C:\\Users\\hp\\Desktop\\ticket.jpeg"));
                multipart.addBodyPart(attachementPart);*/
                /*MimeBodyPart attachementPart1 = new MimeBodyPart();
                attachementPart1.attachFile(new File("C:\\Users\\hp\\Desktop\\map.jpeg"));
                multipart.addBodyPart(attachementPart1);*/
                MimeBodyPart attachementPart = new MimeBodyPart();
                attachementPart.attachFile(new File("resources/QR.png"));
                multipart.addBodyPart(attachementPart);
                msg.setContent(multipart);
                Transport.send(msg);
                System.out.println("---Done---");
           } catch (Exception ex) {
                ex.printStackTrace();
           }
    }
}