/**
 *
 */

package com.dev.hassan.devchallenge.utils;

public enum AppKeys {

    FRAGMENT_DIALOG_TITLE("fragment_dialog_title"), SAVED_ARRAY_KEY("saved_array_key");

    /**
     * The _message_key.
     */
    private String _message_key = "";

    /**
     * Instantiates a new message keys.
     *
     * @param action the action
     */
    AppKeys(String action) {

        this._message_key = action;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {

        return this._message_key;
    }
}
