package com.kidoz.vacationbonus;

import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;


/* This class is responsible for actually processing
 * "Mail Events" and sending emails using the Java Mail API.
 * 
 * Note that this class is an EJB.
 * 
 */
@Singleton
public class EmailApi {
	

	
	
	/* To make the mail sending routine asynchronous, we annotate this method
	 * with @Asynchronous and @Lock(READ). These are EJB annotations, not CDI.
	 * 
	 * we can also include the CDI @Observers annotation and 
	 * its "during = AFTER_SUCCESS" attribute. This is what guarantees that this event
	 * will only be processed if the transaction commits successfully.
	 * eg : public void sendMail(@Observes(during = TransactionPhase.AFTER_SUCCESS) MailEvent event) */
	@Asynchronous
	@Lock(LockType.READ)
	static void sendEmail(String email,String subject,String body) {
        System.out.printf("%s\n%s\n%s%n",email,subject,body);
	}
}
