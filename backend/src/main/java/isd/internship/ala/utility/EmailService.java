package isd.internship.ala.utility;

import isd.internship.ala.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendUserNotification(User user) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(user.getEmail());
        mail.setFrom("intherintership@gmail.com");
        mail.setSubject("ISD Leave Requests");
        mail.setText("One of your leave requests have been approved. Check your profile page!");
        javaMailSender.send(mail);
    }

    public void sendAdminNotification(List<User> admins, User user) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        for(User admin: admins) {
            System.out.println(admin.getEmail());
            simpleMailMessage.setTo(admin.getEmail());
            simpleMailMessage.setFrom("intherintership@gmail.com");
            simpleMailMessage.setSubject("ISD Leave Request");
            simpleMailMessage.setText("A new leave request have been created by " + user.getName() + " " + user.getSurname());
            javaMailSender.send(simpleMailMessage);
        }
    }
}
