package br.com.desafio_android_jonathan_feitosa.ui.hq

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.desafio_android_jonathan_feitosa.R
import br.com.desafio_android_jonathan_feitosa.base.BaseFragment
import br.com.desafio_android_jonathan_feitosa.models.ComicsId
import org.koin.androidx.viewmodel.ext.android.viewModel


class HqFragment : BaseFragment() {

    private var hList: MutableList<ComicsId> = arrayListOf()
    private val hqViewModel by viewModel<HqViewModel>()
    private var loading = false
    private var totalComics = 0
    private var comicId : Int? =  0


    override fun checkConnection() {
        hqViewModel.getComicId(comicId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        comicId = arguments?.getInt("comicId")

        Log.i("ResultadoJFS", "Prices: $comicId")


        hqViewModel.comicsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { pair ->
                hList.addAll(pair.first!!)
                totalComics = pair.second
                loading = false
                Log.i("ResultadoJFS", "Prices: $hList")
            }
        })

        hqViewModel.hasErrorLiveData.observe(viewLifecycleOwner, Observer {error ->
            if (error) {
                Toast.makeText(
                    requireActivity(), "Error get comicsId list !",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        checkConnection()

        Log.i("ResultadoJFS", "Prices: $hList")

    }
}
