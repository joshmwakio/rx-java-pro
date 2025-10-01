package org.rx.app.`01_observables`.`01_basics`

import io.reactivex.rxjava3.core.Observable

fun main(){
    /*
        so here am going to create a list of users using the list of method.
        then I will create an observable using the fromIterable method. In this method I will pass the list
        of users as an argument and then I will subscribe. As simple as that
     */

    val users= listOf("Josh (Admin)","Eren (Assistant)","Mikasa (Developer)","Armin (Manager)")
    val usersObservable = Observable.fromIterable(users)
    usersObservable.subscribe(
        {user->
            if(user.contains("Admin")) {
                println("🔑 User: $user - Has full access.")
            }
            else if(user.contains("Assistant")) {
                println("👩‍🏫 User: $user - Has limited access.")
            }
            else if(user.contains("Developer")) {
                println("🔧 User: $user - Can modify code.")
            }
            else if(user.contains("Manager")) {
                println("🧘‍♀️ User: $user - Can manage teams.")
            }
            else
            {
                println("👤 User: $user - Role not recognized.")
            }
        }
        ,
        {error-> println("Error occurred: $error")},
        {println("Observable has completed emitting user roles")}
    )
}