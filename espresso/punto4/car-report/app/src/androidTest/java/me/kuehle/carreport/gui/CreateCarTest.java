package me.kuehle.carreport.gui;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.kuehle.carreport.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateCarTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createCarTest() {

        onView(withId(R.id.btn_create_car)).perform(click());
        onView(withId(R.id.edt_name)).perform(typeText("Ford Explorer"), closeSoftKeyboard());
        onView(withId(R.id.edt_initial_mileage)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.menu_save)).perform(click());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Fuel consumption")).check(matches(isDisplayed()));
    }
}
