package org.rx.app.`01_observables`.`01_basics`

import io.reactivex.rxjava3.core.Observable

fun main(){
    /*so here I will create a variable that is going to hold the observable data from the just
       I will have a first subscriber, and I will show it by printing the line to note
       that the first subscriber has subscribed, and then I will use the subscribe method
       in it, I will have the onnext method which will print the data being emitted by the observable.
       this onnext method is a lambda function which is written in a shorhand way. The shorthand way
       is written by removing the parenthesis and the curly braces. It will work if there is a single
       parameter. this OnNext method has an equal sign followed by some curly braces. Inside the curly braces
       I will print the data being emitted by the observable. Then I will call the onComplete method
       which will print a line to show that the observable has completed emitting data. I will call the second subscriber which
       will print a line to show that the second subscriber has subscribed, and then I will use the subscribe method
       in it, I will have the onnext method which will print the data being emitted by the observable. And finally call the
       onComplete method which will print a line to show that the observable has completed emitting data.
       so remember that the subscribe method will not permit named parameters. dont name your method as onNext, onError, onComplete
       just pass the lambda functions in the order of onNext, onError, onComplete.
    */

    val coldObservable= Observable.just("Josh","Eren", "Mikasa","Armin")
    println("First subscriber has subscribed")
    coldObservable.subscribe(
        { item -> println("First subscriber received: $item") },
        { error -> println("First subscriber error: $error") },
        { println("First subscriber: Observable has completed emitting data") }
    )
    println("Second subscriber has subscribed")
    coldObservable.subscribe(
        { item -> println("Second subscriber received: $item") },
        { error -> println("Second subscriber error: $error") },
        { println("Second subscriber: Observable has completed emitting data") }
    )
}