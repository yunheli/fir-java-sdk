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
public class WarningDialog extends JDialog{
    private static WarningDialog jDialog;
    protected Animator _animator;
    private boolean _alreadyHidden = false;
    float alpha = 1.0f;
    JLabel tipLabel;
    public WarningDialog() {
        super();
        addPanel();
        init();
        jDialog = this;
        this.setVisible(true);

    }

    public WarningDialog(String tip) {
        super();
        addPanel();
        init();
        jDialog = this;
        this.setVisible(true);
    }

    public static WarningDialog getInstance(){
        if(jDialog == null) return new WarningDialog();
        return jDialog;
    }

    public void addPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        tipLabel = new JLabel("上传失败，请检查您的网络或api_token是否正确:-) ");
        tipLabel.setBounds(0,13,330,30);
        tipLabel.setForeground(Color.white);
        jPanel.setBackground(new Color(234,163,163));
        jPanel.setSize(330,60);
        jPanel.add(tipLabel);
        this.add(jPanel);
    }

    public void init(){
        this.setSize(330, 60);
        this.setUndecorated(true);
        this.setBackground(Color.black);
        initAnimator();
        this.setLocation(OsUtil.getScreenSize().width-330,120);
        AWTUtilities.setWindowOpacity(WarningDialog.this, 0.0f);
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
//        System.out.println("alpha#"+alpha);
        AWTUtilities.setWindowOpacity(WarningDialog.this, alpha);
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
                WarningDialog.this.fadeIn();
            }
        }, delay);
    }

    public void fadeOut(int delay) {
        new java.util.Timer().schedule(new TimerTask() {
            public void run() {
                WarningDialog.this.fadeOut();
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
