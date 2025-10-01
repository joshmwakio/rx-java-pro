package org.rx.app.`01_observables`.`02_subjects`

import io.reactivex.rxjava3.subjects.PublishSubject

class RxPublishSubject {
}

fun main(){
    /*
      so here I will create a hot observable variable which is going to hold the observable data from
      a publish subject. when you call the create method from the publish subject, we are basically going to create some
      string data from it. This is by calling the onNext method from the publish subject hot observable variable.
      In the parenthesis of the onNext method, I will pass some string data. then I will call the sleep method to
      simulate some time passing. then I will have the first subscriber joining. this will be done by
      calling the subscribe method from the hot observable variable. In it, we'll call the onNext method
      which will print the data of the subscriber receiving an item, along with the onError and onComplete
      methods.

      I will then call the On Next method from the hot observable with some string data, then I will call the sleep method
      to simulate some time passing. then I will have the second subscriber joining. this will be
      done by calling the subscribe method from the hot observable variable. In it, we'll call the onNext method
      which will print the data of the subscriber receiving an item, along with the onError and
      onComplete methods. then I will call the on Next method with some string data on it.

      Finally I will call the oncomplete method from the hot observable to indicate that the
        observable has completed emitting data.
     */

    val hotObservable= PublishSubject.create<String>()
    hotObservable.onNext("Event 1 occurred")
    Thread.sleep(1000)
    hotObservable.subscribe(
        { item -> println("First subscriber received: $item") },
        { error -> println("First subscriber error: $error") },
        { println("First subscriber: Observable has completed emitting data") }
    )
    hotObservable.onNext("Event 2 occurred")
    Thread.sleep(1000)
    hotObservable.subscribe(
        { item -> println("Second subscriber received: $item") },
        { error -> println("Second subscriber error: $error") },
        { println("Second subscriber: Observable has completed emitting data") })
    hotObservable.onNext("Event 3 occurred")
    hotObservable.onComplete()
}