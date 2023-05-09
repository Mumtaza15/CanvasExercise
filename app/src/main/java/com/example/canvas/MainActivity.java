package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPainText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mimageView;

    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    //Batasan mau gambar dimana, colornya apa & sampai kapan
    private static final int OFFSET = 40;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackground;
    private int mColorRectangle;
    private int mColorCircle;
    private int mColorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRectangle= ResourcesCompat.getColor(getResources(), R.color.colorRectangle, null);
        mColorCircle = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mColorText = ResourcesCompat.getColor(getResources(), R.color.black, null);

        mPaint.setColor(mColorBackground);
        mPainText.setColor(mColorText);
        mPainText.setTextSize(70);


        mimageView = findViewById(R.id.my_image_view);
        mimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view);
            }
        });
    }

    private void drawSomething(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();

        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mimageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);

            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPainText);
            mOffset += OFFSET;
        }else {
            if (mOffset < halfWidth && mOffset < halfHeight){
                mPaint.setColor(mColorRectangle - MULTIPLIER*mOffset);
                mRect.set(mOffset, mOffset, vWidth-mOffset, vHeight-mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;
            }
            else{
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mCanvas.drawCircle(halfWidth,halfHeight,halfWidth/3, mPaint);
                String text = getString(R.string.done);

                mPainText.getTextBounds(text,0,text.length(),mBounds);
                //Tulisan text biar di tengah lingkaran
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPainText);
                mOffset += OFFSET;

                mPaint.setColor(mColorBackground - MULTIPLIER*OFFSET);
                Point a = new Point(halfWidth-50,halfHeight-50);
                Point b = new Point(halfWidth+50,halfHeight-50);
                Point c = new Point(halfWidth,halfHeight+250);

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(a.x,a.y);
                path.lineTo(b.x,b.y);
                path.lineTo(c.x,c.y);
                path.lineTo(a.x,a.y);
                path.close();

                mCanvas.drawPath(path, mPaint);
                mOffset += OFFSET;
            }

        }

        view.invalidate();

    }
}

//// Menggambar lingkaran lonjong
//            canvas.save(); // Menyimpan kondisi sebelum melakukan transformasi
//                    canvas.scale(2, 1); // Mengubah skala pada sumbu X
//                    canvas.drawOval(100, 100, 300, 200, paint);
//                    canvas.restore(); // Mengembalikan kondisi sebelum transformasi