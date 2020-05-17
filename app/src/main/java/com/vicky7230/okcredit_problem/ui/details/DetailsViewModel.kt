package com.vicky7230.okcredit_problem.ui.details

import com.vicky7230.okcredit_problem.data.DataManager
import com.vicky7230.okcredit_problem.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}