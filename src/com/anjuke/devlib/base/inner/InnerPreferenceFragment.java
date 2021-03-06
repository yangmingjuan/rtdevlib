package com.anjuke.devlib.base.inner;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.anjuke.devlib.common.GlobalInstance;

public abstract class InnerPreferenceFragment extends PreferenceFragment {
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (GlobalInstance.dualPane) {
			getActivity().getActionBar().setTitle(getBarTitleWithPath());
		} else {
			getActivity().getActionBar().setTitle(getBarTitle());
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initComponents();
		initEvents();
		initLogic();
	}

	protected abstract int getBarTitle();

	protected abstract int getBarTitleWithPath();

	protected abstract void initComponents();
	
	protected abstract void initEvents();

	protected abstract void initLogic();

	protected abstract void initMenu(Menu menu);
	
	protected abstract String getMainActivityName();
	
	protected abstract int getPreferenceLayoutId();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		addPreferencesFromResource(getPreferenceLayoutId());
	}

	@Override
	public void onPause() {
		getActivity().setTitle(getBarTitle());
		super.onPause();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity().getClass().getName()
				.equals(getMainActivityName())
				&& !GlobalInstance.dualPane) {
			return;
		}

		initMenu(menu);
	}

}
