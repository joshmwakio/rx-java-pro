package org.rx.app.`01_observables`.`01_basics`

import io.reactivex.rxjava3.core.Observable

fun main(){
    /*
        In this code snippet, we will create an array of temperature readings using the
        array of function. then we'll create an Observable from this array using the fromArray
        method. in the method we will pass the temperature readings array. and then outside
        the method we will call the subscribe method. in it we will have the onNext method
        which will print the temperature reading being emitted by the observable. then we will
        call the onComplete method which will print a line to show that the observable has completed
        emitting data. the spread operator (*) is used to unpack the elements of the array
        so that they can be passed as individual arguments to the fromArray method.
     */

    val kenyanTemperatures= arrayOf(23.4,25.6,26.9,32.9,29.8,23.5, 19.2,16,0.4, 22.1, 28.3)
    Observable.fromArray(*kenyanTemperatures)
        .subscribe(
            { temp ->
                if(temp.toDouble()>26.0)
                {
                    println("🔥Temperature reading: $temp °C - It's quite warm!")
                }
                else if(temp.toDouble() in 20.0..26.0)
                {
                    println("☁️Temperature reading: $temp °C - Pleasant weather.")
                }
                else
                {
                    println("🌨️Temperature reading: $temp °C - It's a bit chilly!")
                }
            },
            { error -> println("Error occurred: $error") },
            { println("Observable has completed emitting temperature readings") }
        )
}