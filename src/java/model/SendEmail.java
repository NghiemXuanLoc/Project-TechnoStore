///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.security.auth.Subject;

public class SendEmail {

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(User user) {
        boolean test = false;
        String toEmail = user.getEmail();
        String fromEmail = "thongbphe171683@fpt.edu.vn";
        String fromPass = "brby vdhi hivn jcyc";

        try {
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, fromPass);
                }
            });

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Registered successfully! Please verify your account using this code: " + user.getCode());

            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    public static String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return new BigInteger(1, tokenBytes).toString(16);
    }

    //////resetpasss
    public boolean sendVerificationEmail(User user, String token) {
        boolean test = false;
        String toEmail = user.getEmail();
        String fromEmail = "thongbphe171683@fpt.edu.vn";
        String fromPass = "brby vdhi hivn jcyc";
        String verificationLink = "http://localhost:9999/TechStore/views/user/item-page/resetpassword.jsp?token=" + token;

        try {
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, fromPass);
                }
            });

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("Email Verification");
            mess.setText("Click the link below to verify your email:\n" + verificationLink);

            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    public boolean sendEmailOrder(String toEmail, String Subject, String htmls) {
        boolean test = false;
        String fromEmail = "thongbphe171683@fpt.edu.vn";
        String fromPass = "brby vdhi hivn jcyc";

        try {
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, fromPass);
                }
            });

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject(Subject);
            mess.setContent(htmls, "text/html; charset=utf-8"); // Sử dụng setContent để gửi nội dung HTML

            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

}
