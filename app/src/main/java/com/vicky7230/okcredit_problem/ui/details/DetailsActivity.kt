package com.vicky7230.okcredit_problem.ui.details

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.vicky7230.okcredit_problem.R
import com.vicky7230.okcredit_problem.data.Article
import com.vicky7230.okcredit_problem.ui.base.BaseActivity
import com.vicky7230.okcredit_problem.utils.CommonUtils
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var detailsViewModel: DetailsViewModel

    private val inputDateFormat: SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    private val outputDateFormat: SimpleDateFormat =
        SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)

    companion object {
        const val ARTICLE = "article"

        fun getStartIntent(context: Context, article: Article): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(ARTICLE, article)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailsViewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {

        back_button.setOnClickListener {
            finish()
        }

        if (intent != null && intent.getParcelableExtra<Article>(ARTICLE) != null) {
            val article = intent.getParcelableExtra<Article>(ARTICLE)
            if (CommonUtils.isValidUrl(article?.coverImageUrl)) {
                Glide.with(this)
                    .load(article?.coverImageUrl)
                    .error(R.drawable.error_image)
                    .transform(CenterCrop())
                    .into(cover_image)
            } else {
                cover_image.setImageResource(R.drawable.error_image)
            }
            title_text.text = article?.title
            author_name.text = article?.author?.removePrefix("By ")

            try {
                val date = inputDateFormat.parse(article!!.publishedAt)
                published_on.text = "Published On : ${outputDateFormat.format(date!!)}"
            } catch (e: Exception) {
                Timber.e("Date Parsing Error : ${e.localizedMessage}")
            }

            article_link.paintFlags = article_link.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            article_link.text = article?.url
            article_link.setOnClickListener {
                val url = article?.url
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.setPackage("com.android.chrome")
                try {
                    startActivity(i)
                } catch (e: ActivityNotFoundException) {
                    i.setPackage(null)
                    startActivity(i)
                }
            }

            abstract_text.text = article?.abstractx
        }
    }
}
