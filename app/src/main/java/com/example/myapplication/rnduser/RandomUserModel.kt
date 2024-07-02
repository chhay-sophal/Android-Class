// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.myapplication.rnduser

data class User (
    val results: List<UserResult>,
    val info: Info
)

data class Info (
    val seed: String,
    val page: Long,
    val results: Long,
    val version: String
)

data class UserResult (
    val nat: String,
    val gender: String,
    val phone: String,
    val dob: Dob,
    val name: Name,
    val registered: Dob,
    val location: Location,
    val id: ID,
    val login: Login,
    val cell: String,
    val email: String,
    val picture: Picture
)

data class Dob (
    val date: String,
    val age: Long
)

data class ID (
    val name: String,
    val value: String
)

data class Location (
    val country: String,
    val city: String,
    val street: Street,
    val timezone: Timezone,
//    val postcode: String,
    val coordinates: Coordinates,
    val state: String
)

data class Coordinates (
    val latitude: String,
    val longitude: String
)

data class Street (
    val number: Long,
    val name: String
)

data class Timezone (
    val offset: String,
    val description: String
)

data class Login (
    val sha1: String,
    val password: String,
    val salt: String,
    val sha256: String,
    val uuid: String,
    val username: String,
    val md5: String
)

data class Name (
    val last: String,
    val title: String,
    val first: String
)

data class Picture (
    val thumbnail: String,
    val large: String,
    val medium: String
)
