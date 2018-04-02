package com.squareup.picasso3;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

public class Target2<T> {
  T target;
  final Drawable errorDrawable;
  final int errorResId;
  final boolean noFade;

  Target2(@NonNull T target) {
    this.target = target;
    this.errorResId = 0;
    this.errorDrawable = null;
    this.noFade = false;
  }

  Target2(@NonNull T target, @DrawableRes int errorResId) {
    this.target = target;
    this.errorResId = errorResId;
    this.errorDrawable = null;
    this.noFade = false;
  }

  Target2(@NonNull T target, Drawable errorDrawable) {
    this.target = target;
    this.errorResId = 0;
    this.errorDrawable = errorDrawable;
    this.noFade = false;
  }

  Target2(@NonNull T target, @DrawableRes int errorResId, Drawable errorDrawable, boolean noFade) {
    this.target = target;
    this.errorResId = errorResId;
    this.errorDrawable = errorDrawable;
    this.noFade = noFade;
  }
}
