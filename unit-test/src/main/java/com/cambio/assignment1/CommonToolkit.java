package com.cambio.assignment1;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;

public final class CommonToolkit
{

    /**
     * @param number
     * @return return english time format as a string
     */
    public static String convertToEnglishFormat( String number )
    {
        try
        {
            String[] data = number.split(",");
            String englishFormat = data[0] + "." + data[1];
            System.out.println("English Format of Swedish Number " + number + " is " + englishFormat);
            return englishFormat;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Number format entered is invalid");
        }

        return "";
    }

    /**
     * @param previousDoesTime
     * @param duration
     * @return next does time as String
     * @throws ParseException
     */
    public static String getNextDoesTime( Date previousDoesTime , String duration ) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);

        Date turnForward = formatter.parse( "26-03-2019 02:00:00 PM" );
        Date turnBackward = formatter.parse( "29-10-2019 03:00:00 PM" );

        Calendar cal = Calendar.getInstance();
        cal.setTime( previousDoesTime );
        cal.add( Calendar.HOUR_OF_DAY, Integer.parseInt( duration ) );
        Date nextDoesTime = cal.getTime();


        // checks if next does time comes before turnForward
        if ( nextDoesTime.compareTo(turnForward) < 0 )
        {
            System.out.println( formatter.format(nextDoesTime) );
            return formatter.format( nextDoesTime );
        }
        // checks if does time crossing turnForward
        else if ( previousDoesTime.compareTo(turnForward) <= 0 && nextDoesTime.compareTo(turnForward) >= 0)
        {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(nextDoesTime);
            cal1.add(Calendar.HOUR_OF_DAY, 1);
            Date actualDoesTime=cal1.getTime();
            System.out.println("NextDoesTime : " + formatter.format( actualDoesTime ));
            return formatter.format( actualDoesTime );
        }
        // checks if does time crossing turnBackward
        else if ( previousDoesTime.compareTo( turnBackward ) <= 0 && nextDoesTime.compareTo( turnBackward ) >= 0)
        {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(nextDoesTime);
            cal2.add(Calendar.HOUR_OF_DAY, -1);
            Date actualDoesTime=cal2.getTime();
            System.out.println("NextDoesTime : " + formatter.format( actualDoesTime ));
            return formatter.format( actualDoesTime );
        }
        // checks if previous does time comes after turnForward or comes after turnBackward
        else if ( previousDoesTime.compareTo( turnBackward ) > 0 || previousDoesTime.compareTo( turnForward ) > 0 )
        {
            System.out.println("NextDoesTime : " + formatter.format(nextDoesTime)  );
            return formatter.format( nextDoesTime );
        }

        return "";
    }

    /**
     * @param textWithUnicode
     * @return text as normal text
     */
    public static String getSwedishText( String textWithUnicode )
    {
        try
        {
            byte[] utf8Bytes = textWithUnicode.getBytes("UTF8");
            String swedishText = new String(utf8Bytes,"UTF8");
            System.out.println("Normal Text = "+ swedishText );
            return swedishText;
        }
        catch ( UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return "";
    }


    public static void main( String[] args ){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH );
        Scanner sc = new Scanner( System.in );


        try
        {
            // Getting PreviousDoes time and Duration as user inputs
            System.out.println( "Enter PreviosDoesTime In Fromat [dd-M-yyyy hh:mm::ss aaa]:" );
            String dateInString = sc.nextLine();
            System.out.println( "Enter Duration in hours :" );
            String duration = sc.nextLine();

            Date previousDoesTime = formatter.parse( dateInString );
            CommonToolkit.getNextDoesTime( previousDoesTime, duration );
            CommonToolkit.convertToEnglishFormat("2,7" );
            CommonToolkit.getSwedishText("\u00E5sk\u00E5da" );
        }
        catch(ParseException e)
        {
            System.out.println( "!!! Invalid Date or Time Format" );
        }

    }

}
