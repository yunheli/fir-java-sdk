package fir.im.dialog;

import com.sun.awt.AWTUtilities;
import fir.im.utils.OsUtil;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.PropertySetter;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 15/9/7
 * Time: 下午2:06
 * To change this template use File | Settings | File Templates.
 */
public class TipDialog extends JDialog{
    private static TipDialog jDialog;
    protected Animator _animator;
    private boolean _alreadyHidden = false;
    float alpha = 1.0f;
    public TipDialog() {
        super();
        addPanel();
        init();
        jDialog = this;
        this.setVisible(true);

    }

    public static TipDialog getInstance(){
        if(jDialog == null) return new TipDialog();
        return jDialog;
    }

    public void addPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JLabel tipLabel = new JLabel("应用上传成功:-) ");
        tipLabel.setBounds(52,13,100,30);
        tipLabel.setForeground(Color.white);
        jPanel.setBackground(Color.BLACK);
        jPanel.setSize(200,60);
        jPanel.add(tipLabel);
        this.add(jPanel);
    }

    public void init(){
        this.setSize(200, 60);
        this.setUndecorated(true);
        this.setBackground(Color.black);
        initAnimator();
        this.setLocation(OsUtil.getScreenSize().width-200,100);
        AWTUtilities.setWindowOpacity(TipDialog.this, 0.0f);
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
//        System.out.println("alpha#"+alpha);
        AWTUtilities.setWindowOpacity(TipDialog.this, alpha);
        repaint();

    }

    protected void initAnimator() {
        final TimingSource ts = new SwingTimerTimingSource();
        Animator.setDefaultTimingSource(ts);
        ts.init();
        this._animator = new Animator.Builder()
                .addTarget(
                        PropertySetter.getTarget(this, "alpha", new Float[]{
                                Float.valueOf(0.0F), Float.valueOf(1.0f)}))
                .setDuration(1000L, TimeUnit.MILLISECONDS).build();
    }

    public void fadeIn(int delay) {
        new java.util.Timer().schedule(new TimerTask() {
            public void run() {
                TipDialog.this.fadeIn();
            }
        }, delay);
    }

    public void fadeOut(int delay) {
        new java.util.Timer().schedule(new TimerTask() {
            public void run() {
                TipDialog.this.fadeOut();
            }
        }, delay);
    }

    public void tip(){
        fadeIn(0);
        fadeOut(3000);
    }

    public void fadeIn() {
        if (this._animator.isRunning())
            this._animator.restart();
        else {
            this._animator.start();
        }
        this._alreadyHidden = false;
    }

    public void fadeOut() {
        if ((!(this._alreadyHidden)) && (!(this._animator.reverseNow()))) {
            this._animator.restartReverse();
        }
        this._alreadyHidden = true;
    }

}
