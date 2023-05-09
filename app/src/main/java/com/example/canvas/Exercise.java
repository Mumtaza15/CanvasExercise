package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;

public class Exercise extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private ImageView imageView;

    private int frameCount = 0;

    private int colorBlack;
    private int colorWhite;
    private int colorYellow;
    private int colorSemiBlack;
    private int colorBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // color
        colorBlack = ResourcesCompat.getColor(getResources(),
                R.color.black, null);
        colorWhite = ResourcesCompat.getColor(getResources(),
                R.color.white, null);
        colorYellow = ResourcesCompat.getColor(getResources(),
                R.color.kuning, null);
        colorSemiBlack = ResourcesCompat.getColor(getResources(),
                R.color.hitamDikit, null);
        colorBlue = ResourcesCompat.getColor(getResources(),
                R.color.biru, null);

        imageView = findViewById(R.id.my_image_view);
        imageView.setOnClickListener(view -> {
            drawSomething(view);
        });
    }

    private void drawSomething(View view) {
        int width = view.getWidth();
        int halfWidth = width/2;

        int height = view.getHeight();
        int halfHeight = height/2;

        // coordinate
        Point faceA = new Point(halfWidth-240, halfHeight-180);
        Point faceB = new Point(halfWidth+240, halfHeight-180);
        Point faceC = new Point(halfWidth, halfHeight+320);

        Point leftFoot = new Point(faceA.x+170, faceA.y+545);
        Point rightFoot = new Point(faceB.x-170, faceB.y+545);

        switch (frameCount) {
            case 0:
                // init mCanvas
                mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                imageView.setImageBitmap(mBitmap);
                mCanvas = new Canvas(mBitmap);

//                draw foot
                mPaint.setColor(colorYellow);
                mCanvas.save();
//                canvas.rotate(-30, 400, 0);
                mCanvas.drawOval(new RectF(leftFoot.x-150, leftFoot.y-100,
                        leftFoot.x+20, leftFoot.y+320), mPaint);
                mCanvas.restore();

                mCanvas.save();
                mCanvas.drawOval(new RectF(rightFoot.x-20, rightFoot.y-100,
                        rightFoot.x+150, rightFoot.y+320), mPaint);
//                canvas.rotate(-30, 200, 200);
                mCanvas.restore();
                break;

            case 1:
                //draw tubuh
                mPaint.setColor(colorBlack);
                mCanvas.save(); // Menyimpan kondisi sebelum melakukan transformasi
                mCanvas.rotate(90, 200, 200); // Memutar canvas sebesar 90 derajat pada titik (200, 200)
                mCanvas.scale(2, 1); // Mengubah skala pada sumbu X
                //ingat ini di balik 90 derajat
                mCanvas.drawOval(200, -600, 800, 320, mPaint);
                mCanvas.restore(); // Mengembalikan kondisi sebelum transformasi

//                //left: pengaturan ke kiri
//                //top: pengaturan ke atas
//                //right: pengaturan ke kanan
//                //bottom: poengaturan ke bawah
//                canvas.drawOval(-200, 1000, faceA.x, 100, paint);//dikurangin malah nambah, nambah malah ngurang bjir
//                canvas.restore(); // Mengembalikan kondisi sebelum transformasi
                break;

            case 2:
                // EYE
                Point leftEye = new Point(faceA.x+100, faceA.y-80);
                Point rightEye = new Point(faceB.x-100, faceB.y-80);

                mPaint.setColor(colorWhite);
                mCanvas.drawCircle(leftEye.x, leftEye.y, 80, mPaint);
                mCanvas.drawCircle(rightEye.x, rightEye.y, 80, mPaint);

                // EYEBALL
                mPaint.setColor(colorBlack);
                mCanvas.drawOval(new RectF(leftEye.x-40, leftEye.y-40,
                        leftEye.x+40, leftEye.y+42), mPaint);
                mCanvas.drawOval(new RectF(rightEye.x-40, rightEye.y-40,
                        rightEye.x+40, rightEye.y+42), mPaint);

                mPaint.setColor(colorWhite);

                mCanvas.drawCircle(leftEye.x-2, leftEye.y-10, 20, mPaint);
                mCanvas.drawCircle(rightEye.x+2, rightEye.y-10, 20, mPaint);
                break;
            case 3:
                //draw perut
                mPaint.setColor(colorWhite);
                mCanvas.drawCircle(faceC.x, faceC.y-100, 340, mPaint);
                break;
            case 4:
                //cucuk
                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(faceA.x/3+360, faceA.y/4+600);
                path.lineTo(faceB.x/3+360, faceB.y/4+600);
                path.lineTo(faceC.x/3+360, faceC.y/4+600);
                path.lineTo(faceA.x/3+360, faceA.y/4+600);
                path.close();

                mPaint.setColor(colorYellow);
                mCanvas.drawPath(path, mPaint);
                break;
            case 5:
                //draw hand
                mPaint.setColor(colorSemiBlack);
                mCanvas.save();
                mCanvas.drawOval(new RectF(leftFoot.x-300, leftFoot.y-400,
                        leftFoot.x-140, leftFoot.y+60), mPaint);
                mCanvas.restore();

                mCanvas.save();
                mCanvas.drawOval(new RectF(rightFoot.x+140, rightFoot.y-400,
                        rightFoot.x+300, rightFoot.y+60), mPaint);
                mCanvas.restore();
                break;
            case 6:
                String text = "\n\nJawabannya adalah hewan favoritnya ryangga\n=SKIPPER \uD83D\uDE0E";

                TextPaint textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
                textPaint.setColor(0xFF000000);

                int widthz = (int) textPaint.measureText(text);
                StaticLayout staticLayout = new StaticLayout(text, textPaint, (int) widthz, Layout.Alignment.ALIGN_CENTER, 1.0f, 0, false);
                staticLayout.draw(mCanvas);
                break;

            case 7:
                getWindow().getDecorView().setBackgroundColor(colorBlue);
                break;
            default:
                break;
        }

        frameCount++;
        view.invalidate();
    }
}