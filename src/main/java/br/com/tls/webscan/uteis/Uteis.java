package br.com.tls.webscan.uteis;

import java.util.Calendar;
import java.util.Date;

public class Uteis {

	public static Date getData0000(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		
		return calendar.getTime();
	}

	public static Date getData2359(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 58);
		calendar.set(Calendar.SECOND, 58);
		
		return calendar.getTime();
	}
	
	
	
	
}
