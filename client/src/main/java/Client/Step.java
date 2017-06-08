package Client;

import java.util.Date;

/**
 * Created by Jp on 01/06/2017.
 */
public class Step
{
    private int steps;
    private long registrationDate;


    public Step(int steps, long registrationDate) {
        this.setSteps(steps);
        this.setRegistrationDate(registrationDate);
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public long getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(long registrationDate) {
        this.registrationDate = registrationDate;
    }
}
