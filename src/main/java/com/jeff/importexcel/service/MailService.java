package com.jeff.importexcel.service;

import org.apache.poi.ss.usermodel.Workbook;

public interface MailService {
    void sendMailToEdemForMoney(Workbook workbook);
}
