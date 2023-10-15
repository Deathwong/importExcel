package com.jeff.importexcel.service.implement;

import com.jeff.importexcel.service.MailService;
import jakarta.mail.internet.MimeMessage;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMailToEdemForMoney(Workbook workbook) {

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String[] mails = new String[]{"baptistejean457@gmail.com", "nina.toulassi@outlook.fr"};
            helper.setTo(mails);
            helper.setSubject("Envoyé Depuis ue application Java que j'ai développé ce week-end");
            helper.setText("fichier");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                workbook.write(byteArrayOutputStream);
            } catch (Exception exception) {
                throw new RuntimeException();
            }

            byte[] excelBytes = byteArrayOutputStream.toByteArray();

            helper.addAttachment("fichier.xlsx", new ByteArrayResource(excelBytes));

            emailSender.send(message);

        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}