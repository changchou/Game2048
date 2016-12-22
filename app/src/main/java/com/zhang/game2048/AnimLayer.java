package com.zhang.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Z on 2016/10/13 0013.
 */

public class AnimLayer extends FrameLayout {

    public AnimLayer(Context context) {
        super(context);
    }

    public AnimLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void createScaleTo1(Card card) {
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(100);
        card.setAnimation(null);
        card.getLabel().startAnimation(sa);

    }

    public void createMoveAnim(final Card fromC, final Card toC, int fromX, int toX, int fromY, int toY) {
        final Card card = getCard(fromC.getNum());

        LayoutParams lp = new LayoutParams(GameView.CARD_WIDTH, GameView.CARD_WIDTH);
        lp.leftMargin = fromX * GameView.CARD_WIDTH;
        lp.topMargin = fromY * GameView.CARD_WIDTH;
        card.setLayoutParams(lp);

        if (toC.getNum() <= 0) {
            toC.getLabel().setVisibility(INVISIBLE);
        }

        TranslateAnimation ta = new TranslateAnimation(0, GameView.CARD_WIDTH * (toX - fromX),
                0, GameView.CARD_WIDTH * (toY - fromY));
        ta.setDuration(100);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toC.getLabel().setVisibility(VISIBLE);
                recycleCard(card);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        card.startAnimation(ta);

    }

    private Card getCard(int num) {
        Card card;
        if (cards.size() > 0) {
            card = cards.remove(0);
        } else {
            card = new Card(getContext());
            addView(card);
        }
        card.setVisibility(VISIBLE);
        card.setNum(num);
        return card;
    }

    private void recycleCard(Card card) {
        card.setVisibility(INVISIBLE);
        card.setAnimation(null);
        cards.add(card);
    }

    private List<Card> cards = new ArrayList<>();
}
