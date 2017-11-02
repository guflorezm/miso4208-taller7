package me.kuehle.carreport.gui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.kuehle.carreport.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CrearCartest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void crearCartest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_create_car), withText("Create a car"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.edt_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edt_name_input_layout),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("Ford explorer"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.edt_name), withText("Ford explorer"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edt_name_input_layout),
                                        0),
                                0)));
        textInputEditText2.perform(pressImeActionButton());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.edt_initial_mileage), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edt_initial_mileage_input_layout),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("10"));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.edt_initial_mileage), withText("10"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edt_initial_mileage_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.edt_initial_mileage), withText("10"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.edt_initial_mileage_input_layout),
                                        0),
                                0)));
        textInputEditText5.perform(pressImeActionButton());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_save), withContentDescription("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Reports"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar_layout),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Reports")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.txt_title), withText("Fuel consumption"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Fuel consumption")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
