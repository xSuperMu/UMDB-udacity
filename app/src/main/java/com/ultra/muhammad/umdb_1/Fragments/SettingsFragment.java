package com.ultra.muhammad.umdb_1.Fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.ultra.muhammad.umdb_1.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);
    }
}
