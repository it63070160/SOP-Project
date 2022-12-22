package com.example.orderservice.services;

import com.example.orderservice.command.CreateOrderCommand;
import com.example.orderservice.command.rest.CreateOrderRestModel;
import com.example.orderservice.command.rest.MailSenderRestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, CreateOrderRestModel model, CreateOrderCommand command){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            String htmlMsg = "<h3>Order id : " + command.getOrderId() + " completed</h3>"
                    + "<p>Address : " + model.getBuyerAddress() + "</p>"
                    + "<table><tr><th>Book Name</th><th>Quantity</th></tr>";
            for (MailSenderRestModel data : model.getBookListMail()){
                htmlMsg = htmlMsg + "<tr><td>" + data.getBookName() + "</td><td>" + data.getOrderQuantity() + "</td></tr>";
            }
            htmlMsg = htmlMsg + "</table><p>Total : " + model.getTotal() + "</p>";
            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("ploikongbookstore@gmail.com");
            helper.setTo(toEmail);
            helper.setText(htmlMsg, true);

            javaMailSender.send(message);
            System.out.println("Mail Sent successfully to " + toEmail);
        }catch (MessagingException ex) {
            System.out.println(ex.getLocalizedMessage());
        }



    }
}