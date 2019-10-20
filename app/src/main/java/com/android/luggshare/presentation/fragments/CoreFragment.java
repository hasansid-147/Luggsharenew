package com.android.luggshare.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.luggshare.R;
import com.android.luggshare.presentation.application.CustomApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class CoreFragment extends Fragment {
    //region /* ====================================== Interface =============================================== */
    public interface OnFragmentInteractionListener {
        void onNavigateNextFragment(@NonNull final Fragment fragmentName,
                                    final boolean goNext, final boolean goBack, Bundle params, final boolean addToBackStack);


    }

    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */

    // Variable for Unbinder
    private Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    protected abstract int getLayoutResourceId();



    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResourceId(), container, false);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Unbind the views
        if (unbinder != null)
            unbinder.unbind();
    }

    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    protected void replaceChildFragmentWithDelay(@NonNull final Fragment fragmentName,
                                                 final boolean goNext, final boolean goBack, Bundle params, final boolean addToBackStack) {
        if (mListener != null) {
            mListener.onNavigateNextFragment(fragmentName, goNext, goBack, params, addToBackStack);


        }
    }

    public void navigateBackFragment() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }
    //endregion /* =================================== User Define Methods ===================================== */


}
