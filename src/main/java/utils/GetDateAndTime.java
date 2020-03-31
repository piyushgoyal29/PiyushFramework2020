package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateAndTime 
{
	public static String currentDateAndTime() 
	{
		//To retrieve the current date and time.
		return new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(new Date());
	}
}
