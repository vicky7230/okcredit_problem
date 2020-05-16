package com.vicky7230.okcredit_problem.data

import com.vicky7230.okcredit_problem.data.db.DbHelper
import com.vicky7230.okcredit_problem.data.network.ApiHelper


interface DataManager : ApiHelper, DbHelper {
}