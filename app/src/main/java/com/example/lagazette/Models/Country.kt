package com.example.lagazette.Models

class Country(
    var countryName: String, // Image name (Without extension)
    var flagName: String, var population: Int
) {

    override fun toString(): String {
        return countryName + " (Population: " + population + ")"
    }
}