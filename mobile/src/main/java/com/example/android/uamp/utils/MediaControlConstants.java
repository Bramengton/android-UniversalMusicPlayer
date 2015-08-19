/*
 */
package com.example.android.uamp.utils;

public class MediaControlConstants {
    public static final String EXTRA_RESERVE_SLOT_SKIP_TO_NEXT = "com.example.android.uamp.extra.RESERVE_SLOT_SKIP_TO_NEXT";
    public static final String EXTRA_RESERVE_SLOT_SKIP_TO_PREVIOUS = "com.example.android.uamp.extra.RESERVE_SLOT_SKIP_TO_PREVIOUS";

    public static final String CUSTOM_ACTION_SKIP_TO_PREVIOUS = "com.example.android.uamp.ACTION_SKIP_TO_PREVIOUS";
    public static final String CUSTOM_ACTION_SKIP_TO_NEXT = "com.example.android.uamp.ACTION_SKIP_TO_NEXT";

    /**
     * Values to indicate whether custom actions are available.
     * Caution: These values must not conflict with {@link android.media.session.PlaybackState}.
     */
    public static final long ACTION_SKIP_TO_PREVIOUS = 1L << 32;
    public static final long ACTION_SKIP_TO_NEXT = 1L << 33;

    private MediaControlConstants() {
    }
}

