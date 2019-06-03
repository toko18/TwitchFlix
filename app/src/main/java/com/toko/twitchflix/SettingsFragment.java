package com.toko.twitchflix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference delete_preference = findPreference("delete_account");

        delete_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Toast.makeText(getContext(), "This feature is not ready yet :(", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        Preference about_preference = findPreference("about");

        about_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Intent j = new Intent(getContext(), AboutActivity.class);
                getContext().startActivity(j);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        assert root != null;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar((Toolbar) root.findViewById(R.id.settings_toolbar));
        ActionBar actionBar = activity.getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Settings");
        setHasOptionsMenu(true);
        return root;
    }
}