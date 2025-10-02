package org.rx.app.`01_observables`.`03_advanced`

import io.reactivex.rxjava3.core.Completable

fun main(){
    println("Testing a completable observable with db operations")

    val testData=listOf(
        "user preferences: dark theme, notifications enabled",
        "session data: user logged in at 2025-10-02"
    )

    testData.forEachIndexed { index,item ->
        println("${index+1}: ${item}")
        saveToDatabase(item).subscribe(
            { println("Data saved successfully") },
            { error -> println(error) }
        )
    }

}
fun saveToDatabase(data: String): Completable {
    /*
        so in this method we will create and return a completable observable. in this create method we are going to
        emit the complete on a successful operation otherwise we will emit an error. so the code will be set on a try
        catch block
     */

    return Completable.create { emitter ->
        try {
            if(data!=null){
                println("saving data to database")
                Thread.sleep(5000)
                emitter.onComplete()
            }
            else{
                emitter.onError(IllegalArgumentException("data is null"))
            }

        }
        catch(e:Exception){
            emitter.onError(e)
        }
    }
}
