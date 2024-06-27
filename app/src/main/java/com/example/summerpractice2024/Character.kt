package com.example.summerpractice2024

class Character(
    name: String,
    resourcesLink: String,
    welcomeMessage: String)
{
    private var _name : String = name
    private var _resourcesLink : String = resourcesLink
    private var _welcomeMessage = welcomeMessage

    fun getName() : String {
        return _name
    }

    fun getLink() : String {
        return _resourcesLink
    }

    fun getMessage() : String {
        return _welcomeMessage
    }
}