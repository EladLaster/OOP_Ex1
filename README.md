# Object_Oriented_Programming_1

*presenters:*

            elad laster 208968636

            david stern 315556308
            
*subject:*
          
            Send updates with Observer(a design pattern) and track object size in Heap.
            
## get start 
1) clone the repository:

                        git clone https://github.com/EladLaster/Object_Oriented_1.git   
                                               
2) Open in any Java IDE.
 
3) Build a GroupAdmin object and a ConcreteMember object,register your ConcreteMember to the GroupAdmin, use the functions in the GroupAdmin class to add and change the UndoableStringBuilder object, run() the project and see how all the ConcreteMember objects will update.

4) Enjoy!!!


## the observer design pattern

The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.

There are times when users will want to receive information from a certain site (for example: a news site, social networks, promotions and discounts from any site and more ...), for this they will have to go to the site where the information is available and ask him if it has been updated recently again and again until it really happens, in order to streamline the process - since it will cause a load on the site and waste time, We want to find a design pattern that will allow us to do it efficiently and quickly.

The Observer is a design pattern that enables updating all users of the site by adding them to the list of those registered to the site and updates directly in any update that is made on the site.


## classes

*GroupAdmin:*

In order to unite all users who want to receive updates from the GroupAdmin, we will build the group that will serve as our "update center" and in which the changes in the information will occur.
In it, changes in the information will happen, and any updates made will automatically reach those registered in the group
We will do this with the help of the "register" function in which we will put the interested user in a list of users. We will then be able by the functions we have built in UndoableStringBuilder to change the information in the "Update Center".
to each function we will attach the command function "Notify" and with its help we will send the updates to all the members in the list.
Of course, any user will also be able to exit the "Update Center" by the "unregister" function.

We will use the following functions in this class:

- _register()_- registers a new member to a GroupAdmin and adds him to the arrMember list.

- _unregister()_- unregister a specific member from the GroupAdmin and remove him from the arrMember list.

- _append()_- Inserts the string into this character sequence.

- _insert()_- Appends the specified string to the group.

- _delete()_- Delete the characters in a substring of this sequence.

- _undo()_- Cancels the last change that was made to the sequence and goes back to the previous sequence.

- _notify()_- notifies all the members in the GroupAdmin with any change that happens.

*ConcreteMember:*

Within the ConcreteMember there will be the "update" function, which will actually make a shallow copying of the information that entered the "Update Center" directly into it and thus the user will be able to see the change made to the information. Each user will decide to be placed on the list of users and also to exit it at any time he chooses.

We will use the following functions in this class:

- _update()_-  Updating the change thet happans to the member by a shallow copy.


## tests

*GroupAdmin test:*

In this class, we will check the functions of the "Update Center".
In the "register" function, We would like to see that indeed the new users are added to the list of registered . 
In the "unregister" function,  We would like to see that indeed the users who wanted to exit the list of registered did indeed exit it
In all functions of changing information, We would also like to see that any changes made to the information has really happened.
In the "Notify" function, we will check whether the users have indeed received the information they should have received.

*ConcreteMember test:*

In this class, we will check whether the "users" function.
In te "update" function, We would like to see that the information copied to users is indeed the exact information that came from the "Update Center".

*JvmUtieles test:*

In this class, we would like to test the efficiency of the realization of our functions. We will see the size of the object, all the references that the object contains, and the total amount of memory for the benefit of the program.
