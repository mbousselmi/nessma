package com.nessma.java.services;

import com.nessma.java.exceptions.AdressMailNotValidException;
import com.nessma.java.models.MailContact;

public interface IMailSenderService {
	
	 void sendMail(MailContact mailContact) throws AdressMailNotValidException;

}
