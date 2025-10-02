package org.rx.app.`02_flowables`.`01_basics`

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    /*
        In this flowable example, we will create a flowable that will emit
        100,000 integers. This create method receives two parameters one is the emitter and the
        other is the backpressure strategy.
        we will us a for loop which will process the numbers from 1 to
        100000

        in the loop we will use the onNext method which will emit the item but first we must check if the emitter
        was cancelled. if it was cancelled we can break off from the loop. outside the loop we will call
        the onComplete method and then write the second parameter which is the backpressure strategy. we will use the BUFFER strategy
        which will buffer all the items until the subscriber is ready to consume them.

        now with the varible that holds the flowable we will call the observe method first which will
        specify the scheduler on which the flowable will emit the items. we will use the computation scheduler.
        We will then call the subscribe method where will will access the onNext, onError and onComplete methods.
        we will simulate a long processing time by adding a sleep of 1 second in the onNext method.
        finally we will add a sleep in the main thread to allow time for processing.
     */

    val numberStream = Flowable.create<Int>({ emitter ->
        for (i in 1..100_000) {
            if (emitter.isCancelled) {
                break
            }
            emitter.onNext(i)
        }
        emitter.onComplete()
    }, BackpressureStrategy.BUFFER)

    numberStream.observeOn(Schedulers.computation()).subscribe(
        { item ->
            Thread.sleep(1000)
            println("Received item: $item")
        },
        { error -> println("Error occurred: $error") },
        { println("All items processed successfully") }
    )
    Thread.sleep(120_000) // Sleep to allow time for processing
}