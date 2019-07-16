package com.jalsalabs.ticklemyphonefull;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;

public class TML_LangLib {
    private static String[][] Label;

    static {
        r0 = new String[7][];
        r0[0] = new String[]{"MANAGE_RULES", "ಕನ�?ನಡ"};
        r0[1] = new String[]{"ACTIVATE", "ನಮ�?ಮ ಮನೆಯಲ�?ಲಿ"};
        r0[2] = new String[]{"MANAGE_SENDER", "SMS ಕಳ�?ಹಿಸಿದವರ ನಿರ�?ವಹಿಸಿ"};
        r0[3] = new String[]{"LOG_DETAILS", "ಲಾಗ�? ವಿವರಗಳ�?"};
        r0[4] = new String[]{"HELP", "ಸಹಾಯ"};
        r0[5] = new String[]{"ABOUT", "ನಮ�?ಮ ಬಗ�?ಗೆ"};
        r0[6] = new String[]{"EXIT", "ನಿರ�?ಗಮನ"};
        Label = r0;
    }

    public static String GetLabel(String StringCode, String Language) {
        int column = 1;
        if (Language.equals("KANNADA")) {
            column = 1;
        }
        for (int row = 0; row < Label.length; row++) {
            if (Label[row][0].equals(StringCode)) {
                return Label[row][column];
            }
        }
        return "Undefined";
    }

    public static boolean isNonLocale() {
        return false;
    }

    public static void SetNonLocForButton(Context context, String code, Button bt) {
        bt.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/sampige.ttf"));
        bt.setText(GetLabel(code, "KANNADA"));
    }
}
