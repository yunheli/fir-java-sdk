package fir.im.utils;

import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/19
 * Time: 下午11:10
 * To change this template use File | Settings | File Templates.
 */
public class TimerScan {

    Timer timer;
    long Interval = 3000;
    long delay = 3000;

    public TimerScan(){
        timer = new Timer();
        timer.schedule(new TimerScanTask(), delay, Interval);

    }
}
