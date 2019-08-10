package com.bulunduc.weatherapp.mvp.contract

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseContract {
    interface View

    abstract class Presenter<V : View>{
        private val subscriptions = CompositeDisposable()
        protected lateinit var view : V

        fun subcribe(subscription: Disposable){
            subscriptions.add(subscription)
        }

        fun unsubcribe(){
            subscriptions.clear()
        }

        fun attach(view : V){
            this.view = view
        }

        fun detach(){
            unsubcribe()
        }
    }
}