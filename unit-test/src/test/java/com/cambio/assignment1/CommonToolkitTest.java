package com.cambio.assignment1;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CommonToolkitTest extends TestCase
{

    @Test
    public void testconvertToEnglishFormat_oneDecimal_returnEngDecimal()
    {
       assertEquals ("9.7", CommonToolkit.convertToEnglishFormat("9,7") );
    }

    @Test
    public void testconvertToEnglishFormat_threeDecimal_returnEngishDecimal()
    {
        assertEquals ("9.789", CommonToolkit.convertToEnglishFormat("9,789") );
    }

    @Test
    public void testNumberFormatConvert_zero_returnEnglishDecimal()
    {
        assertEquals ("0.0", CommonToolkit.convertToEnglishFormat("0,0") );
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNumberFormatConvert_invalidInput_throwException()
    {
        CommonToolkit.convertToEnglishFormat("23");
    }

    @Test
    public void testNextDoesTime_BeforeTurnForward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "26-03-2019 09:00:00 AM" );
        assertEquals("26-3-2019 11:00:00 AM",CommonToolkit.getNextDoesTime(previousDoesTime, "2" ) );
    }

    @Test
    public void testDoesTime_CrossTurnForward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "26-03-2019 01:00:00 PM" );
        assertEquals("26-3-2019 06:00:00 PM",CommonToolkit.getNextDoesTime(previousDoesTime,"4" ) );
    }

    @Test
    public void testPreviousDoesTime_OnTurnForward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "26-03-2019 02:00:00 PM" );
        assertEquals("26-3-2019 07:00:00 PM",CommonToolkit.getNextDoesTime(previousDoesTime,"4" ) );
    }

    @Test
    public void testPreviousDoesTime_AfterTurnForward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "26-03-2019 11:00:00 PM" );
        assertEquals("27-3-2019 01:00:00 AM",CommonToolkit.getNextDoesTime(previousDoesTime,"2" ) );
    }

    @Test
    public void testDoesTime_BetweenTurns_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "12-06-2019 02:00:00 PM" );
        assertEquals("12-6-2019 07:00:00 PM",CommonToolkit.getNextDoesTime(previousDoesTime,"5" ) );
    }

    @Test
    public void testDoesTime_CrossTurnBackward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "29-10-2019 02:00:00 PM" );
        assertEquals("29-10-2019 05:00:00 PM",CommonToolkit.getNextDoesTime(previousDoesTime,"4" ) );
    }

    @Test
    public void testPreviousDoesTime_OnTurnBackward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "29-10-2019 03:00:00 PM" );
        assertEquals("29-10-2019 07:00:00 PM",CommonToolkit.getNextDoesTime(previousDoesTime,"5" ) );
    }

    @Test
    public void testPreviousDoesTime_AfterTurnBackward_returnNextDoesTime() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss aaa", Locale.ENGLISH);
        Date previousDoesTime = formatter.parse( "29-10-2019 04:00:00 PM" );
        assertEquals("29-10-2019 08:00:00 PM",CommonToolkit.getNextDoesTime(previousDoesTime,"4" ) );
    }

    @Test
    public void testGetSwedishText_senarioOne_returnNormalText()
    {
       assertEquals("åskåda" , CommonToolkit.getSwedishText("\u00E5sk\u00E5da" ) );
    }

    @Test
    public void testGetSwedishText_senarioTwo_returnNormalText()
    {
        assertEquals("ÕjǥrkǦ" , CommonToolkit.getSwedishText("\u00D5j\u01E5rk\u01E6" ) );
    }


}


