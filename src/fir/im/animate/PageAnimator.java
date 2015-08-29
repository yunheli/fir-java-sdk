/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package fir.im.animate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import fir.im.dialog.FirDialog;
import fir.im.utils.FunctionalUtil;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.Interpolator;
import org.jdesktop.core.animation.timing.TimingTarget;

public abstract class PageAnimator implements TimingTarget {
    public static final int DEFAULT_TRANSITION_DURATION_MS = 350;
    public static final int DEFAULT_TRANSITION_START_DELAY_MS = 25;
    private static final TimingTarget[] EMPTY_TIMING_TARGET_LIST = new TimingTarget[0];
    protected final PageAnimationCompleteCallback _callback;
    private FirDialog _appWindow;
    private JPanel _oldPage;
    private JPanel _newPage;
    private Direction _direction = Direction.NORMAL;



    protected PageAnimator(PageAnimationCompleteCallback callback) {
        this._callback = callback;
    }

    public void animate(FirDialog appWindow, JPanel first, JPanel second,
                        Direction direction) {
        configure(appWindow, first, second, direction);
        appWindow.add(second);
        postAnimator(this.TWO_PAGE_TRANSITION_PROVIDER);
    }

    public void animate(FirDialog appWindow, JPanel page, Direction direction) {
        configure(appWindow, page, null, direction);
        if (!(page.isValid())) {
            FirDialog.add(page);
        }
        postAnimator(this.SINGLE_PAGE_TRANSITION_PROVIDER);
    }

    protected void configure(FirDialog appWindow, JPanel oldPage,
                             JPanel newPage, Direction direction) {
        this._appWindow = appWindow;
        this._oldPage = oldPage;
        this._newPage = newPage;
        this._direction = direction;
    }

    protected void postAnimator(
            FunctionalUtil.Provider<TimingTarget[], UnsupportedPageAnimationException> timingTargetProvider) {
        if (this._newPage == null) {
            startOrDelayAnimator(timingTargetProvider);
        } else
            this._newPage.postAnimation(this, timingTargetProvider);
    }

    protected void startAnimator(
            FunctionalUtil.Provider<TimingTarget[], UnsupportedPageAnimationException> timingTargetProvider) {
        SwingUtilities.invokeLater(new Runnable(timingTargetProvider) {
            public void run() {
                try {
                    Animator animator = new Animator.Builder()
                            .addTarget(PageAnimator.this)
                            .addTargets(
                                    (TimingTarget[]) this.val$timingTargetProvider
                                            .get())
                            .setDuration(
                                    PageAnimator.this.getTransitionDuration(),
                                    TimeUnit.MILLISECONDS)
                            .setInterpolator(
                                    PageAnimator.this.getInterpolator())
                            .build();

                    if (PageAnimator.this._direction == PageAnimator.Direction.NORMAL)
                        animator.start();
                    else
                        animator.startReverse();
                } catch (PageAnimator.UnsupportedPageAnimationException e) {
                    PageAnimator.this.cleanUp();
                    PageAnimator.this._callback.onAnimationFinished(false);
                }
            }
        });
    }

    public void startOrDelayAnimator(
            FunctionalUtil.Provider<TimingTarget[], UnsupportedPageAnimationException> timingTargetProvider) {
        int delay = getTransitionStartDelay();
        if (delay > 0) {
            Timer timer = new Timer(delay, new ActionListener(
                    timingTargetProvider) {
                public void actionPerformed(ActionEvent te) {
                    PageAnimator.this
                            .startAnimator(this.val$timingTargetProvider);
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            startAnimator(timingTargetProvider);
        }
    }

    protected void cleanUp() {
        this._appWindow = null;
        this._oldPage = null;
        this._newPage = null;
    }

    protected int getTransitionDuration() {
        return 350;
    }

    protected int getTransitionStartDelay() {
        return 25;
    }

    protected Interpolator getInterpolator() {
        return null;
    }

    protected Direction getDirection() {
        return this._direction;
    }

    protected abstract TimingTarget[] getTimingTargetsForSinglePageTransition(
            AppPage paramAppPage)
            throws PageAnimator.UnsupportedPageAnimationException;

    protected abstract TimingTarget[] getTimingTargetsForTwoPageTransition(
            AppPage paramAppPage1, AppPage paramAppPage2)
            throws PageAnimator.UnsupportedPageAnimationException;

    public void begin(Animator source) {
    }

    public void end(Animator source) {
        if ((this._oldPage != null) && (this._newPage != null)) {
            if (this._oldPage.getParent() != null) {
                this._oldPage.setVisible(false);
                this._appWindow.getDialog().remove(this._oldPage);
            }
            this._appWindow.pageChanged(this._newPage);
        }
        cleanUp();
        this._callback.onAnimationFinished(true);
    }

    public void repeat(Animator source) {
    }

    public void reverse(Animator source) {
    }

    public void timingEvent(Animator source, double fraction) {
    }

    public AppPage getPreviousPage() {
        return this._oldPage;
    }

    public AppPage getNextPage() {
        return this._newPage;
    }

    public AppWindow getAppWindow() {
        return this._appWindow;
    }

    protected TimingTarget[] safeList(TimingTarget[] targets) {
        return ((targets == null) ? EMPTY_TIMING_TARGET_LIST : targets);
    }

    public static final int getDefaultTotalPageAnimationTime() {
        return 375;
    }

    public static abstract interface AppPageMasquerade {
        public abstract boolean getTransformChildrenOnly();

        public abstract BufferedImage computeTransformedImage();
    }

    public static class UnsupportedPageAnimationException extends Exception {
        private static final long serialVersionUID = 979872042736093517L;

        public UnsupportedPageAnimationException() {
        }

        public UnsupportedPageAnimationException(String string) {
            super(string);
        }
    }

    public static abstract interface PageAnimationCompleteCallback {
        public abstract void onAnimationFinished(boolean paramBoolean);
    }

    public static enum Direction {
        NORMAL, REVERSED;
    }

    public static enum Type {
        NONE, CROSS_FADE, SLIDE, FLIP, PEEL, SHAKE;
    }
}