package com.dionu.simplejson

class Sport(val idSport: String, val strSport : String, val strFormat : String, val strSportThumb : String, val strSportThumbGreen : String, val strSportDescription : String) {

    override fun toString(): String {
        return "Sport ID : ${idSport} - Sport : ${strSport} - Format : ${strFormat} - SportThumb : ${strSportThumb} - SportThumbGreen : ${strSportThumbGreen} - Description : ${strSportDescription}"
    }
}