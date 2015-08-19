package com.example.android.uamp.utils;

import android.media.session.PlaybackState;
import android.os.Bundle;

import com.example.android.uamp.R;

/**
 * Helper class for managing custom media controls.
 */
public class MediaControlHelper {

    /**
     * Setup the extras with basic settings for using custom previous and next slots.
     *
     * If a slot is reserved, and the corresponding custom next or previous action is not set,
     * then the slot will be empty. If the slot is not reserved, then the next custom action should
     * fill the slot.
     *
     * @param extras Flags will be set in this Bundle.
     * @param reserveSkipToNextSlot true to reserve slot for custom Next action
     * @param reserveSkipToPrevSlot true to reserve slot for custom Previous action
     */
    public static void setSlotReservationFlags(Bundle extras,
                boolean reserveSkipToNextSlot, boolean reserveSkipToPrevSlot) {
        if (reserveSkipToPrevSlot) {
            extras.putBoolean(MediaControlConstants.EXTRA_RESERVE_SLOT_SKIP_TO_PREVIOUS, true);
        } else {
            extras.remove(MediaControlConstants.EXTRA_RESERVE_SLOT_SKIP_TO_PREVIOUS);
        }
        if (reserveSkipToNextSlot) {
            extras.putBoolean(MediaControlConstants.EXTRA_RESERVE_SLOT_SKIP_TO_NEXT, true);
        } else {
            extras.remove(MediaControlConstants.EXTRA_RESERVE_SLOT_SKIP_TO_NEXT);
        }
    }

    /**
     * This should be called any time availableActions is updated. Based on the flags set with
     * setSlotReservationFlags(), this will update the extras object with the correct flags
     * to reserve the collect slots on Android Auto and Android Wear.
     *
     * @param extras
     * @param availableActions
     */
    public static void updateGlobalSlotReservationFlags(Bundle extras, long availableActions) {
        boolean reserveSkipToPrevSlot =
                extras.getBoolean(MediaControlConstants.EXTRA_RESERVE_SLOT_SKIP_TO_PREVIOUS);
        boolean reserveSkipToNextSlot =
                extras.getBoolean(MediaControlConstants.EXTRA_RESERVE_SLOT_SKIP_TO_NEXT);

        boolean customPrevDisabled = !isUsingCustomPreviousAction(availableActions);
        boolean customNextDisabled = !isUsingCustomNextAction(availableActions);

        boolean prevReserved = customPrevDisabled && reserveSkipToPrevSlot;
        boolean nextReserved = customNextDisabled && reserveSkipToNextSlot;

        CarHelper.setSlotReservationFlags(extras, nextReserved, prevReserved);
        WearHelper.setSlotReservationFlags(extras, nextReserved, prevReserved);
    }

    public static boolean isUsingCustomPreviousAction(long availableActions) {
        boolean customPrevDisabled =
                (availableActions & MediaControlConstants.ACTION_SKIP_TO_PREVIOUS) == 0;
        return !customPrevDisabled;
    }

    public static boolean isUsingCustomNextAction(long availableActions) {
        boolean customNextDisabled =
                (availableActions & MediaControlConstants.ACTION_SKIP_TO_NEXT) == 0;
        return !customNextDisabled;
    }

    public static int getSkipToPreviousIcon(PlaybackState state) {
        for (PlaybackState.CustomAction action : state.getCustomActions()) {
            if (MediaControlConstants.CUSTOM_ACTION_SKIP_TO_PREVIOUS.equals(action.getAction())) {
                return action.getIcon();
            }
        }
        return R.drawable.ic_skip_previous_white_48dp;
    }

    public static int getSkipToNextIcon(PlaybackState state) {
        for (PlaybackState.CustomAction action : state.getCustomActions()) {
            if (MediaControlConstants.CUSTOM_ACTION_SKIP_TO_NEXT.equals(action.getAction())) {
                return action.getIcon();
            }
        }
        return R.drawable.ic_skip_next_white_48dp;
    }
}
