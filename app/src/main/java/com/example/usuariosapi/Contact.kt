package com.example.usuariosapi

class Contact {
    var id: String? = null
    var name: String? = null
    var lastName: String? = null
    var address: String? = null
    var phone: String? = null

    constructor(id: String, name: String, lastName: String, address: String, phone: String){
        this.id = id
        this.name = name
        this.lastName = lastName
        this.address = address
        this.phone = phone
    }
}