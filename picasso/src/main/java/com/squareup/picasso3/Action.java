/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.picasso3;

import android.graphics.drawable.Drawable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static com.squareup.picasso3.Picasso.Priority;

abstract class Action<T> {
  static class RequestWeakReference<M> extends WeakReference<M> {
    final Action action;

    RequestWeakReference(Action action, M referent, ReferenceQueue<? super M> q) {
      super(referent, q);
      this.action = action;
    }
  }

  final Picasso picasso;
  final Request request;
  final WeakReference<T> target;
  final boolean noFade;
  final int errorResId;
  final Drawable errorDrawable;

  boolean willReplay;
  boolean cancelled;

  Action(Picasso picasso, T target, Request request, int errorResId, Drawable errorDrawable,
      boolean noFade) {
    this.picasso = picasso;
    this.request = request;
    this.target =
        target == null ? null : new RequestWeakReference<>(this, target, picasso.referenceQueue);
    this.noFade = noFade;
    this.errorResId = errorResId;
    this.errorDrawable = errorDrawable;
  }

  abstract void complete(RequestHandler.Result result);

  abstract void error(Exception e);

  void cancel() {
    cancelled = true;
  }

  Request getRequest() {
    return request;
  }

  T getTarget() {
    return target == null ? null : target.get();
  }

  String getKey() {
    return request.key;
  }

  boolean isCancelled() {
    return cancelled;
  }

  boolean willReplay() {
    return willReplay;
  }

  Picasso getPicasso() {
    return picasso;
  }

  Priority getPriority() {
    return request.priority;
  }

  Object getTag() {
    return request.tag != null ? request.tag : this;
  }
}
