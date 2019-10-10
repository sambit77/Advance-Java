Web-Application
---------------

 -> it is very similar to distributed applications in core java.
 -> like distributed application Web-Application is also divided into two parts
     1.client side
     2.server side

 -> in case of distributed application , both client and server machine require jdk.
 -> it is not required to install jdk in client machine in case of web application.
 -> jdk is required to install in server machine only.
 -> in case of Web-Application both servre and client side application running in server machine ,end user access
    only client side application with the help of web browser.
 ->  web browser always send request in the foem of http protocol and get response in the form of http.


    end user --uses--> client(Browser) -->   client side apps -> server side apps
                                              (-------------SERVER MACHINE-----)

 -> IPC 

    Inter Process Communication
    ---------------------------
    both the client and server process running in single machine.

 -> RPC 
    Remote Process Communication / Remote Procedure Call
    ----------------------------------------------------
       server and client process running in different machine.


 -> web application always run within web server.

     web server example:- tomcat , glassfish ,weblogic , websphere

 -> web server control the entire web application.
 -> web application does not contain main method.
 -> web application is a collection of webpages. 

      web pages are of two types.

        1.static web pages
        ------------------
        -> these web pages are not interactive.
        -> the content of the web page is same for all user requests.

        Ex:
           about us , terms and conditions.

           Technologies used 
           -----------------
           html
           css
           javascript
           bootstraps
           jquery


        2.dynamic web pages
        -------------------

        -> these web pages are intreactive.
        -> the contents of the web pages are different for different user requests.

        EX:
           login , registration.

           Technologies used
           ------------------
           jsp
           servlet
           filter 
           jstl
           listener
           taglib
           spring 
           struts 
------------------------------------------------------------------------------------------------------------------
MVC (Model View Controller)


  -> it is an architectural design pattern.
  -> this is generally used to create an web application.
  -> MVC divides the web application into 3 different layer:-

        1.view layer/presentation layer
        2.controller layer
        3.model layer


        1.view layer
        ------------

        -> this layer is responsible to store view components
        -> ex- images ,audio and video files , html files ,jsp pages , javascript , 
           css bootstarp files etc.

        2.controller layer
        ------------------

        -> this layer is responsible to read data from presentation layer.
        -> controller decides which layer will process the request 
            buisness layer or  DAO layer(Data Access Object)

        3.Model layer
        -------------
        -> this layer is divide into two parts.

             ->buisness layer
             ----------------

               ->it contains the buisness codes like arithmetic operation vallidation etc.

             -> dao layer
             ------------

               ->it contains database interaction code like query processing.

--------------------------------------------------------------------------------------------------------------






