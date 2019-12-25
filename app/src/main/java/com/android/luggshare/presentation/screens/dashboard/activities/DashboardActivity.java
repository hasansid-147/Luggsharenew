package com.android.luggshare.presentation.screens.dashboard.activities;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.GetUserProfileBundle;
import com.android.luggshare.common.constants.IsDashboard;
import com.android.luggshare.common.constants.IsPreferenceProfile;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.Claim.ClaimFragment;
import com.android.luggshare.presentation.screens.Notifications.MyNotificationFragment;
import com.android.luggshare.presentation.screens.cards.fragments.MyPaymentFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;
import com.android.luggshare.presentation.screens.myoffers.fragments.MyOffersFragment;
import com.android.luggshare.presentation.screens.myrequests.fragments.MyRequestFragment;
import com.android.luggshare.presentation.screens.profile.fragments.ProfileFragment;
import com.android.luggshare.presentation.screens.splash.FragmentPolicies;
import com.android.luggshare.presentation.screens.tracking.fragments.fragment.MyTrackingFragment;
import com.google.android.material.navigation.NavigationView;
import butterknife.BindView;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class DashboardActivity extends CoreActivity implements CoreFragment.OnFragmentInteractionListener{

    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nestedScroll)
    NestedScrollView nestedScroll;

    GetUserProfileBundle getUserProfileBundle;

    private static final int sDELAY_MILLIS = 150;
    private final String TRANSACTION_FRAGMENT_TAG = "transactionFragment";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_new_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        t = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.nav_home);

        loadDefaultView();

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                switch(id)
                {
                    case R.id.nav_home:
                        replaceChildFragmentWithDelay(R.id.content_area, new HomeFragment(), true, false, null, true);
                        break;
                    case R.id.nav_profile:

                        IsPreferenceProfile isprefuser = IsPreferenceProfile.getInstance();
                        isprefuser.setData(true);
                        getUserProfileBundle = new GetUserProfileBundle();
                        getUserProfileBundle.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
                        getUserProfileBundle.setEmail(null);


                        Bundle bundle = new Bundle();
                        bundle.putSerializable(BundleKeys.GET_USER_PROFILE, getUserProfileBundle);
                        replaceChildFragmentWithDelay(R.id.content_area, new ProfileFragment(), true, false, bundle, true);
                        break;
                    /*case R.id.nav_security:
                        replaceChildFragmentWithDelay(R.id.content_area, new HomeFragment(), true, false, null, true);
                        break;*/
                    case R.id.nav_request:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyRequestFragment(), true, false, null, true);
                        break;
                    case R.id.nav_offer:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyOffersFragment(), true, false, null, true);
                        break;
                    case R.id.nav_tracking:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyTrackingFragment(), true, false, null, true);
                        break;
                    case R.id.nav_notifications:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyNotificationFragment(), true, false, null, true);
                        break;
                    case R.id.nav_payment:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyPaymentFragment(), true, false, null, true);
                        break;


                    case R.id.nav_policies:
                        replaceChildFragmentWithDelay(R.id.content_area, new FragmentPolicies(), true, false, null, true);
                        break;

                    case R.id.nav_logout:

                        PreferenceManager.getInstance().clear();

                        ApplicationStateManager.getInstance().setIsAuthenticated(false);

                        // After logout redirect user to Loing Activity
                        Intent i = new Intent(CustomApplication.getContext(), LoginActivity.class);
                        // Closing all the Activities
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Staring Login Activity
                        CustomApplication.getContext().startActivity(i);


                    default:
                        return true;
                }


                return true;

            }
        });


    }

    private void loadDefaultView() {
        replaceChildFragmentWithDelay(R.id.content_area, new HomeFragment(), true, false, null, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    protected void replaceChildFragmentWithDelay(@IdRes final int containerId, @NonNull final Fragment fragmentName,
                                                 final boolean goNext, final boolean goBack, Bundle params, final boolean addToBackStack) {

        Fragment fragment = fragmentName;
        fragment.setArguments(params);


        final Fragment finalFragment = fragment;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (goNext) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (goBack) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (!goNext && !goBack) {
                    fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                }

                fragmentTransaction.replace(containerId, finalFragment, TRANSACTION_FRAGMENT_TAG);
                if (addToBackStack)
                    fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }, sDELAY_MILLIS);
    }

    @Override
    public void onNavigateNextFragment(@NonNull Fragment fragmentName, boolean goNext, boolean goBack, Bundle params, boolean addToBackStack) {
        replaceChildFragmentWithDelay(R.id.content_area, fragmentName, goNext, goBack, params, addToBackStack);
    }

    public void setNestedScroll() {
        nestedScroll.requestDisallowInterceptTouchEvent(true);
    }
}