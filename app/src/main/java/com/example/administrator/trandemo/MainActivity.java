package com.example.administrator.trandemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mButton1;
    Button mButton2;
    Button mButton3;
    private ObjectAnimator mTranslationY;
    private ObjectAnimator mTranslationY2;
    private ObjectAnimator mTranslationY3;
    private ObjectAnimator mTranslationY4;
    private ObjectAnimator mAlpha;
    private ObjectAnimator mAlpha2;
    private ObjectAnimator mAlpha3;
    private ObjectAnimator mAlpha4;
    private AnimatorSet mAnimatorSet;
    private AnimatorSet mAnimatorSet2;
    private static int current = 1;
    private static boolean isExpan = false;
    private static boolean isPlay = false;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.b1);
        mButton2 = (Button) findViewById(R.id.b2);
        mButton3 = (Button) findViewById(R.id.b3);
        mButton3.setAlpha(0f);
        mButton2.setAlpha(0f);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mTranslationY = ObjectAnimator.ofFloat(mButton2, "TranslationY", 0f, -200);
        mTranslationY2 = ObjectAnimator.ofFloat(mButton3, "TranslationY", 0f, 200);
        mTranslationY3 = ObjectAnimator.ofFloat(mButton2, "TranslationY", -200, 0f);
        mTranslationY4 = ObjectAnimator.ofFloat(mButton3, "TranslationY", 200, 0f);
        mAlpha = ObjectAnimator.ofFloat(mButton2, "alpha", 0f, 1f);
        mAlpha3 = ObjectAnimator.ofFloat(mButton2, "alpha", 1f, 0f);
        mAlpha2 = ObjectAnimator.ofFloat(mButton3, "alpha", 0f, 1f);
        mAlpha4 = ObjectAnimator.ofFloat(mButton3, "alpha", 1f, 0f);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet2 = new AnimatorSet();
        mAnimatorSet.setDuration(500);
        mAnimatorSet2.setDuration(500);
        mAnimatorSet.setInterpolator(new AnticipateOvershootInterpolator());
        mAnimatorSet2.setInterpolator(new AnticipateOvershootInterpolator());
        mAnimatorSet.play(mTranslationY).with(mAlpha).with(mAlpha2).with(mTranslationY2);
        mAnimatorSet2.play(mTranslationY3).with(mAlpha3).with(mAlpha4).with(mTranslationY4);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isPlay = true;
                MainActivity.this.setBtnEnable(!isPlay);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isPlay = false;
                MainActivity.this.setBtnEnable(!isPlay);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAnimatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isPlay = true;
                MainActivity.this.setBtnEnable(!isPlay);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isPlay = false;
                MainActivity.this.setBtnEnable(!isPlay);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.b1:
                // 动态
                if (!isExpan) {

                    mAnimatorSet.start();
                    isExpan = true;
                } else {

                    mAnimatorSet2.start();
                    isExpan = false;
                }
                break;


            case R.id.b2:
                if (current == 1) {
                    mButton1.setText("车管家");
                    mButton2.setText("动态");
                    mButton3.setText("车生活");
                    current = 2;
                    switchTab(1, 2);
                } else if (current == 2) {
                    mButton1.setText("动态");
                    mButton2.setText("车管家");
                    mButton3.setText("车生活");
                    current = 1;
                    switchTab(2, 1);
                } else if (current == 3) {
                    mButton1.setText("车管家");
                    mButton3.setText("车生活");
                    mButton2.setText("动态");
                    current = 2;
                    switchTab(3, 2);
                }
                if (!isExpan) {
                    mAnimatorSet.start();
                    isExpan = true;
                } else {
                    mAnimatorSet2.start();
                    isExpan = false;
                }
                break;

            case R.id.b3:
                if (current == 1) {
                    mButton1.setText("车生活");
                    mButton2.setText("车管家");
                    mButton3.setText("动态");
                    current = 3;
                    switchTab(1, 3);
                } else if (current == 2) {
                    mButton1.setText("车生活");
                    mButton2.setText("车管家");
                    mButton3.setText("动态");
                    current = 3;
                    switchTab(2, 3);
                } else if (current == 3) {
                    mButton1.setText("动态");
                    mButton2.setText("车管家");
                    mButton3.setText("车生活");
                    current = 1;
                    switchTab(3, 1);
                }
                if (!isExpan) {
                    mAnimatorSet.start();
                    isExpan = true;
                } else {
                    mAnimatorSet2.start();
                    isExpan = false;
                }
                break;
        }
    }


    public void setBtnEnable(boolean isEnable) {
        if (isEnable) {
            mButton1.setEnabled(true);
            mButton2.setEnabled(true);
            mButton3.setEnabled(true);
        } else {
            mButton1.setEnabled(false);
            mButton2.setEnabled(false);
            mButton3.setEnabled(false);
        }

    }

    public void switchTab(int last, int tag) {
        String lasStr = null;
        if (last == 1) {
            lasStr = "动态";
        } else if (last == 2) {
            lasStr = "车管家";
        } else {
            lasStr = "车生活";
        }
        switch (tag) {
            case 1:

                Log.d(TAG, lasStr + "--> 动态");
                break;
            case 2:
                Log.d(TAG, lasStr + "--> 车管家");
                break;
            case 3:
                Log.d(TAG, lasStr + "--> 车生活");
                break;
        }
    }
}
