package org.rx.app.`01_observables`.`03_advanced`

import io.reactivex.rxjava3.core.Maybe


fun getKeyData(key: String): Maybe<String> {

    /*
        so in this function I will create a Maybe observable which will emit some string
        value data or null if not found. if the returned value is not null then we will emit
        the value on the success method and if null we can emit an oncomplete
     */
    return Maybe.create { emitter ->
        val value:String? = when (key) {
            "user_profile" -> "{\"id\":\"1\",\"name\":\"Josh\"}"
            "session_key" -> "session-key"
            else -> null
        }

        if(value!=null)
            emitter.onSuccess(value)
        else
            emitter.onComplete()
    }
}


fun main(){
    println("Testing observable with some data")
    getKeyData("user_profile")
        .subscribe(
            {item -> println(item)},
            {error -> println(error)},
            {println("Data retrival complete")}

        )

    /*
            I can also create a list of keys and loop on them using the foreach function.
            the foreach function will be accessible from the list
     */

    val keys=listOf<String>("user_profile","session_key", "user_settings")

    keys.forEach { key ->
        println("looking up for data on ${key}")
        getKeyData(key).subscribe(
            {item -> println(item)},
            {error -> println(error)},
            {println("Data retrival complete")}
        )
        println()

    }

}