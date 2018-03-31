package com.gefa.invf.client.activities;

import java.util.ArrayList;

public class Actions extends ArrayList<com.gefa.invf.client.activities.Activity> {

	public boolean has(Class clazz) {
		for (com.gefa.invf.client.activities.Activity activity : this) {
			if (activity.getClass() == clazz) {
				return true;
			}
		}

		return false;
	}

	public <T extends com.gefa.invf.client.activities.Activity> T get(Class clazz) {

		for (com.gefa.invf.client.activities.Activity activity : this) {
			if (activity.getClass() == clazz) {
				return (T) clazz.cast(activity);
			}
		}

		return null;
	}

}
