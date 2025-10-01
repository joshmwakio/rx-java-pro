package org.rx.app.`01_observables`.`03_advanced`

import io.reactivex.rxjava3.core.Single
import org.rx.app.models.UserProfile
import javax.management.RuntimeErrorException


fun fetchUserProfile(userId: Int): Single<UserProfile>{
    return Single.create { emitter ->
        println("Fetching user profile")
        Thread.sleep(1000)
        if(userId==123){
            val userProfile = UserProfile(
                userId,
                "joshmwakio@gmail.com",
                "0721466024"
            )
            emitter.onSuccess(userProfile)
        }
        else{
            emitter.onError(RuntimeErrorException(null,"Error fetching user profile with Id ${userId}"))
        }
    }
}





fun main(){
    /*
        So here in this code we are going to simulate a network request where by we'll create a method which
        will fetch a user's profile based on the user id. we will create a method called fetchUserProfile whereby we'll
        pass the user id as an argument. in return we will create a single observable and access the create lambda method
        and we'll have an emitter object in it which will help us emit either a single item or an error.
        so inside the create method we will simulate a network request which will a two seconds delay then
        we will check if the user id is 123 then create a user profile. we will access the emiter on success then pass the
        profile object on the on success. if the user id is not 123 then we will access the emitter on error and pass an exception object
        to it. We can pass a RuntimeException with a string message of "user not found".

        so here in the main method we will call the fetchUserProfile method and subscribe to it along with the onNext, onError
     */

    println("fetching an existing user profile...")
    fetchUserProfile(1).subscribe(
        {item -> println(item)},
        {error -> println(error)}
    )
}