/*
 * Copyright 2014 Uwe Trottmann
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

package com.battlelancer.seriesguide.settings;

import com.battlelancer.seriesguide.sync.AccountUtils;
import com.battlelancer.seriesguide.util.SimpleCrypto;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Settings related to trakt.tv integration.
 */
public class TraktSettings {

    public static final String KEY_PASSWORD_SHA1_ENCR = "com.battlelancer.seriesguide.traktpwd";

    public static final String KEY_LAST_UPDATE = "com.battlelancer.seriesguide.lasttraktupdate";

    public static final String KEY_SHARE_WITH_TRAKT = "com.battlelancer.seriesguide.sharewithtrakt";

    public static final String KEY_AUTO_ADD_TRAKT_SHOWS
            = "com.battlelancer.seriesguide.autoaddtraktshows";

    public static final String KEY_SYNC_UNWATCHED_EPISODES
            = "com.battlelancer.seriesguide.syncunseenepisodes";

    /**
     * Returns the SHA hash of the users trakt password.<br> <b>Never</b> store this yourself,
     * always call this method.
     */
    public static String getPasswordSha1(Context context) {
        String hash = PreferenceManager.getDefaultSharedPreferences(context).getString(
                KEY_PASSWORD_SHA1_ENCR, "");

        // try decrypting the hash
        if (!TextUtils.isEmpty(hash)) {
            hash = SimpleCrypto.decrypt(hash, context);
        }

        return hash;
    }

    public static boolean isSharingWithTrakt(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_SHARE_WITH_TRAKT, false);
    }

    public static long getLastUpdateTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getLong(KEY_LAST_UPDATE, System.currentTimeMillis());
    }

    public static boolean isAutoAddingShows(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_AUTO_ADD_TRAKT_SHOWS, true);
    }

    public static boolean isSyncingUnwatchedEpisodes(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_SYNC_UNWATCHED_EPISODES, false);
    }

}
