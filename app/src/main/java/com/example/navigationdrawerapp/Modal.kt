package com.example.navigationdrawerapp

import java.io.Serializable

class Modal : Serializable{

    var name:String? = null
    var image:Int? = null
    var describe:String? = null

    constructor( name:String, image:Int, describe: String ){
        this.name = name;
        this.image = image;
        this.describe = describe;
    }

}