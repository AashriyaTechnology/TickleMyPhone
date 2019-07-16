package com.jalsalabs.ticklemyphonefull;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class LongSummaryCheckBoxPreference extends CheckBoxPreference {
    public LongSummaryCheckBoxPreference(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }

    public LongSummaryCheckBoxPreference(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    /* Access modifiers changed, original: protected */
    public void onBindView(View view) {
        super.onBindView(view);
        TextView summary = (TextView) view.findViewById(16908304);
        summary.setMaxLines(5);
        summary.setTextSize(12.0f);
        ((TextView) view.findViewById(16908310)).setTextSize(16.0f);
    }
}
