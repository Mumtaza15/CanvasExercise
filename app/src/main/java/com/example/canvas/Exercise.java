package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Exercise extends AppCompatActivity {

    private Canvas canvas;
    private Paint paint = new Paint();
    private Bitmap bitmap;
    private ImageView imageView;

    private int frameCount = 0;

    private int colorBlack;
    private int colorWhite;
    private int colorGreen400;
    private int colorGreen500;
    private int colorGreen600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // color
        colorBlack = ResourcesCompat.getColor(getResources(),
                R.color.black, null);
        colorWhite = ResourcesCompat.getColor(getResources(),
                R.color.white, null);
        colorGreen400 = ResourcesCompat.getColor(getResources(),
                R.color.purple_200, null);
        colorGreen500 = ResourcesCompat.getColor(getResources(),
                R.color.purple_500, null);
        colorGreen600 = ResourcesCompat.getColor(getResources(),
                R.color.purple_700, null);

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

        // face coordinate
        Point faceA = new Point(halfWidth-240, halfHeight-180);
        Point faceB = new Point(halfWidth+240, halfHeight-180);
        Point faceC = new Point(halfWidth, halfHeight+320);

        switch (frameCount) {
            case 0:
                // init canvas
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                imageView.setImageBitmap(bitmap);
                canvas = new Canvas(bitmap);

                // MOUSE FACE
                // make triangle path from coordinate
//                Path path = new Path();
//                path.setFillType(Path.FillType.EVEN_ODD);
//                path.lineTo(faceA.x, faceA.y);
//                path.lineTo(faceB.x, faceB.y);
//                path.lineTo(faceC.x, faceC.y);
//                path.lineTo(faceA.x, faceA.y);
//                path.close();

                paint.setColor(colorBlack);
                canvas.save(); // Menyimpan kondisi sebelum melakukan transformasi
                canvas.rotate(90, 200, 200); // Memutar canvas sebesar 90 derajat pada titik (200, 200)
                canvas.scale(2, 1); // Mengubah skala pada sumbu X
                //ingat ini di balik 90 derajat
                canvas.drawOval(200, -600, 800, 320, paint);
                canvas.restore(); // Mengembalikan kondisi sebelum transformasi

//                paint.setColor(colorGreen400);
//                canvas.save(); // Menyimpan kondisi sebelum melakukan transformasi
//                canvas.rotate(-30, 200, 200); // Memutar canvas sebesar 90 derajat pada titik (200, 200)
//                canvas.scale(1, 1); // Mengubah skala pada sumbu X
//
//                //left: pengaturan ke kiri
//                //top: pengaturan ke atas
//                //right: pengaturan ke kanan
//                //bottom: poengaturan ke bawah
//                canvas.drawOval(-200, 1000, faceA.x, 100, paint);//dikurangin malah nambah, nambah malah ngurang bjir
//                canvas.restore(); // Mengembalikan kondisi sebelum transformasi

                Point leftFoot = new Point(faceA.x+170, faceA.y+545);
                Point rightFoot = new Point(faceB.x-170, faceB.y+545);

//                paint.setColor(colorWhite);
//                canvas.drawCircle(leftFoot.x, leftFoot.y, 58, paint);
//                canvas.drawCircle(rightFoot.x, rightFoot.y, 58, paint);

                // EYEBALL
                paint.setColor(colorBlack);
                canvas.save();
//                canvas.rotate(-30, 400, 0);
                canvas.drawOval(new RectF(leftFoot.x-150, leftFoot.y-100,
                        leftFoot.x+20, leftFoot.y+320), paint);
                canvas.restore();

                canvas.drawOval(new RectF(rightFoot.x-20, rightFoot.y-100,
                        rightFoot.x+150, rightFoot.y+320), paint);
//                canvas.rotate(-30, 200, 200);


//                paint.setColor(colorWhite);
//                canvas.drawCircle(leftFoot.x-2, leftFoot.y+12, 11, paint);
//                canvas.drawCircle(rightFoot.x+2, rightFoot.y+12, 11, paint);
                break;


//                // draw face to canvas
//                paint.setColor(colorGreen500);
//                canvas.drawPath(path, paint);

//                // Menggambar lingkaran lonjong ke bawah
//                canvas.save(); // Menyimpan kondisi sebelum melakukan transformasi
//                canvas.rotate(90, 200, 200); // Memutar canvas sebesar 90 derajat pada titik (200, 200)
//                canvas.scale(4, 1); // Mengubah skala pada sumbu X
//                canvas.drawOval(100, 100, 600, 600, paint);
//                canvas.restore(); // Mengembalikan kondisi sebelum transformasi

//                break;
            case 1:
                // EYE
                Point leftEye = new Point(faceA.x+170, faceA.y-60);
                Point rightEye = new Point(faceB.x-170, faceB.y-60);

                paint.setColor(colorWhite);
                canvas.drawCircle(leftEye.x, leftEye.y, 58, paint);
                canvas.drawCircle(rightEye.x, rightEye.y, 58, paint);

                // EYEBALL
                paint.setColor(colorBlack);
                canvas.drawOval(new RectF(leftEye.x-20, leftEye.y-6,
                        leftEye.x+20, leftEye.y+42), paint);
                canvas.drawOval(new RectF(rightEye.x-20, rightEye.y-6,
                        rightEye.x+20, rightEye.y+42), paint);

                paint.setColor(colorWhite);
                canvas.drawCircle(leftEye.x-2, leftEye.y+12, 11, paint);
                canvas.drawCircle(rightEye.x+2, rightEye.y+12, 11, paint);
                break;
        }

        frameCount++;
        view.invalidate();
    }
}