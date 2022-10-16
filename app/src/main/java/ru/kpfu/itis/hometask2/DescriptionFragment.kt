package ru.kpfu.itis.hometask2

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.kpfu.itis.hometask2.databinding.FragmentDescriptionBinding

private const val ARG_PARAM1 = "NAME"
private const val ARG_PARAM2 = "DESCRIPTION"
private const val ARG_PARAM3 = "PATH"

class DescriptionFragment : Fragment() {
    private var binding: FragmentDescriptionBinding? = null
    private var name: String? = null
    private var description: String? = null
    private var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_PARAM1)
            description = it.getString(ARG_PARAM2)
            path = it.getString(ARG_PARAM3)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            tvName.text = name
            tvDescription.text = description
            Glide
                .with(this@DescriptionFragment)
                .load(path)
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                       return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.INVISIBLE
                       return false
                    }
                }
                    )
                .into(ivPortrait)
        }
    }

    companion object {

        const val DESCRIPTION_FRAGMENT_TAG = "DESCRIPTION_FRAGMENT_TAG"

        @JvmStatic
        fun getInstance(name: String, description: String, path: String) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, name)
                    putString(ARG_PARAM2, description)
                    putString(ARG_PARAM3, path)
                }
            }
    }
}
