package cn.nean.boss.util;

import cn.nean.boss.model.message.EmailMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    /*
    *  发送邮件
    * */
    public void sendEmail(EmailMsg emailMsg){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailMsg.getTo());
        message.setSubject(emailMsg.getTitle());
        message.setText(emailMsg.getMessage());
        mailSender.send(message);
        log.info("邮件发成功:{}",message.toString());
    }

    /*
    * 发送验证码
    * */
    public void sendVerificationCode(String to,String subject,String verificationCode){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(verificationCode);
        mailSender.send(message);
        log.info("注册验证码已发送:{}",message.toString());
    }
}
