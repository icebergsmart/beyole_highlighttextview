package com.beyole.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.TextView;

public class HightLightTextView extends TextView {

	// �洢view�Ŀ��
	private int mTextViewWidth = 0;
	// ����
	private Paint mPaint;
	// ������Ⱦ
	private LinearGradient mLinearGradient;
	// �洢�任��matrix
	private Matrix matrix;
	// �ƶ�����
	private int mTranslateX = 0;
	// �Ƿ�������
	private boolean isAnimateOn = true;

	// ���췽��
	public HightLightTextView(Context context) {
		this(context, null);
	}

	public HightLightTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HightLightTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * view�ĵ��ù���:���췽��->onFinishInflate->onSizeChanged->onDraw
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// ��ȡview�Ŀ�ȣ���ʼ�����ʵȳ�ʼ����
		if (mTextViewWidth == 0) {
			mTextViewWidth = getMeasuredWidth();
			// �����ȴ���0�Ļ������ʼ��
			if (mTextViewWidth > 0) {
				// ��ʼ������
				mPaint = getPaint();
				// ������Ⱦ
				mLinearGradient = new LinearGradient(-mTextViewWidth, 0, 0, 0, new int[] { 0X55FFFFFF, 0XFFFFFFFF, 0X55FFFFFF }, new float[] { 0, 0.5f, 1 }, TileMode.CLAMP);
				mPaint.setShader(mLinearGradient);
				matrix = new Matrix();
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isAnimateOn && matrix != null) {
			mTranslateX += mTextViewWidth / 10;
			// ����ƶ��ľ�����������Ŀ�ȣ������¿�ʼ�ƶ�
			if (mTranslateX > 2 * mTextViewWidth) {
				mTranslateX = -mTextViewWidth;
			}
			// ƽ��matrix
			matrix.setTranslate(mTranslateX, 0);
			// �������Ա仯��matrix
			mLinearGradient.setLocalMatrix(matrix);
			// �ӳ�100ms�ػ�
			postInvalidateDelayed(100);
		}
	}

}
