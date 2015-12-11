package cz.kinst.jakub.viewmodelbinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by jakubkinst on 10/11/15.
 */
public abstract class BaseViewModelFragment<T extends ViewDataBinding, S extends BaseViewModel> extends Fragment implements ViewInterface {

    private final ViewModelHelper<S, T> mViewModelHelper = new ViewModelHelper<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModelHelper.onCreate(this, savedInstanceState, getViewModelClass());
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModelHelper.onCreate(this, savedInstanceState, getViewModelClass());
        return mViewModelHelper.getBinding().getRoot();
    }


    @Nullable
    @Override
    public Bundle getBundle() {
        return getArguments();
    }


    @Override
    public void onResume() {
        super.onResume();
        mViewModelHelper.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mViewModelHelper.onPause();
    }


    @Override
    public void onDestroyView() {
        mViewModelHelper.onDestroyView(this);
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        mViewModelHelper.onDestroy(this);
        super.onDestroy();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        mViewModelHelper.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(getViewModel() != null) {
            getViewModel().onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public Context getContext() {
        return getActivity();
    }


    public S getViewModel() {
        return mViewModelHelper.getViewModel();
    }


    public T getBinding() {
        return mViewModelHelper.getBinding();
    }


    protected abstract Class<? extends BaseViewModel> getViewModelClass();
}
