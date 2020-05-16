package com.vicky7230.okcredit_problem.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicky7230.okcredit_problem.R
import com.vicky7230.okcredit_problem.data.Article
import com.vicky7230.okcredit_problem.data.State
import com.vicky7230.okcredit_problem.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_source_news.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : BaseFragment(), NewsAdapter.Callback {

    companion object {
        val SOURCE = "source"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    lateinit var newsViewModel: NewsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_source_news, container, false)
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter.setCallback(this)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {

        retry_button.setOnClickListener {
            retry_button.visibility = View.GONE
            news_list.visibility = View.GONE
            progress.visibility = View.VISIBLE

            arguments?.takeIf { it.containsKey(SOURCE) }?.apply {
                newsViewModel.getNewsFromDb(getString(SOURCE)!!)
            }
        }

        news_list.layoutManager = LinearLayoutManager(context)
        news_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        news_list.adapter = newsAdapter

        newsViewModel.state.observe(this.viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    retry_button.visibility = View.GONE
                    news_list.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
                is State.Error -> {
                    progress.visibility = View.GONE
                    news_list.visibility = View.GONE
                    retry_button.visibility = View.VISIBLE
                    showError(it.exception.localizedMessage)
                }
                is State.Success -> {
                    progress.visibility = View.GONE
                    retry_button.visibility = View.GONE
                    news_list.visibility = View.VISIBLE
                    newsAdapter.updateItems(it.data)
                }
            }
        })

        arguments?.takeIf { it.containsKey(SOURCE) }?.apply {
            newsViewModel.getNewsFromDb(getString(SOURCE)!!)
        }
    }

    override fun onItemClick(article: Article) {
        /*arguments?.takeIf { it.containsKey(SOURCE_NAME) }?.apply {
            *//*startActivity(
                WebViewUI.getStartIntent(
                    context!!,
                    article.url,
                    getString(SOURCE_NAME)!!
                )
            )*//*
        }*/
    }
}
