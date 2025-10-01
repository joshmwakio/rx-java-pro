package org.rx.app.`01_observables`.`01_basics`

import io.reactivex.rxjava3.core.Observable
import java.time.LocalDateTime

class RxObservableFromCallable {
}

fun main(){
    /*
        so in this code snippet we'll simulate a lazy db query where we will use the fromCallable method
        where we will pass a lambda function which will do the following:
        1. print a line to show that the db query is being executed
        2. simulate a delay of 2 seconds to mimic the time taken for a db
        3. simulate a potential failure by throwing an exception where if we get the datetime now we'll
        throw if the modulo of 2 is 0 for the seconds.
        4. then in this we'll return a string showing 1,200 records have been fetched from the db

       then outside the fromcallable method we'll print that we are subscribing to the observable to
       execute the dbquery. then we'll call the subscribe method where we'll have the onNext method as usual
       along with the onError and onComplete methods.
     */
    println("Creating the observable for the db query...")
    val expensiveDbQuery= Observable.fromCallable{
        println("Executing db query...")
        Thread.sleep(2000)

        if(LocalDateTime.now().second % 2 == 0){
            throw Exception("Simulated db query failure")
        }

        "Fetched 1,200 records from the database"
    }

    println("Subscribing to the observable to execute the db query...")
    expensiveDbQuery.subscribe(
        {result-> println(result)},
        {error-> println("Error occurred during db query: $error")},
        {println("Db query observable has completed emitting data")}
    )
}