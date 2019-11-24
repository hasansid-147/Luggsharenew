package com.android.luggshare.presentation.screens.dashboard.activities;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.android.luggshare.R;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.myoffers.fragments.MyOffersFragment;
import com.android.luggshare.presentation.screens.myrequests.fragments.MyRequestFragment;
import com.android.luggshare.presentation.screens.profile.fragments.ProfileFragment;
import com.android.luggshare.presentation.screens.sender.fragments.SenderRequestFragment;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;

public class DashboardActivity extends CoreActivity implements CoreFragment.OnFragmentInteractionListener{

    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nestedScroll)
    NestedScrollView nestedScroll;

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
                        replaceChildFragmentWithDelay(R.id.content_area, new ProfileFragment(), true, false, null, true);
                        break;
                    case R.id.nav_security:
                        replaceChildFragmentWithDelay(R.id.content_area, new HomeFragment(), true, false, null, true);
                        break;
                    case R.id.nav_request:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyRequestFragment(), true, false, null, true);
                        break;
                    case R.id.nav_offer:
                        replaceChildFragmentWithDelay(R.id.content_area, new MyOffersFragment(), true, false, null, true);
                        break;
                    case R.id.nav_tracking:
                        replaceChildFragmentWithDelay(R.id.content_area, null, true, false, null, true);
                        break;
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