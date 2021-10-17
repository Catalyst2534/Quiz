package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.logging.Level;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        //задаем номер уровня
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        //скругления
        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);
        //Пути к текст вью
        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);
        //полноэкранный режим
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//убираем заголовок
        dialog.setContentView(R.layout.previewdialog);//путь
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialog.setCancelable(false);//окно не закрывается кнопкой назад

        //кнопка закрытия
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                }
                dialog.dismiss();// закрытие диалогового окна
            }
        });
        //кнопка продолжить
        Button btncont = (Button)dialog.findViewById(R.id.btncont);
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();//отображение окна
//__________________________________________________________________финальное диалоговое окно
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//убираем заголовок
        dialogEnd.setContentView(R.layout.dialogend2);//путь
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//окно не закрывается кнопкой назад

        //кнопка закрытия
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                }
                dialogEnd.dismiss();// закрытие диалогового окна
            }
        });
        //кнопка продолжить
        Button btncont2 = (Button)dialogEnd.findViewById(R.id.btncont);
        btncont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){}

                dialogEnd.dismiss();
            }
        });



        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){}
                }
            });
        final int [] progress = {
            R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,R.id.point6,R.id.point7,
            R.id.point8,R.id.point9,R.id.point10,R.id.point11,R.id.point12,R.id.point13,R.id.point14,
            R.id.point15,R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,
        };
        //анимация
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        numLeft = random.nextInt(10);
        numRight = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);
        text_left.setText(array.texts1[numLeft]);
        while (numRight==numLeft){
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);

        //нажатие на левую кнопку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }
                    else img_left.setImageResource(R.drawable.img_false);
                }
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        if (numLeft>numRight){
                            if (count<20){
                                count+=1;
                            }
                            for (int i = 0; i<20; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }

                            for (int i = 0; i<count; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }

                        }else{
                            if (count>0){
                                if (count==1){
                                    count = 0;
                                }
                                else {
                                    count-=2;
                                }
                            }
                            for (int i = 0; i<19; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }

                            for (int i = 0; i<count; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        }
                        if (count == 20){
                            dialogEnd.show();//отображение окна
                        }else{
                            numLeft = random.nextInt(10);
                            numRight = random.nextInt(10);
                            img_left.setImageResource(array.images1[numLeft]);
                            img_left.startAnimation(a);
                            text_left.setText(array.texts1[numLeft]);
                            while (numRight==numLeft){
                                numRight = random.nextInt(10);
                            }
                            img_right.setImageResource(array.images1[numRight]);
                            img_right.startAnimation(a);
                            text_right.setText(array.texts1[numRight]);
                            img_right.setEnabled(true);
                        }
                    }

                return true;
            }
        });

        /* if (numRight != numLeft) {
            img_left.setImageResource(array.images1[numLeft]);
            text_left.setText(array.texts1[numLeft]);
            img_right.setImageResource(array.images1[numRight]);
            text_right.setText(array.texts1[numRight]);
        } else{
            numLeft = random.nextInt(10);
            numRight = random.nextInt(10);
        }*/
        //нажатие на правую кнопку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if (numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }
                    else img_right.setImageResource(R.drawable.img_false);
                }
                else if (event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeft<numRight){
                        if (count<20){
                            count+=1;
                        }
                        for (int i = 0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else{
                        if (count>0){
                            if (count==1){
                                count = 0;
                            }
                            else {
                                count-=2;
                            }
                        }
                        for (int i = 0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 20){
                        dialogEnd.show();//отображение окна
                    }else{
                        numLeft = random.nextInt(10);
                        numRight = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);
                        while (numRight==numLeft){
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);
                        img_left.setEnabled(true);
                    }
                }

                return true;
            }
        });
        }
        // системная кнопка назад
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){}
    }

}

