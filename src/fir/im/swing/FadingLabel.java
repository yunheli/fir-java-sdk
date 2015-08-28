package fir.im.swing;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class FadingLabel extends JLabel {
    protected Animator animator;
    protected String newLabelText;
    public FadingLabel(String s, Icon icon, int i) {
        super(s, icon, i);
        initAnimator();
    }
    public FadingLabel(String s, int i) {
        super(s, i);
        initAnimator();
    }
    public FadingLabel(String s) {
        super(s);
        initAnimator();
    }
    public FadingLabel(Icon icon, int i) {
        super(icon, i);
        initAnimator();
    }
    public FadingLabel(Icon icon) {
        super(icon);
        initAnimator();
    }
    public FadingLabel() {
        super();
        initAnimator();
    }
    protected void initAnimator() {
        final TimingSource ts = new SwingTimerTimingSource();
        Animator.setDefaultTimingSource(ts);
        ts.init();
        animator = new Animator.Builder().setDuration(250, TimeUnit.MILLISECONDS).setRepeatCount(2).setRepeatBehavior(Animator.RepeatBehavior.REVERSE).setStartDirection(Animator.Direction.BACKWARD).addTarget(new TimingTargetAdapter() {
            @Override
            public void repeat(Animator source) {
                setText(newLabelText);
            }
            @Override
            public void timingEvent(Animator animator, double fraction) {
                int alpha = new Double(255 * fraction).intValue();
                setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), alpha));
                repaint();
            }
        }).build();
    }

    @Override
    public void setText(String s) {
        if (animator != null && !animator.isRunning()) {
            newLabelText = s;
            animator.start();
        } else {
            super.setText(s);
        }
    }
}