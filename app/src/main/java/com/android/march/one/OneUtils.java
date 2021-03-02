package com.android.march.one;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.march.one.model.bean.CastBean;
import com.android.march.one.model.bean.DirectorBean;

import java.lang.reflect.Field;
import java.util.List;

public class OneUtils {

    /**
     * 格式化导演名字
     */
    public static String formatDirectorName(List<DirectorBean> directorBeanList) {
        if (directorBeanList != null && directorBeanList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < directorBeanList.size(); i++) {
                if (i < directorBeanList.size() - 1) {
                    stringBuilder.append(directorBeanList.get(i).getName()).append(" / ");
                } else {
                    stringBuilder.append(directorBeanList.get(i).getName());
                }
            }
            return stringBuilder.toString();
        } else {
            return "佚名";
        }
    }

    /**
     * 格式化主演名字
     */
    public static String formatCastName(List<CastBean> castBeanList) {
        if (castBeanList != null && castBeanList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < castBeanList.size(); i++) {
                if (i < castBeanList.size() - 1) {
                    stringBuilder.append(castBeanList.get(i).getName()).append(" / ");
                } else {
                    stringBuilder.append(castBeanList.get(i).getName());
                }
            }
            return stringBuilder.toString();
        } else {
            return "佚名";
        }
    }

    /**
     * 格式化电影类型
     */
    public static String formatGenre(List<String> genreList) {
        if (genreList != null && genreList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < genreList.size(); i++) {
                if (i < genreList.size() - 1) {
                    stringBuilder.append(genreList.get(i)).append(" / ");
                } else {
                    stringBuilder.append(genreList.get(i));
                }
            }
            return stringBuilder.toString();
        } else {
            return "不知名类型";
        }
    }

    /**
     * 格式化制片国家/地区
     */
    public static String formatCountry(List<String> countryList) {
        if (countryList != null && countryList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < countryList.size(); i++) {
                if (i < countryList.size() - 1) {
                    stringBuilder.append(countryList.get(i)).append(" / ");
                } else {
                    stringBuilder.append(countryList.get(i));
                }
            }
            return stringBuilder.toString();
        } else {
            return "不知";
        }
    }

    public static void setBackground(View view, Bitmap bitmap) {
        Palette.from(bitmap).generate(palette -> {
            // 1.活力颜色
            Palette.Swatch a = palette.getVibrantSwatch();
            // 2.亮的活力颜色
            Palette.Swatch b = palette.getLightVibrantSwatch();
            // 3.暗的活力颜色
            Palette.Swatch c = palette.getDarkVibrantSwatch();
            // 4.柔色
            Palette.Swatch d = palette.getMutedSwatch();
            // 5.亮的柔色
            Palette.Swatch e = palette.getLightMutedSwatch();
            // 6.暗的柔色
            Palette.Swatch f = palette.getDarkMutedSwatch();

            if (f != null) {
                f.getRgb(); // rgb颜色
                f.getTitleTextColor();// 文本颜色
            }

            int one = 0;
            int two = 0;

            if (f != null) {
                one = f.getRgb();
            } else {
                if (d != null) {
                    one = d.getRgb();
                } else {
                    if (e != null) {
                        one = e.getRgb();
                    } else {
                        if (a != null) {
                            one = a.getRgb();
                        }
                    }
                }
            }

            if (b != null) {
                two = b.getRgb();
            } else {
                if (a != null) {
                    two = a.getRgb();
                } else {
                    if (c != null) {
                        two = c.getRgb();
                    } else {
                        if (d != null) {
                            two = d.getRgb();
                        }
                    }
                }
            }

            if (one == 0 || two == 0) return;
            view.setBackgroundDrawable(getDrawable(one, two));
        });
    }

    public static int getLightColor(Bitmap bitmap) {
        int color = 0;
        Palette palette = Palette.from(bitmap).generate();
        // 1.活力颜色
        Palette.Swatch a = palette.getVibrantSwatch();
        // 2.亮的活力颜色
        Palette.Swatch b = palette.getLightVibrantSwatch();
        // 3.暗的活力颜色
        Palette.Swatch c = palette.getDarkVibrantSwatch();
        // 4.柔色
        Palette.Swatch d = palette.getMutedSwatch();

        if (b != null) {
            color = b.getRgb();
        } else {
            if (a != null) {
                color = a.getRgb();
            } else {
                if (c != null) {
                    color = c.getRgb();
                } else {
                    if (d != null) {
                        color = d.getRgb();
                    }
                }
            }
        }
        return color;
    }

    public static int getDarkColor(Bitmap bitmap) {
        int color = 0;
        Palette palette = Palette.from(bitmap).generate();
        // 1.活力颜色
        Palette.Swatch a = palette.getVibrantSwatch();
        // 4.柔色
        Palette.Swatch d = palette.getMutedSwatch();
        // 5.亮的柔色
        Palette.Swatch e = palette.getLightMutedSwatch();
        // 6.暗的柔色
        Palette.Swatch f = palette.getDarkMutedSwatch();

        if (f != null) {
            color = f.getRgb();
        } else {
            if (d != null) {
                color = d.getRgb();
            } else {
                if (e != null) {
                    color = e.getRgb();
                } else {
                    if (a != null) {
                        color = a.getRgb();
                    }
                }
            }
        }
        return color;
    }

    public static void setTint(FloatingActionButton floatingActionButton, int color) {
        int[] colors = new int[]{color, color, color, color, color, color};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        floatingActionButton.setBackgroundTintList(new ColorStateList(states, colors));
    }

    private static Drawable getDrawable(int one, int two) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{one, two});
        // 设置形状
        drawable.setShape(GradientDrawable.RECTANGLE);
        // 设置渐变方式
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        return drawable;
    }

    public static void setTextImageSwitch(int maxLine, String data, TextView textView, ImageView imageView) {
        if (data.equals("")) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(data);
            textView.setHeight(textView.getLineHeight() * (textView.getLineCount() > maxLine ? maxLine : textView.getLineCount()));
            imageView.setVisibility(textView.getLineCount() > maxLine ? View.VISIBLE : View.GONE);
        }
    }

    public static void imageSwitch(Context context, int maxLine, Boolean isExpand, TextView textView, ImageView imageView) {
        // 清除动画效果
        imageView.clearAnimation();
        textView.clearAnimation();
        int differ;// 差值
        int startValue = textView.getHeight(); // 起始高度
        long durationMillis = 350L; // 动画持续时间
        if (isExpand) {
            // 折叠动画 --> 从实际高度缩回起始高度
            differ = textView.getLineHeight() * textView.getLineCount() - startValue;

            // xml 布局模式
            // Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.book_image_1);
            // 代码模式
            RotateAnimation animation = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);
        } else {
            // 展开动画 --> 从起始高度增长至实际高度
            differ = textView.getLineHeight() * maxLine - startValue;

            // Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.book_image_2);
            RotateAnimation animation = new RotateAnimation(-180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(durationMillis);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);
        }

        Animation animation = new Animation() {

            // 根据ImageView旋转动画的百分比来显示TextView高度,达到动画效果
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                textView.setHeight((int) (startValue + differ * interpolatedTime));
            }
        };
        animation.setDuration(durationMillis);
        textView.startAnimation(animation);
    }

    /**
     * 复制文字到剪切板
     *
     * @param context  上下文
     * @param copyText 要复制的文本
     * @return
     */
    public static boolean copyText(Context context, String copyText) {
        // 获取剪切板管理区
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建能够存入剪贴板的ClipData数据
        // ClipData data = ClipData.newPlainText("Label", "Content");
        ClipData data = ClipData.newRawUri("Url", Uri.parse(copyText));
        // 将ClipData数据复制到剪贴板
        cmb.setPrimaryClip(data);
        return true;
    }

    /**
     * 使用浏览器打开
     *
     * @param context 上下文
     * @param url     地址
     */
    public static void openWithBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void setRecyclerView(Context context, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnScrollListener(new RecyclerViewScrollListener(context));
    }

    /**
     * 改变 EditText 游标的颜色
     */
    public static void setCursorTint(@NonNull EditText editText, @ColorInt int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[2];
            drawables[0] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[1] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}