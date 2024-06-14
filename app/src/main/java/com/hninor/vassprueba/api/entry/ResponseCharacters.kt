package com.hninor.vassprueba.api.entry

import com.google.gson.annotations.SerializedName


data class ResponseCharacters (

  @SerializedName("info"    ) var info    : Info?              = Info(),
  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf()

)