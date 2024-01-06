package com.lolideveloper.shogun.Core

class Loader {
    companion object {
        init {
            System.loadLibrary("shogun")
        }
        external fun getID() : String
    }
}