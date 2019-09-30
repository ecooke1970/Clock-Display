
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Erik Cooke
 * @version 2019.09.30
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String ampm;

    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00AM.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        ampm = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, String ampm)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, ampm);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if(hours.getValue() == 0)
            {
                if(ampm == "AM")
                {
                    setAmpm("PM");
                }
                else
                {
                    setAmpm("AM");
                }
            }            
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, String ampm)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        setAmpm(ampm);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }

    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if(hours.getValue() == 0)
        {
            displayString = "12" + ":" + minutes.getDisplayValue() + ampm;
        }
        else
        {
            displayString = hours.getValue() + ":" + minutes.getDisplayValue() + ampm;
        }
    }

    /**
     * Checks if AM or am has been entered for time of day and sets the ampm variable.
     * If anything else is entered ampm is set to PM.
     */
    private void setAmpm(String ampm)
    {
        //if(ampm == "AM" || ampm == "am" || ampm == "Am" || ampm == "aM")
        if(ampm.toUpperCase().equals("AM"))
        {
            this.ampm = "AM";
        }
        else
        {
            this.ampm = "PM";
        }
    }

}
