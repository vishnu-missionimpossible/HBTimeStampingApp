package in.ineuron.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import in.ineuron.Model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class ObjectUpdationTimeStamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction transaction = null;
		boolean flag =true;
		BankAccount account = null;
		
		session=HibernateUtil.getSession();
		
		try {
			transaction = session.beginTransaction();
			account  = session.get(BankAccount.class, 1L);
				if(account!=null) {
					account.setBalance(account.getBalance()+10000);
					flag= true;
				}else {
					System.out.println("Record not available for update...");
					System.exit(0);
				}
			
			
		}catch(HibernateException e) {
			e.printStackTrace();
			flag = false;
		}finally {
			if(flag) {
				transaction.commit();
				System.out.println("Account opened on :: "+account.getOpeningDate());
				System.out.println("Account modified on :: "+account.getLastUpdated());
				System.out.println("No of Modifications is :: "+account.getVersion());
			}else {
				transaction.rollback();
			}
			HibernateUtil.closeSession(session);
		}
		

	}

}
