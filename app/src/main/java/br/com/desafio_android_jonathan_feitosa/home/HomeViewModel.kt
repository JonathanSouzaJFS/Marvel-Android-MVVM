package br.com.desafio_android_jonathan_feitosa.home

import android.util.Log.i
import androidx.lifecycle.MutableLiveData
import br.com.desafio_android_jonathan_feitosa.base.BaseViewModel
import br.com.desafio_android_jonathan_feitosa.models.Hero
import br.com.desafio_android_jonathan_feitosa.repository.UpcomingHeroesRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val upcomingHeroesRepository: UpcomingHeroesRepository) : BaseViewModel() {

    val heroesLiveData = MutableLiveData< Pair< List<Hero>?, Int> >()

    fun getUpcomingList(offset:Int){

        launch{

            loading.value = true

            try {
                hasErrorLiveData.value = false
                heroesLiveData.value = upcomingHeroesRepository.getUpcomingList(offset)
            }catch (t:Throwable){
                hasErrorLiveData.value = true
                i("ResultadoJFS", "[error] getUpcomingList: ${t.message}")
            }finally {
                loading.value = false
            }
        }

    }

}